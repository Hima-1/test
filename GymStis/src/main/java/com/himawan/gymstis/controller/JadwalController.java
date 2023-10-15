package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.JadwalDTO;
import com.himawan.gymstis.dto.JadwalResponse;
import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.service.JadwalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {
    @Autowired
    private JadwalService jadwalService;

    @PostMapping("/create")
    public ResponseEntity<Object> createJadwal(@Valid @RequestBody JadwalDTO request) {
        try {
            JadwalResponse jadwal = jadwalService.createJadwal(request.getDate(), request.getGender(), request.getKuota());
            return ResponseEntity.ok(jadwal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> checkJadwal() {
        try {
            List<JadwalResponse> jadwals = jadwalService.checkJadwal();
            return ResponseEntity.ok(jadwals);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
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
