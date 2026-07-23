package com.klinik.laporan.dto;

public class LaporanDTO {
    private String idJadwal;
    private String namaPasien;
    private Double hargaTindakan;
    private Double totalHargaObat;
    private String metodePembayaran;

    public LaporanDTO() {}

    public LaporanDTO(String idJadwal, String namaPasien, Double hargaTindakan, 
                      Double totalHargaObat, String metodePembayaran) {
        this.idJadwal = idJadwal;
        this.namaPasien = namaPasien;
        this.hargaTindakan = hargaTindakan;
        this.totalHargaObat = totalHargaObat;
        this.metodePembayaran = metodePembayaran;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public Double getHargaTindakan() {
        return hargaTindakan;
    }

    public void setHargaTindakan(Double hargaTindakan) {
        this.hargaTindakan = hargaTindakan;
    }

    public Double getTotalHargaObat() {
        return totalHargaObat;
    }

    public void setTotalHargaObat(Double totalHargaObat) {
        this.totalHargaObat = totalHargaObat;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
