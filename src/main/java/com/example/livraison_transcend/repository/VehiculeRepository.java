package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Client;
import com.example.livraison_transcend.entity.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Page<Vehicule> findByImmatriculeContains(String kw, Pageable pageable);

}
