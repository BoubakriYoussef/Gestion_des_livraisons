package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Livraison;
import com.example.livraison_transcend.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    Page<Livraison> findByIntituleContains(String keyword, PageRequest of);
}
