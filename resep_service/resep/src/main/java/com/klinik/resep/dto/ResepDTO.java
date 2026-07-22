package com.klinik.resep.dto;

public class ResepDTO {
    private Integer idJadwal;
    private String penyakitKeluhan;
    private Integer idObat;
    private Integer jumlahObat;
    private String namaDokter;
    private String dosisAturanPakai;
    private Double hargaSatuan;

    public ResepDTO() {}

    public ResepDTO(Integer idJadwal, String penyakitKeluhan, Integer idObat, Integer jumlahObat, 
                    String namaDokter, String dosisAturanPakai, Double hargaSatuan) {
        this.idJadwal = idJadwal;
        this.penyakitKeluhan = penyakitKeluhan;
        this.idObat = idObat;
        this.jumlahObat = jumlahObat;
        this.namaDokter = namaDokter;
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

    public Integer getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(Integer jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
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
