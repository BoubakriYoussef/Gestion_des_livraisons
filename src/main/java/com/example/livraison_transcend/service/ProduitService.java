package com.example.livraison_transcend.service;

import com.example.livraison_transcend.repository.ProduitRepository;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {

    private ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
}
