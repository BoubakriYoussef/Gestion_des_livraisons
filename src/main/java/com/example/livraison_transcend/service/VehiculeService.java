package com.example.livraison_transcend.service;

import com.example.livraison_transcend.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

@Service
public class VehiculeService {

    private VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }
}
