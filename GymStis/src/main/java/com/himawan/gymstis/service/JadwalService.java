package com.himawan.gymstis.service;

import com.himawan.gymstis.dto.JadwalResponse;
import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public interface JadwalService {
    JadwalResponse createJadwal(LocalDate date, Gender gender, Integer kuota);
    Jadwal findByDate(LocalDate date);
    void saveJadwal(Jadwal jadwal);

    JadwalResponse editJadwal(LocalDate date, Gender gender, Integer kuota);

    List<JadwalResponse> checkJadwalAvailable();
}
