package com.himawan.gymstis.service.implementation;

import com.himawan.gymstis.dto.JadwalResponse;
import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.enums.Gender;
import com.himawan.gymstis.mapper.JadwalMapper;
import com.himawan.gymstis.repository.JadwalRepository;
import com.himawan.gymstis.service.JadwalService;
import com.himawan.gymstis.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JadwalServiceImpl implements JadwalService{

    final
    JadwalRepository jadwalRepository;
    final
    UserService userService;

    public JadwalServiceImpl(JadwalRepository jadwalRepository, UserService userService) {
        this.jadwalRepository = jadwalRepository;
        this.userService = userService;
    }

    @Override
    public JadwalResponse createJadwal(LocalDate date, Gender gender, Integer kuota) {
        Optional<Jadwal> existingJadwal = jadwalRepository.findByDate(date);
        if (existingJadwal.isPresent()) {
            throw new RuntimeException("Error: Jadwal Sudah ada");
        }
        Jadwal jadwal = Jadwal.builder()
                .date(date)
                .gender(gender)
                .kuota(kuota)
                .peminjam(0)
                .createdAt(LocalDateTime.now())
                .build();
        jadwalRepository.save(jadwal);
        return JadwalMapper.toJadwalResponse(jadwal);
    }

    @Override
    public List<JadwalResponse> checkJadwal() {
        List<Jadwal> jadwals = jadwalRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
        if (jadwals.isEmpty()) {
            throw new RuntimeException("Error: Tidak ada jadwal yang tersedia");
        }
        return JadwalMapper.toJadwalResponseList(jadwals);
    }

    @Override
    public Jadwal findByDate(LocalDate date) {
        Optional<Jadwal> existingJadwal = jadwalRepository.findByDate(date);
        if (existingJadwal.isEmpty()) {
            throw new RuntimeException("Error: Jadwal Tidak ditemukan");
        }
        return existingJadwal.get();
    }

    @Override
    public void saveJadwal(Jadwal jadwal) {
        jadwalRepository.save(jadwal);
    }

    @Override
    public JadwalResponse editJadwal(LocalDate date, Gender gender, Integer kuota) {
        Optional<Jadwal> existingJadwal = jadwalRepository.findByDate(date);
        if (existingJadwal.isEmpty()) {
            throw new RuntimeException("Error: Jadwal Tidak ditemukan");
        } else if (existingJadwal.get().getPeminjam() != 0){
            throw new RuntimeException("Error: Jadwal sudah terisi");
        }
        Jadwal jadwal = existingJadwal.get();
        jadwal.setGender(gender);
        jadwal.setDate(date);
        jadwal.setKuota(kuota);
        jadwalRepository.save(jadwal);

        return JadwalMapper.toJadwalResponse(jadwal);
    }


}
