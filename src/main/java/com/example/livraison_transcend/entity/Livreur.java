package com.example.livraison_transcend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    @OneToMany(mappedBy = "livreur")
    private List<Livraison> livraisons;
}
