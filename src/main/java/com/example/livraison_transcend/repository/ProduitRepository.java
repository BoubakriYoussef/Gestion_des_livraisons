package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Page<Produit> findByNomContains(String kw, Pageable pageable);
}
