package com.klinik.jadwal.controller;

import com.klinik.jadwal.model.JadwalModel;
import com.klinik.jadwal.service.JadwalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jadwal")
@Tag(name = "Jadwal", description = "API untuk mengelola jadwal layanan klinik")
public class JadwalController {
    @Autowired
    private JadwalService js;

    @PostMapping
    @Operation(summary = "Buat jadwal baru", description = "Menambahkan jadwal pemeriksaan pasien ke sistem")
    public boolean createJadwal(@RequestBody JadwalModel jadwal) {
        return js.createJadwal(jadwal);
    }

    @GetMapping
    @Operation(summary = "Dapatkan semua jadwal", description = "Mengambil daftar semua jadwal pemeriksaan")
    public List<JadwalModel> getAllJadwal() {
        return js.getAllJadwal();
    }
}

