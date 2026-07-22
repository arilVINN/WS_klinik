package com.klinik.laporan.service;

import com.klinik.laporan.dto.LaporanDTO;
import com.klinik.laporan.model.LaporanModel;
import com.klinik.laporan.repository.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LaporanService {

    @Autowired
    private LaporanRepository laporanRepository;

    public List<LaporanModel> getAllLaporan() {
        return laporanRepository.findAll();
    }

    public Optional<LaporanModel> getLaporanById(String id) {
        LaporanModel laporan = laporanRepository.findById(id);
        return Optional.ofNullable(laporan);
    }

    public List<LaporanModel> getLaporanByIdJadwal(Integer idJadwal) {
        return laporanRepository.findByIdJadwal(idJadwal);
    }

    public LaporanModel generateLaporan(LaporanDTO dto) {
        Double hargaTindakan = dto.getHargaTindakan() != null ? dto.getHargaTindakan() : 0.0;
        Double totalObat = dto.getTotalHargaObat() != null ? dto.getTotalHargaObat() : 0.0;

        LaporanModel laporan = new LaporanModel(
                dto.getIdJadwal(),
                dto.getNamaPasien(),
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
