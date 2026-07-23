package com.klinik.jadwal.dto;

public class JadwalCreateDTO {
    private String nama_pasien;
    private String jadwal_periksa;
    private String periksa_tindakan;
    private double harga_periksa; // Changed to double to prevent precision loss errors
    private String status;

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getJadwal_periksa() {
        return jadwal_periksa;
    }

    public void setJadwal_periksa(String jadwal_periksa) {
        this.jadwal_periksa = jadwal_periksa;
    }

    public String getPeriksa_tindakan() {
        return periksa_tindakan;
    }

    public void setPeriksa_tindakan(String periksa_tindakan) {
        this.periksa_tindakan = periksa_tindakan;
    }

    public double getHarga_periksa() {
        return harga_periksa;
    }

    public void setHarga_periksa(double harga_periksa) {
        this.harga_periksa = harga_periksa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
