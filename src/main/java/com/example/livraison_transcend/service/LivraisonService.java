package com.example.livraison_transcend.service;

import com.example.livraison_transcend.repository.LivraisonRepository;
import com.example.livraison_transcend.repository.ProduitRepository;
import org.springframework.stereotype.Service;

@Service
public class LivraisonService {

    private LivraisonRepository livraisonRepository;

    public LivraisonService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }
}
