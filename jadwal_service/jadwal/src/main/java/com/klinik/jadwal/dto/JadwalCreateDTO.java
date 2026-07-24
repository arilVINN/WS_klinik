package com.klinik.jadwal.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "JadwalCreateRequest", description = "Payload untuk membuat jadwal pemeriksaan klinik gigi")
public class JadwalCreateDTO {
    @Schema(description = "Nama pasien", example = "Siti Aminah")
    private String nama_pasien;

    @Schema(description = "Waktu pemeriksaan", example = "2026-07-24 09:00")
    private String jadwal_periksa;

    @Schema(description = "Jenis tindakan yang dilakukan", example = "Scaling gigi")
    private String periksa_tindakan;

    @Schema(description = "Biaya pemeriksaan", example = "250000")
    private double harga_periksa;

    @Schema(description = "Status jadwal", example = "Booked")
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
