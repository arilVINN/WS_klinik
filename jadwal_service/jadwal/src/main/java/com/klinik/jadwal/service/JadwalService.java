package com.klinik.jadwal.service;

import com.klinik.jadwal.model.JadwalModel;
import com.klinik.jadwal.repository.JadwalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JadwalService {
    // Service methods will be implemented here
    @Autowired
    JadwalRepository jr;

    public boolean createJadwal(JadwalModel jadwal) {
        try {
            jr.save(jadwal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<JadwalModel> getAllJadwal() {
        try {
            return jr.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }
}