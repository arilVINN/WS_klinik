package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "InventoryCreateDTO", description = "DTO untuk membuat item inventaris baru")
@Data
public class InventoryCreateDTO {
    @Schema(description = "Nama item", example = "Stetoskop", required = true)
    private String name;

    @Schema(description = "Jumlah stok awal", example = "10", required = true)
    private Integer quantity;

    @Schema(description = "Kategori item inventaris", example = "Alat Medis", required = true)
    private String category;

    @Schema(description = "Harga satuan item", example = "120000", required = true)
    private Integer price;
}
