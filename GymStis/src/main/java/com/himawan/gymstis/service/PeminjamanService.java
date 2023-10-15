package com.himawan.gymstis.service;

import com.himawan.gymstis.dto.PeminjamanResponse;

import java.time.LocalDate;

public interface PeminjamanService {
    PeminjamanResponse createPeminjaman(LocalDate tanggal);
    PeminjamanResponse approvePeminjaman(Long id);

    PeminjamanResponse denyPeminjaman(Long id);
}
