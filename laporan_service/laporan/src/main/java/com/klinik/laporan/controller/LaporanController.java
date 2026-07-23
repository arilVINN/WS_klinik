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
@RequestMapping("/api/laporan")
@Tag(name = "Laporan Service", description = "API endpoints untuk laporan keuangan klinik")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    @Autowired
    private DataSeederLaporan dataSeederLaporan;

    // GET semua laporan keuangan - /api/laporan/getall
    @Operation(summary = "Ambil semua laporan", description = "Mengambil daftar seluruh laporan keuangan")
    @GetMapping("/getall")
    public Map<String, Object> getAllLaporan() {
        Map<String, Object> response = new HashMap<>();
        List<LaporanModel> list = laporanService.getAllLaporan();
        response.put("status", "success");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET detail laporan by ID - /api/laporan/get/{id}
    @Operation(summary = "Ambil detail laporan", description = "Mengambil detail laporan berdasarkan ID")
    @GetMapping("/get/{id}")
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
    @Operation(summary = "Ambil laporan per jadwal", description = "Mengambil laporan berdasarkan id jadwal")
    @GetMapping("/jadwal/{idJadwal}")
    public Map<String, Object> getLaporanByIdJadwal(@PathVariable("idJadwal") Integer idJadwal) {
        Map<String, Object> response = new HashMap<>();
        List<LaporanModel> list = laporanService.getLaporanByIdJadwal(idJadwal);
        response.put("status", "success");
        response.put("idJadwal", idJadwal);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // POST generate laporan baru - /api/laporan/generate
    @Operation(summary = "Buat laporan baru", description = "Membuat laporan keuangan baru menggunakan data yang diberikan")
    @PostMapping("/generate")
    public Map<String, Object> generateLaporan(@RequestBody LaporanDTO dto) {
        Map<String, Object> response = new HashMap<>();
        if (dto.getIdJadwal() == null || dto.getNamaPasien() == null) {
            response.put("status", "error");
            response.put("message", "idJadwal dan namaPasien wajib diisi");
            return response;
        }

        LaporanModel laporanBaru = laporanService.generateLaporan(dto);
        response.put("status", "success");
        response.put("message", "Laporan keuangan berhasil dibuat");
        response.put("data", laporanBaru);
        return response;
    }

    // GET summary total pendapatan & transaksi (MongoTemplate Custom Query) - /api/laporan/summary
    @Operation(summary = "Ambil ringkasan keuangan", description = "Mengambil ringkasan total pendapatan dan transaksi")
    @GetMapping("/summary")
    public Map<String, Object> getSummaryKeuangan() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> summary = laporanService.getSummaryKeuangan();
        response.put("status", "success");
        response.put("data", summary);
        return response;
    }

    // DELETE hapus laporan - /api/laporan/delete/{id}
    @Operation(summary = "Hapus laporan", description = "Menghapus laporan berdasarkan ID")
    @DeleteMapping("/delete/{id}")
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
    @Operation(summary = "Update laporan", description = "Memperbarui laporan berdasarkan ID dan data yang diberikan")
    @PutMapping("/update")
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
    @Operation(summary = "Reset data dummy", description = "Mengembalikan data laporan dummy ke keadaan awal")
    @PostMapping("/reset-dummy")
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
