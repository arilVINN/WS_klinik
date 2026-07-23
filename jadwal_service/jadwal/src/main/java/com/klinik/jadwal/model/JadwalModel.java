package com.klinik.jadwal.model;

public class JadwalModel {
    private int id_jadwal;
    private String nama_pasien;
    private String jadwal_periksa;
    private String periksa_tindakan;
    private double harga_periksa;
    private String status;

    public int getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(int id_jadwal) {
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

    // --- Methods dari Class Diagram ---
    public void buatJadwal() {
        System.out.println("Jadwal dibuat untuk pasien " + this.nama_pasien + " pada " + this.jadwal_periksa);
    }

    public void ubahStatus(String statusBaru) {
        this.status = statusBaru;
    }

    public int getHargaTindakan() {
        return (int) this.harga_periksa;
    }
}
