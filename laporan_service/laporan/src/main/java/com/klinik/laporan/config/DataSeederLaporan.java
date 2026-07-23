package com.klinik.laporan.config;

import com.klinik.laporan.model.LaporanModel;
import com.klinik.laporan.repository.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeederLaporan implements CommandLineRunner {

    @Autowired
    private LaporanRepository laporanRepository;

    @Override
    public void run(String... args) throws Exception {
        if (laporanRepository.count() == 0) {
            seedDummyData();
        }
    }

    public List<LaporanModel> seedDummyData() {
        laporanRepository.deleteAll();

        List<LaporanModel> dummyLaporan = Arrays.asList(
                new LaporanModel(101, "Siti Rahma", 150000.0, 50000.0, "TUNAI"),
                new LaporanModel(102, "Ahmad Hidayat", 200000.0, 45000.0, "TRANSFER"),
                new LaporanModel(103, "Dewi Sartika", 350000.0, 90000.0, "QRIS"),
                new LaporanModel(104, "Rizky Pratama", 500000.0, 120000.0, "TUNAI")
        );
        
        System.out.println("=== Men-generate Laporan Dummy ===");
        for (LaporanModel laporan : dummyLaporan) {
            laporan.generateLaporan();
            laporan.cetakTagihan();
        }
        System.out.println("==================================");

        return laporanRepository.saveAll(dummyLaporan);
    }
}
