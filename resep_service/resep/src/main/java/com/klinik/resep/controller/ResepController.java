package com.klinik.resep.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.resep.config.DataSeederResep;
import com.klinik.resep.dto.ResepDTO;
import com.klinik.resep.model.ResepModel;
import com.klinik.resep.service.ResepService;

@RestController
@RequestMapping("/recipe")
public class ResepController {

    @Autowired
    private ResepService resepService;

    @Autowired
    private DataSeederResep dataSeederResep;

    // GET semua resep - /api/resep/getall
    @GetMapping("/getall")
    public Map<String, Object> getAllResep() {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getAllResep();
        response.put("status", "success");
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET detail resep by ID - /api/resep/get/{id}
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

    // GET resep by idJadwal - /api/resep/jadwal/{idJadwal}
    @GetMapping("/jadwal/{idJadwal}")
    public Map<String, Object> getResepByIdJadwal(@PathVariable("idJadwal") String idJadwal) {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getResepByIdJadwal(idJadwal);
        response.put("status", "success");
        response.put("idJadwal", idJadwal);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // GET resep by nama pasien - /api/v1/resep/pasien/{namaPasien}
    @GetMapping("/pasien/{namaPasien}")
    public Map<String, Object> getResepByNamaPasien(@PathVariable("namaPasien") String namaPasien) {
        Map<String, Object> response = new HashMap<>();
        List<ResepModel> list = resepService.getResepByNamaPasien(namaPasien);
        response.put("status", "success");
        response.put("namaPasien", namaPasien);
        response.put("jumlah", list.size());
        response.put("data", list);
        return response;
    }

    // POST tambah resep baru - /api/resep/add
    @PostMapping("/add")
    public Map<String, Object> tambahResep(@RequestBody ResepDTO dto) {
        Map<String, Object> response = new HashMap<>();
        if (dto.getIdJadwal() == null || dto.getIdJadwal().trim().isEmpty() || dto.getPenyakitKeluhan() == null) {
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

    // PUT update resep - /api/resep/update?id=xxx
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

    // DELETE hapus resep - /api/resep/delete/{id}
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

    // POST reset dummy data - /api/resep/reset-dummy
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
