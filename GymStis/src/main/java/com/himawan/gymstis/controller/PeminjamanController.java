package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.PeminjamanStatusRequest;
import com.himawan.gymstis.dto.PeminjamanStatusResponse;
import com.himawan.gymstis.dto.PeminjamanUserRequest;
import com.himawan.gymstis.dto.PeminjamanUserResponse;
import com.himawan.gymstis.service.PeminjamanService;
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
@RequestMapping("/api/peminjaman")
public class PeminjamanController {
    private final PeminjamanService peminjamanService;

    public PeminjamanController(PeminjamanService peminjamanService) {
        this.peminjamanService = peminjamanService;
    }

    @Operation(summary = "Create a new peminjaman sesi jadwal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peminjaman created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PeminjamanUserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Object> createPeminjaman(@Valid @RequestBody PeminjamanUserRequest request) {
        try {
            PeminjamanUserResponse response = peminjamanService.createPeminjaman(request.getDate());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Retrieve all current logged user peminjamans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of peminjamans", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PeminjamanUserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Object> getUserPeminjamans() {
        try {
            List<PeminjamanUserResponse> responses = peminjamanService.getUserPeminjaman();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Change peminjaman status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status changed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PeminjamanStatusResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestBody PeminjamanStatusRequest status) {
        try {
            PeminjamanStatusResponse response = peminjamanService.changeStatusPeminjaman(id, status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
