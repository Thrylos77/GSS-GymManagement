package com.gss.gss_springboot.services;

import java.util.List;
import java.util.Optional;

import com.gss.gss_springboot.models.Pack;

public interface PackService {
    Pack createPack(Pack pack);
    Optional<Pack> readPackById(Long id);
    List<Pack> readAllPacks();
    Pack updatePack(Long id, Pack pack);
    void deletePack(Long id);
}
