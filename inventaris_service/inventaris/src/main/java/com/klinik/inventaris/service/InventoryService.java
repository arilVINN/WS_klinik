package com.klinik.inventaris.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.inventaris.repository.InventoryRepository;
import com.klinik.inventaris.mapper.InventoryMapper;
import com.klinik.inventaris.dto.InventoryResponseDTO;
import com.klinik.inventaris.dto.InventoryCreateDTO;
import com.klinik.inventaris.dto.RestockDTO;
import com.klinik.inventaris.model.Inventory;

@Service
public class InventoryService {
    @Autowired 
    private InventoryRepository repository;
    @Autowired 
    private InventoryMapper mapper;
    
    //ambil obat obatannya
    public List<InventoryResponseDTO> getAllInventory(){
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    //ambil obat tertentu
    public InventoryResponseDTO getInventoryById(Long id){
        return this.mapper.toResponseDTO(this.repository.findById(id).orElseThrow(() -> new RuntimeException("ID Obat" +id +"tidak ada!")));
    }

    //tmabah obat
    public InventoryResponseDTO createInventory(InventoryCreateDTO dto){
        Inventory inventory = this.mapper.toEntity(dto);
        Inventory savedInventory = repository.save(inventory);
        return mapper.toResponseDTO(savedInventory);
    }

    //restok
    public InventoryResponseDTO restock(Long id, RestockDTO dto){
        Inventory inventory = this.repository.findById(id).orElseThrow(() -> new RuntimeException("ID Obat" +id +"tidak ada!"));
        int newStock = inventory.getQuantity() + dto.getQuantity();
        inventory.setQuantity(newStock);

        Inventory savedInventory = repository.save(inventory);
        return mapper.toResponseDTO(savedInventory);
    }

    public InventoryResponseDTO reduceStock(Long id, RestockDTO dto){
        Inventory inventory = this.repository.findById(id).orElseThrow(() -> new RuntimeException("ID Obat " +id + "tidak ada"));
        
        if(inventory.getQuantity() < dto.getQuantity()){
            throw new RuntimeException("Jumlah stok tidak cukup");
        }
        int newStock = inventory.getQuantity() - dto.getQuantity();
        inventory.setQuantity(newStock);

        Inventory savedInventory = repository.save(inventory);
        return mapper.toResponseDTO(savedInventory);
    }
}
