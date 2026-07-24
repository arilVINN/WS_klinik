package com.klinik.resep.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResepRequest", description = "Payload untuk membuat resep obat pasien klinik gigi")
public class ResepDTO {
    @Schema(description = "ID jadwal pemeriksaan", example = "jadwal-001")
    private String idJadwal;

    @Schema(description = "Keluhan atau penyakit pasien", example = "Gigi berlubang")
    private String penyakitKeluhan;

    @Schema(description = "ID obat", example = "10")
    private Long idObat;

    @Schema(description = "Nama obat", example = "Amoxicillin")
    private String namaObat;

    @Schema(description = "Jumlah obat", example = "10")
    private Integer jumlahObat;

    @Schema(description = "Nama pasien", example = "Siti Aminah")
    private String namaPasien;

    @Schema(description = "Dosis dan aturan pakai", example = "3x1 sehari setelah makan")
    private String dosisAturanPakai;

    @Schema(description = "Harga satuan obat", example = "15000")
    private Double hargaSatuan;

    public ResepDTO() {}

    public ResepDTO(String idJadwal, String penyakitKeluhan, Long idObat, String namaObat, Integer jumlahObat, 
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

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getPenyakitKeluhan() {
        return penyakitKeluhan;
    }

    public void setPenyakitKeluhan(String penyakitKeluhan) {
        this.penyakitKeluhan = penyakitKeluhan;
    }

    public Long getIdObat() {
        return idObat;
    }

    public void setIdObat(Long idObat) {
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
