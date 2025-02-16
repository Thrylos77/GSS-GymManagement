package com.gss.gss_springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gss.gss_springboot.models.Pack;
import com.gss.gss_springboot.services.PackService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gss/packs")
@AllArgsConstructor
public class PackController {
    private PackService packService;

    @PostMapping("/createPack")
    public ResponseEntity<Pack> createPack(@Validated @RequestBody Pack Pack) {
        Pack createCust = packService.createPack(Pack);
        return ResponseEntity.ok(createCust);
    }
    
    @GetMapping("/readPack/{id}")
    public ResponseEntity<Pack> readPackById(@PathVariable Long id){
        Optional<Pack> userOpt = packService.readPackById(id);
        return userOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/listPacks")  // Show Packs list
    public ResponseEntity<List<Pack>> readAllPacks() {
        List<Pack> pack = packService.readAllPacks();
        return ResponseEntity.ok(pack);
    }
    
    @PutMapping("/updatePack/{id}") // Modify a pack informations
    public ResponseEntity<Pack> updatepack(@PathVariable Long id, @Validated @RequestBody Pack pack) {        
        try {
            Pack updatePack = packService.updatePack(id, pack);
            return ResponseEntity.ok(updatePack);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // return a 404 code if the pack not found
        }
    }

    @DeleteMapping("/deletePack/{id}") // Delete a pack
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();       
    }
}
