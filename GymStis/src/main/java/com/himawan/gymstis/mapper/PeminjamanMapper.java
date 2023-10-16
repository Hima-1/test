package com.himawan.gymstis.mapper;

import com.himawan.gymstis.dto.PeminjamanUserResponse;
import com.himawan.gymstis.entity.Peminjaman;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeminjamanMapper {
    public static PeminjamanUserResponse toPeminjamanResponse(Peminjaman peminjaman) {
        return PeminjamanUserResponse.builder()
                .date(peminjaman.getJadwal().getDate())
                .status(peminjaman.getStatus())
                .build();
    }

    public static List<PeminjamanUserResponse> toPeminjamanResponseList(List<Peminjaman> peminjamans) {
        return peminjamans.stream()
                .map(PeminjamanMapper::toPeminjamanResponse)
                .collect(Collectors.toList());
    }
}
