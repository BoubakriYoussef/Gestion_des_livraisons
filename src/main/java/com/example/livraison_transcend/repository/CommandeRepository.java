package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Page<Commande> findByIntituleCommandeContains(String keyword, PageRequest of);
}
