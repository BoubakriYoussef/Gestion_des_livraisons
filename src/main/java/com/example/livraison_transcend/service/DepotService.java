package com.example.livraison_transcend.service;


import com.example.livraison_transcend.repository.DepotRepository;
import org.springframework.stereotype.Service;

@Service
public class DepotService {

    private DepotRepository depotRepository;

    public DepotService(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }
}
