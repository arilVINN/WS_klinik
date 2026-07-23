package com.klinik.resep.repository;

import com.klinik.resep.model.ResepModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResepRepository extends MongoRepository<ResepModel, String> {
    List<ResepModel> findByIdJadwal(Integer idJadwal);
    List<ResepModel> findByNamaPasienContainingIgnoreCase(String namaPasien);
}
