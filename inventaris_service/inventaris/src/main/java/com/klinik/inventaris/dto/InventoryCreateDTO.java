package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "InventoryCreateRequest", description = "Payload untuk menambah item inventaris klinik gigi")
public class InventoryCreateDTO {
    @Schema(description = "Nama item inventaris", example = "Bahan bleaching")
    private String name;

    @Schema(description = "Jumlah stok tersedia", example = "20")
    private Integer quantity;

    @Schema(description = "Kategori item", example = "perawatan")
    private String category;

    @Schema(description = "Harga satuan item", example = "150000")
    private Integer price;
}
