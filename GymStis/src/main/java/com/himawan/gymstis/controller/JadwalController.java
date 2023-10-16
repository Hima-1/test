package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.JadwalDTO;
import com.himawan.gymstis.dto.JadwalResponse;
import com.himawan.gymstis.service.JadwalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jadwal")
public class JadwalController {
    private final JadwalService jadwalService;

    public JadwalController(JadwalService jadwalService) {
        this.jadwalService = jadwalService;
    }

    @Operation(summary = "Membuat jadwal sesi peminjaman gym")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jadwal created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JadwalResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Object> createJadwal(@Valid @RequestBody JadwalDTO request) {
        try {
            JadwalResponse jadwal = jadwalService.createJadwal(request.getDate(), request.getGender(), request.getKuota());
            return ResponseEntity.ok(jadwal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mengambil semua list jadwal yang available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of jadwal", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JadwalResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Object> checkJadwalAvailable() {
        try {
            List<JadwalResponse> jadwals = jadwalService.checkJadwalAvailable();
            return ResponseEntity.ok(jadwals);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Edit existing jadwal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edited jadwal", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JadwalResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping
    public ResponseEntity<Object> editJadwal(@Valid @RequestBody JadwalDTO request) {
        try {
            JadwalResponse jadwal = jadwalService.editJadwal(request.getDate(), request.getGender(), request.getKuota());
            return ResponseEntity.ok(jadwal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
