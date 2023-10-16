package com.himawan.gymstis.service;

import com.himawan.gymstis.dto.PeminjamanStatusRequest;
import com.himawan.gymstis.dto.PeminjamanStatusResponse;
import com.himawan.gymstis.dto.PeminjamanUserResponse;
import com.himawan.gymstis.entity.Peminjaman;

import java.time.LocalDate;
import java.util.List;

public interface PeminjamanService {
    PeminjamanUserResponse createPeminjaman(LocalDate tanggal);
    PeminjamanStatusResponse approvePeminjaman(Peminjaman peminjaman);

    PeminjamanStatusResponse denyPeminjaman(Peminjaman peminjaman);

    List<PeminjamanUserResponse> getUserPeminjaman();

    PeminjamanStatusResponse changeStatusPeminjaman(Long id, PeminjamanStatusRequest status);
}
