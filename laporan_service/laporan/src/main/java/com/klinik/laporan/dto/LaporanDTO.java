package com.klinik.laporan.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LaporanDTO", description = "DTO untuk pembuatan atau pembaruan laporan keuangan")
public class LaporanDTO {
    @Schema(description = "ID jadwal yang terkait dengan laporan", example = "123", required = true)
    private Integer idJadwal;

    @Schema(description = "Nama pasien", example = "Budi Santoso", required = true)
    private String namaPasien;

    @Schema(description = "Biaya tindakan medis", example = "150000", required = false)
    private Double hargaTindakan;

    @Schema(description = "Total harga obat", example = "35000", required = false)
    private Double totalHargaObat;

    @Schema(description = "Metode pembayaran", example = "TUNAI", required = false)
    private String metodePembayaran;

    public LaporanDTO() {}

    public LaporanDTO(Integer idJadwal, String namaPasien, Double hargaTindakan, 
                      Double totalHargaObat, String metodePembayaran) {
        this.idJadwal = idJadwal;
        this.namaPasien = namaPasien;
        this.hargaTindakan = hargaTindakan;
        this.totalHargaObat = totalHargaObat;
        this.metodePembayaran = metodePembayaran;
    }

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
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
