package com.klinik.laporan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klinik.laporan.config.DataSeederLaporan;
import com.klinik.laporan.dto.LaporanDTO;
import com.klinik.laporan.model.LaporanModel;
import com.klinik.laporan.service.LaporanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/report")
@Tag(name = "Laporan", description = "API untuk mengelola laporan keuangan dan transaksi klinik gigi")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    @Autowired
    private DataSeederLaporan dataSeederLaporan;

    // GET semua laporan keuangan - /api/laporan/getall
    @GetMapping("/getall")
    @Operation(summary = "Daftar semua laporan", description = "Mengambil seluruh laporan keuangan klinik gigi")
    public Map<String, Object> getAllLaporan() {
        Map<String, Object> response = new HashMap<>();
        List<LaporanModel> list = laporanService.getAllLaporan();
        response.put("status", "success");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET detail laporan by ID - /api/laporan/get/{id}
    @GetMapping("/get/{id}")
    @Operation(summary = "Detail laporan", description = "Mengambil data laporan berdasarkan ID")
    public Map<String, Object> getLaporanById(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        Optional<LaporanModel> optionalLaporan = laporanService.getLaporanById(id);
        if (optionalLaporan.isPresent()) {
            response.put("status", "success");
            response.put("data", optionalLaporan.get());
        } else {
            response.put("status", "error");
            response.put("message", "Laporan dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // GET laporan by idJadwal - /api/laporan/jadwal/{idJadwal}
    @GetMapping("/jadwal/{idJadwal}")
    @Operation(summary = "Laporan berdasarkan jadwal", description = "Mengambil laporan keuangan untuk satu jadwal pemeriksaan pasien")
    public Map<String, Object> getLaporanByIdJadwal(@PathVariable("idJadwal") String idJadwal) {
        Map<String, Object> response = new HashMap<>();
        List<LaporanModel> list = laporanService.getLaporanByIdJadwal(idJadwal);
        response.put("status", "success");
        response.put("idJadwal", idJadwal);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // POST generate laporan baru - /api/laporan/generate
    @PostMapping("/generate")
    @Operation(summary = "Buat laporan keuangan", description = "Membuat laporan transaksi berdasarkan data jadwal dan pasien klinik gigi")
    public Map<String, Object> generateLaporan(@RequestBody LaporanDTO dto) {
        Map<String, Object> response = new HashMap<>();
        if (dto.getIdJadwal() == null) {
            response.put("status", "error");
            response.put("message", "idJadwal wajib diisi");
            return response;
        }

        try {
            LaporanModel laporanBaru = laporanService.generateLaporan(dto);
            response.put("status", "success");
            response.put("message", "Laporan keuangan berhasil dibuat");
            response.put("data", laporanBaru);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    // GET summary total pendapatan & transaksi (MongoTemplate Custom Query) -
    // /api/laporan/summary
    @GetMapping("/summary")
    @Operation(summary = "Ringkasan keuangan", description = "Mengambil ringkasan total pendapatan dan transaksi klinik gigi")
    public Map<String, Object> getSummaryKeuangan() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> summary = laporanService.getSummaryKeuangan();
        response.put("status", "success");
        response.put("data", summary);
        return response;
    }

    // DELETE hapus laporan - /api/laporan/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Hapus laporan", description = "Menghapus satu laporan keuangan berdasarkan ID")
    public Map<String, Object> hapusLaporan(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = laporanService.hapusLaporan(id);
        if (deleted) {
            response.put("status", "success");
            response.put("message", "Laporan dengan ID " + id + " berhasil dihapus");
        } else {
            response.put("status", "error");
            response.put("message", "Laporan dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // PUT update laporan - /api/laporan/update?id=xxx
    @PutMapping("/update")
    @Operation(summary = "Perbarui laporan", description = "Memperbarui data laporan keuangan yang telah dibuat")
    public Map<String, Object> updateLaporan(
            @RequestParam("id") String id,
            @RequestBody LaporanDTO dto) {
        Map<String, Object> response = new HashMap<>();
        Optional<LaporanModel> updated = laporanService.updateLaporan(id, dto);
        if (updated.isPresent()) {
            response.put("status", "success");
            response.put("message", "Laporan berhasil diperbarui");
            response.put("data", updated.get());
        } else {
            response.put("status", "error");
            response.put("message", "Laporan dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // POST reset dummy data - /api/laporan/reset-dummy
    @PostMapping("/reset-dummy")
    @Operation(summary = "Reset data dummy", description = "Mengembalikan data dummy laporan keuangan untuk pengujian")
    public Map<String, Object> resetDummyData() {
        Map<String, Object> response = new HashMap<>();
        List<LaporanModel> list = dataSeederLaporan.seedDummyData();
        response.put("status", "success");
        response.put("message", "Data dummy laporan keuangan berhasil direset");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }
}
