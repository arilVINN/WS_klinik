package com.klinik.laporan.service;

import com.klinik.laporan.dto.LaporanDTO;
import com.klinik.laporan.model.LaporanModel;
import com.klinik.laporan.repository.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LaporanService {

    @Autowired
    private LaporanRepository laporanRepository;

    private final WebClient webClient;

    public LaporanService() {
        this.webClient = WebClient.create();
    }

    public List<LaporanModel> getAllLaporan() {
        return laporanRepository.findAll();
    }

    public Optional<LaporanModel> getLaporanById(String id) {
        LaporanModel laporan = laporanRepository.findById(id);
        return Optional.ofNullable(laporan);
    }

    public List<LaporanModel> getLaporanByIdJadwal(String idJadwal) {
        return laporanRepository.findByIdJadwal(idJadwal);
    }

    public LaporanModel generateLaporan(LaporanDTO dto) {
        if (dto.getIdJadwal() == null || dto.getIdJadwal().trim().isEmpty()) {
            throw new RuntimeException("ID Jadwal wajib diisi");
        }

        // Fetch data dari jadwal-service (running on port 63)
        Map<String, Object> jadwal = null;
        try {
            jadwal = webClient.get()
                    .uri("http://localhost:63/schedule/" + dto.getIdJadwal())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghubungi Jadwal Service atau data tidak ditemukan: " + e.getMessage());
        }

        if (jadwal == null || !jadwal.containsKey("nama_pasien")) {
            throw new RuntimeException("Jadwal dengan ID " + dto.getIdJadwal() + " tidak ditemukan");
        }

        String namaPasien = (String) jadwal.get("nama_pasien");
        Number hargaPeriksaNum = (Number) jadwal.get("harga_periksa");
        if (hargaPeriksaNum == null) {
            hargaPeriksaNum = (Number) jadwal.get("harga_tindakan");
        }
        Double hargaTindakan = 0.0;
        if (hargaPeriksaNum != null && hargaPeriksaNum.doubleValue() > 0) {
            hargaTindakan = hargaPeriksaNum.doubleValue();
        } else if (dto.getHargaTindakan() != null) {
            hargaTindakan = dto.getHargaTindakan();
        }
        Double totalObat = dto.getTotalHargaObat() != null ? dto.getTotalHargaObat() : 0.0;

        LaporanModel laporan = new LaporanModel(
                dto.getIdJadwal(),
                namaPasien,
                hargaTindakan,
                totalObat,
                dto.getMetodePembayaran()
        );
        laporan.generateLaporan();
        laporan.cetakTagihan();

        return laporanRepository.save(laporan);
    }

    public boolean hapusLaporan(String id) {
        return laporanRepository.deleteById(id);
    }

    public Map<String, Object> getSummaryKeuangan() {
        return laporanRepository.getRekapitulasiKeuangan();
    }

    public Optional<LaporanModel> updateLaporan(String id, LaporanDTO dto) {
        LaporanModel l = laporanRepository.findById(id);
        if (l != null) {
            if (dto.getIdJadwal() != null) l.setIdJadwal(dto.getIdJadwal());
            if (dto.getNamaPasien() != null) l.setNamaPasien(dto.getNamaPasien());
            if (dto.getHargaTindakan() != null) l.setHargaTindakan(dto.getHargaTindakan());
            if (dto.getTotalHargaObat() != null) l.setTotalHargaObat(dto.getTotalHargaObat());
            if (dto.getMetodePembayaran() != null) l.setMetodePembayaran(dto.getMetodePembayaran());
            
            l.kalkulasiGrandTotal();
            
            return Optional.of(laporanRepository.save(l));
        }
        return Optional.empty();
    }
}
