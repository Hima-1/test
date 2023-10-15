package com.himawan.gymstis.mapper;

import com.himawan.gymstis.dto.JadwalResponse;
import com.himawan.gymstis.entity.Jadwal;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class JadwalMapper {
    public static JadwalResponse toJadwalResponse(Jadwal jadwal) {
        return JadwalResponse.builder()
                .hari(jadwal.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("id", "ID")))
                .date(jadwal.getDate())
                .gender(jadwal.getGender())
                .peminjam(jadwal.getPeminjam())
                .kuota(jadwal.getKuota())
                .build();
    }

    public static List<JadwalResponse> toJadwalResponseList(List<Jadwal> jadwals) {
        return jadwals.stream()
                .map(JadwalMapper::toJadwalResponse)
                .collect(Collectors.toList());
    }
}
