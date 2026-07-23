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

@RestController
@RequestMapping("/report")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    @Autowired
    private DataSeederLaporan dataSeederLaporan;

    // GET semua laporan keuangan - /api/laporan/getall
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
    @GetMapping("/jadwal/{idJadwal}")
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
    public Map<String, Object> getSummaryKeuangan() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> summary = laporanService.getSummaryKeuangan();
        response.put("status", "success");
        response.put("data", summary);
        return response;
    }

    // DELETE hapus laporan - /api/laporan/delete/{id}
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
