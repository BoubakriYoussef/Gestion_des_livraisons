package com.example.livraison_transcend.service;

import com.example.livraison_transcend.repository.CommandeRepository;
import org.springframework.stereotype.Service;


@Service
public class CommandeService {

    private CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }
}
