package com.klinik.resep.controller;

import com.klinik.resep.config.DataSeederResep;
import com.klinik.resep.dto.ResepDTO;
import com.klinik.resep.model.ResepModel;
import com.klinik.resep.service.ResepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resep")
public class ResepController {

    @Autowired
    private ResepService resepService;

    @Autowired
    private DataSeederResep dataSeederResep;

    // GET semua resep - /api/v1/resep/getall
    @GetMapping("/getall")
    public Map<String, Object> getAllResep() {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getAllResep();
        response.put("status", "success");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET detail resep by ID - /api/v1/resep/get/{id}
    @GetMapping("/get/{id}")
    public Map<String, Object> getResepById(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ResepModel> optionalResep = resepService.getResepById(id);
        if (optionalResep.isPresent()) {
            response.put("status", "success");
            response.put("data", optionalResep.get());
        } else {
            response.put("status", "error");
            response.put("message", "Resep dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // GET resep by idJadwal - /api/v1/resep/jadwal/{idJadwal}
    @GetMapping("/jadwal/{idJadwal}")
    public Map<String, Object> getResepByIdJadwal(@PathVariable("idJadwal") Integer idJadwal) {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getResepByIdJadwal(idJadwal);
        response.put("status", "success");
        response.put("idJadwal", idJadwal);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET resep by nama dokter - /api/v1/resep/dokter/{namaDokter}
    @GetMapping("/dokter/{namaDokter}")
    public Map<String, Object> getResepByNamaDokter(@PathVariable("namaDokter") String namaDokter) {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getResepByNamaDokter(namaDokter);
        response.put("status", "success");
        response.put("namaDokter", namaDokter);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // POST tambah resep baru - /api/v1/resep/add
    @PostMapping("/add")
    public Map<String, Object> tambahResep(@RequestBody ResepDTO dto) {
        Map<String, Object> response = new HashMap<>();
        if (dto.getIdJadwal() == null || dto.getPenyakitKeluhan() == null) {
            response.put("status", "error");
            response.put("message", "idJadwal dan penyakitKeluhan wajib diisi");
            return response;
        }

        ResepModel resepBaru = resepService.tambahResepDariDTO(dto);
        response.put("status", "success");
        response.put("message", "Resep obat berhasil ditambahkan");
        response.put("data", resepBaru);
        return response;
    }

    // PUT update resep - /api/v1/resep/update?id=xxx
    @PutMapping("/update")
    public Map<String, Object> updateResep(
            @RequestParam("id") String id,
            @RequestBody ResepDTO dto) {
        Map<String, Object> response = new HashMap<>();
        Optional<ResepModel> updated = resepService.updateResep(id, dto);
        if (updated.isPresent()) {
            response.put("status", "success");
            response.put("message", "Resep obat berhasil diperbarui");
            response.put("data", updated.get());
        } else {
            response.put("status", "error");
            response.put("message", "Resep dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // DELETE hapus resep - /api/v1/resep/delete/{id}
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> hapusResep(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = resepService.hapusResep(id);
        if (deleted) {
            response.put("status", "success");
            response.put("message", "Resep dengan ID " + id + " berhasil dihapus");
        } else {
            response.put("status", "error");
            response.put("message", "Resep dengan ID " + id + " tidak ditemukan");
        }
        return response;
    }

    // POST reset dummy data - /api/v1/resep/reset-dummy
    @PostMapping("/reset-dummy")
    public Map<String, Object> resetDummyData() {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = dataSeederResep.seedDummyData();
        response.put("status", "success");
        response.put("message", "Data dummy resep berhasil direset");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }
}
