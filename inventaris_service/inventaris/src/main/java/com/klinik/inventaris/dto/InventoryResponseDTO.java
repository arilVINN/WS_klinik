package com.klinik.inventaris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private String category;
    private Integer price;
}
