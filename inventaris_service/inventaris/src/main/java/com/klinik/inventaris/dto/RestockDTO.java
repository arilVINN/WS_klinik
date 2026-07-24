package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RestockRequest", description = "Payload untuk menambah atau mengurangi stok inventaris")
public class RestockDTO {
    @Schema(description = "Jumlah perubahan stok", example = "5")
    private Integer quantity;
}
