package com.himawan.gymstis.service.implementation;

import com.himawan.gymstis.dto.PeminjamanStatusRequest;
import com.himawan.gymstis.dto.PeminjamanStatusResponse;
import com.himawan.gymstis.dto.PeminjamanUserResponse;
import com.himawan.gymstis.entity.Peminjaman;
import com.himawan.gymstis.enums.Status;
import com.himawan.gymstis.mapper.PeminjamanMapper;
import com.himawan.gymstis.repository.PeminjamanRepository;
import com.himawan.gymstis.service.JadwalService;
import com.himawan.gymstis.service.PeminjamanService;
import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    PeminjamanRepository peminjamanRepository;
    @Autowired
    JadwalService jadwalService;
    @Autowired
    UserService userService;

    @Override
    public PeminjamanUserResponse createPeminjaman(LocalDate tanggal) {
        Jadwal jadwal = jadwalService.findByDate(tanggal);
        User user = userService.getUserLogged();
        Peminjaman peminjaman = Peminjaman.builder()
                .status(Status.PENDING)
                .jadwal(jadwal)
                .user(user)
                .build();
        if (peminjamanRepository.existsByUserAndJadwal(user, jadwal)){
            throw new RuntimeException("Error: Anda sudah melakukan pengajuan peminjaman sebelumnya");
        } else if (jadwal.getGender() != user.getGender()) {
            throw new RuntimeException("Error: Anda tidak bisa melakukan peminjaman di jadwal gender lain");
        }
        peminjamanRepository.save(peminjaman);
        return PeminjamanUserResponse.builder()
                .status(Status.PENDING)
                .date(tanggal)
                .build();
    }

    @Override
    public PeminjamanStatusResponse approvePeminjaman(Peminjaman peminjaman) {
        Jadwal jadwal = peminjaman.getJadwal();
        if (jadwal.getPeminjam() < jadwal.getKuota()) {
            if ((peminjaman.getStatus() == Status.PENDING || peminjaman.getStatus() == Status.DENIED)) {
                peminjaman.setStatus(Status.APPROVED);
                jadwal.setPeminjam(jadwal.getPeminjam()+1);
                peminjamanRepository.save(peminjaman);
                jadwalService.saveJadwal(jadwal);
            }
        } else{
            throw new RuntimeException("Error: Kuota sesi jadwal sudah penuh");
        }
        return PeminjamanStatusResponse.builder()
                .status(peminjaman.getStatus())
                .date(peminjaman.getJadwal().getDate())
                .peminjam(jadwal.getPeminjam())
                .kuota(jadwal.getKuota())
                .build();
    }

    @Override
    public PeminjamanStatusResponse denyPeminjaman(Peminjaman peminjaman) {
        Jadwal jadwal = peminjaman.getJadwal();
        if (peminjaman.getStatus() == Status.APPROVED) {
            jadwal.setPeminjam(jadwal.getPeminjam()-1);
        }
        peminjaman.setStatus(Status.DENIED);
        peminjamanRepository.save(peminjaman);
        jadwalService.saveJadwal(jadwal);
        return PeminjamanStatusResponse.builder()
                .status(peminjaman.getStatus())
                .date(peminjaman.getJadwal().getDate())
                .peminjam(jadwal.getPeminjam())
                .kuota(jadwal.getKuota())
                .build();
    }

    @Override
    public List<PeminjamanUserResponse> getUserPeminjaman() {
        User user = userService.getUserLogged();
        List<Peminjaman> peminjamans = peminjamanRepository.findByUser(user);
        if (peminjamans.isEmpty()) {
            throw new RuntimeException("Error: Tidak ada peminjaman yang ada");
        }
        return PeminjamanMapper.toPeminjamanResponseList(peminjamans);
    }

    @Override
    public PeminjamanStatusResponse changeStatusPeminjaman(Long id, PeminjamanStatusRequest status) {
        Optional<Peminjaman> optionalPeminjaman = peminjamanRepository.findById(id);
        if (optionalPeminjaman.isEmpty()) {
            throw new RuntimeException("Error: Tidak ada peminjaman yang ditemukan");
        }
        Peminjaman peminjaman = optionalPeminjaman.get();
        if (Status.DENIED == status.getStatus()) {
            return denyPeminjaman(peminjaman);
        } else if (status.getStatus() == Status.APPROVED)
            return approvePeminjaman(peminjaman);
        else
            throw new RuntimeException("Error: Status tidak valid");
    }
}
