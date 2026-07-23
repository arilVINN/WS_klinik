package com.klinik.laporan.repository;

import com.klinik.laporan.model.LaporanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LaporanRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public LaporanModel save(LaporanModel laporan) {
        return mongoTemplate.save(laporan);
    }

    public List<LaporanModel> saveAll(List<LaporanModel> laporanList) {
        return (List<LaporanModel>) mongoTemplate.insertAll(laporanList);
    }

    public List<LaporanModel> findAll() {
        return mongoTemplate.findAll(LaporanModel.class);
    }

    public LaporanModel findById(String id) {
        return mongoTemplate.findById(id, LaporanModel.class);
    }

    public List<LaporanModel> findByIdJadwal(String idJadwal) {
        Query query = new Query(Criteria.where("idJadwal").is(idJadwal));
        return mongoTemplate.find(query, LaporanModel.class);
    }

    public boolean deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, LaporanModel.class).getDeletedCount() > 0;
    }

    public void deleteAll() {
        mongoTemplate.remove(new Query(), LaporanModel.class);
    }

    public long count() {
        return mongoTemplate.count(new Query(), LaporanModel.class);
    }

    // Custom query / aggregation via MongoTemplate
    public Map<String, Object> getRekapitulasiKeuangan() {
        List<LaporanModel> list = findAll();
        double totalHargaTindakan = 0.0;
        double totalHargaObat = 0.0;
        double totalPendapatanGrand = 0.0;

        for (LaporanModel lap : list) {
            if (lap.getHargaTindakan() != null) totalHargaTindakan += lap.getHargaTindakan();
            if (lap.getTotalHargaObat() != null) totalHargaObat += lap.getTotalHargaObat();
            if (lap.getGrandTotal() != null) totalPendapatanGrand += lap.getGrandTotal();
        }

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalTransaksi", list.size());
        summary.put("totalHargaTindakan", totalHargaTindakan);
        summary.put("totalHargaObat", totalHargaObat);
        summary.put("grandTotalPendapatan", totalPendapatanGrand);
        return summary;
    }
}
