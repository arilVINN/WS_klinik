package com.klinik.resep.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResepDTO", description = "DTO untuk pembuatan atau pembaruan resep obat")
public class ResepDTO {
    @Schema(description = "ID jadwal yang terkait dengan resep", example = "123", required = true)
    private Integer idJadwal;

    @Schema(description = "Keluhan atau penyakit pasien", example = "Demam berdarah", required = true)
    private String penyakitKeluhan;

    @Schema(description = "ID obat yang diresepkan", example = "10", required = false)
    private Integer idObat;

    @Schema(description = "Nama obat", example = "Paracetamol", required = false)
    private String namaObat;

    @Schema(description = "Jumlah obat yang diresepkan", example = "2", required = false)
    private Integer jumlahObat;

    @Schema(description = "Nama pasien", example = "Siti Aisyah", required = true)
    private String namaPasien;

    @Schema(description = "Dosis dan aturan pakai obat", example = "2 kali sehari setelah makan", required = false)
    private String dosisAturanPakai;

    @Schema(description = "Harga satuan obat", example = "5000", required = false)
    private Double hargaSatuan;

    public ResepDTO() {}

    public ResepDTO(Integer idJadwal, String penyakitKeluhan, Integer idObat, String namaObat, Integer jumlahObat, 
                    String namaPasien, String dosisAturanPakai, Double hargaSatuan) {
        this.idJadwal = idJadwal;
        this.penyakitKeluhan = penyakitKeluhan;
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.jumlahObat = jumlahObat;
        this.namaPasien = namaPasien;
        this.dosisAturanPakai = dosisAturanPakai;
        this.hargaSatuan = hargaSatuan;
    }

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getPenyakitKeluhan() {
        return penyakitKeluhan;
    }

    public void setPenyakitKeluhan(String penyakitKeluhan) {
        this.penyakitKeluhan = penyakitKeluhan;
    }

    public Integer getIdObat() {
        return idObat;
    }

    public void setIdObat(Integer idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public Integer getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(Integer jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getDosisAturanPakai() {
        return dosisAturanPakai;
    }

    public void setDosisAturanPakai(String dosisAturanPakai) {
        this.dosisAturanPakai = dosisAturanPakai;
    }

    public Double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(Double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}
