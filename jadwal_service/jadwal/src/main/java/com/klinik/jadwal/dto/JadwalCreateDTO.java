package com.klinik.jadwal.dto;

import java.time.LocalDateTime;

public class JadwalCreateDTO {
    private String nama_pasien;
    private LocalDateTime jadwal_periksa;
    private String periksa_tindakan;
    private float harga_periksa;
    private String status;

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public LocalDateTime getJadwal_periksa() {
        return jadwal_periksa;
    }

    public void setJadwal_periksa(LocalDateTime jadwal_periksa) {
        this.jadwal_periksa = jadwal_periksa;
    }

    public String getPeriksa_tindakan() {
        return periksa_tindakan;
    }

    public void setPeriksa_tindakan(String periksa_tindakan) {
        this.periksa_tindakan = periksa_tindakan;
    }

    public float getHarga_periksa() {
        return harga_periksa;
    }

    public void setHarga_periksa(float harga_periksa) {
        this.harga_periksa = harga_periksa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
