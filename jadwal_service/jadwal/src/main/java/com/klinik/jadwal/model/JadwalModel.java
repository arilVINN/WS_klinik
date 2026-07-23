package com.klinik.jadwal.model;

public class JadwalModel {
    private String id_jadwal;
    private String nama_pasien;
    private String jadwal_periksa;
    private String periksa_tindakan;
    private double harga_tindakan;
    private String status;

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

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

    public double getHarga_tindakan() {
        return harga_tindakan;
    }

    public void setHarga_tindakan(double harga_tindakan) {
        this.harga_tindakan = harga_tindakan;
    }

    public double getHarga_periksa() {
        return harga_tindakan;
    }

    public void setHarga_periksa(double harga_periksa) {
        this.harga_tindakan = harga_periksa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
