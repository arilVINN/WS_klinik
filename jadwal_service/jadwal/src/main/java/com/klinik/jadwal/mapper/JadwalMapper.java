package com.klinik.jadwal.mapper;

import com.klinik.jadwal.dto.JadwalCreateDTO;
import com.klinik.jadwal.model.JadwalModel;

public class JadwalMapper {
    public static JadwalModel toModel(JadwalCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        JadwalModel model = new JadwalModel();
        model.setNama_pasien(dto.getNama_pasien());
        model.setJadwal_periksa(dto.getJadwal_periksa());
        model.setPeriksa_tindakan(dto.getPeriksa_tindakan());
        model.setHarga_tindakan(dto.getHarga_periksa());
        model.setStatus(dto.getStatus());
        return model;
    }

    public static JadwalCreateDTO toDTO(JadwalModel model) {
        if (model == null) {
            return null;
        }
        JadwalCreateDTO dto = new JadwalCreateDTO();
        dto.setNama_pasien(model.getNama_pasien());
        dto.setJadwal_periksa(model.getJadwal_periksa());
        dto.setPeriksa_tindakan(model.getPeriksa_tindakan());
        dto.setHarga_periksa(model.getHarga_tindakan());
        dto.setStatus(model.getStatus());
        return dto;
    }
}