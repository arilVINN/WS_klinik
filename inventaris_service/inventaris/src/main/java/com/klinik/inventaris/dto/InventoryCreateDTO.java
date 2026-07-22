package com.klinik.inventaris.dto;

import lombok.Data;

@Data
public class InventoryCreateDTO {
    private String name;
    private Integer quantity;
    private String category;
    private Integer price;
}
