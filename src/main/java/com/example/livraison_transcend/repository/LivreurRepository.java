package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Client;
import com.example.livraison_transcend.entity.Livreur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    Page<Livreur> findByUsernameContains(String kw, Pageable pageable);
}
