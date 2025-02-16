package com.gss.gss_springboot.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gss.gss_springboot.models.Pack;
import com.gss.gss_springboot.repositories.PackRepository;
import com.gss.gss_springboot.services.PackService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PackServiceImpl implements PackService{
    private final PackRepository packRepository;

    // CREATE
    @Override
    public Pack createPack(Pack pack){
        return packRepository.save(pack);
    }

    // READ
    @Override
    public List<Pack> readAllPacks(){
        return packRepository.findAll();
    }
    @Override
    public Optional<Pack> readPackById(Long id){
        return packRepository.findById(id);
    }

    //UPDATE
    @Override
    public Pack updatePack(Long id, Pack pack){
        Optional<Pack> existingPack = packRepository.findById(id);
        return existingPack
                    .map(e->{
                        e.setOfferName(pack.getOfferName());
                        e.setDurationMonths(pack.getDurationMonths());
                        e.setMonthlyPrice(pack.getMonthlyPrice());
                        return packRepository.save(e);
                    }).orElseThrow(()-> new RuntimeException("Pack not found"));
    }

    // DELETE
    @Override
    public void deletePack(Long id){
        packRepository.deleteById(id);
    }
}
