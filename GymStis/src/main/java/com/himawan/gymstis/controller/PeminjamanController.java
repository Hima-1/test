package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.PeminjamanRequest;
import com.himawan.gymstis.dto.PeminjamanResponse;
import com.himawan.gymstis.service.PeminjamanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peminjaman")
public class PeminjamanController {
    private final PeminjamanService peminjamanService;

    public PeminjamanController(PeminjamanService peminjamanService) {
        this.peminjamanService = peminjamanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPeminjaman(@Valid @RequestBody PeminjamanRequest request) {
        try {
            PeminjamanResponse peminjamanResponse = peminjamanService.createPeminjaman(request.getDate());
            return ResponseEntity.ok(peminjamanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("e.getMessage()");
        }
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<Object> approvePeminjaman(@PathVariable Long id) {
        try {
            PeminjamanResponse peminjamanResponse = peminjamanService.approvePeminjaman(id);
            return ResponseEntity.ok(peminjamanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/deny/{id}")
    public ResponseEntity<Object> denyPeminjaman(@PathVariable Long id) {
        try {
            PeminjamanResponse peminjamanResponse = peminjamanService.denyPeminjaman(id);
            return ResponseEntity.ok(peminjamanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
