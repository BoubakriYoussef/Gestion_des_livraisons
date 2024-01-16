package com.example.livraison_transcend.repository;

import com.example.livraison_transcend.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findByNomContains(String kw, Pageable pageable);
}
