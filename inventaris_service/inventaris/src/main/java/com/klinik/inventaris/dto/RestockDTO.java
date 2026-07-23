package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "RestockDTO", description = "DTO untuk menambah atau mengurangi stok inventaris")
@Data
public class RestockDTO {
    @Schema(description = "Jumlah penyesuaian stok", example = "5", required = true)
    private Integer quantity;
}
