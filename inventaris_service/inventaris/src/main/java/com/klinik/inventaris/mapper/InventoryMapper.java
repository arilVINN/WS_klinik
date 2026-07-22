package com.klinik.inventaris.mapper;

import org.springframework.stereotype.Component;

import com.klinik.inventaris.dto.InventoryCreateDTO;
import com.klinik.inventaris.dto.InventoryResponseDTO;
import com.klinik.inventaris.model.Inventory;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryCreateDTO dto) {
        return new Inventory(null, dto.getName(), dto.getQuantity(), dto.getCategory(), dto.getPrice());
    }

    public InventoryResponseDTO toResponseDTO(Inventory inventory) {
        return new InventoryResponseDTO(inventory.getId(), inventory.getName(), inventory.getQuantity(),
                inventory.getCategory(), inventory.getPrice());
    }
}
