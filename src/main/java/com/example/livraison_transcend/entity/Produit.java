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

public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String poids;

    private double[] dimensions;
    private String description;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandes;
    @ManyToOne
    @JoinColumn(name = "depot_id")  // Nom de la colonne de jointure dans la table Produit
    private Depot depot;

    public String getNom() {
        return nom;
    }


}
