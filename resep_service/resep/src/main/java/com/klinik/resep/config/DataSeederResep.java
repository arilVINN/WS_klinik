package com.klinik.resep.config;

import com.klinik.resep.model.ResepModel;
import com.klinik.resep.repository.ResepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeederResep implements CommandLineRunner {

    @Autowired
    private ResepRepository resepRepository;

    @Override
    public void run(String... args) throws Exception {
        if (resepRepository.count() == 0) {
            seedDummyData();
        }
    }

    public List<ResepModel> seedDummyData() {
        resepRepository.deleteAll();

        ResepModel r1 = new ResepModel(101, null, null, null, "Drg. Amanda S.Kg", "3x1 sesudah makan", 25000.0, null);
        r1.catatKeluhan("Sakit Gigi Melubang & Gigi Sensitif");
        r1.tambahResep(1, 2);
        r1.setTotalHargaObat((double) r1.hitungTotalHargaObat());

        ResepModel r2 = new ResepModel(102, null, null, null, "Drg. Budi Santoso", "2x1 kumur setelah sikat gigi", 45000.0, null);
        r2.catatKeluhan("Pembersihan Karang Gigi & Gusi Berdarah");
        r2.tambahResep(2, 1);
        r2.setTotalHargaObat((double) r2.hitungTotalHargaObat());

        ResepModel r3 = new ResepModel(103, null, null, null, "Drg. Amanda S.Kg", "3x1 diminum jika nyeri", 30000.0, null);
        r3.catatKeluhan("Cabut Gigi Bungsu & Pembengkakan Gusi");
        r3.tambahResep(3, 3);
        r3.setTotalHargaObat((double) r3.hitungTotalHargaObat());

        ResepModel r4 = new ResepModel(104, null, null, null, "Drg. Clara Citra", "1x1 malam sebelum tidur", 60000.0, null);
        r4.catatKeluhan("Pemasangan Behel & Iritasi Mulut");
        r4.tambahResep(4, 2);
        r4.setTotalHargaObat((double) r4.hitungTotalHargaObat());

        List<ResepModel> dummyResep = Arrays.asList(r1, r2, r3, r4);

        return resepRepository.saveAll(dummyResep);
    }
}
