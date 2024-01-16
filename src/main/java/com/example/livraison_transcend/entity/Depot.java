package com.example.livraison_transcend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Depot{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private Integer capacite_stockage;

    //"En service" : Le dépôt est opérationnel et prêt à recevoir des marchandises.
    //"Hors service temporairement" : Le dépôt est temporairement indisponible, par exemple en raison de travaux de maintenance.
    //"Hors service définitivement" : Le dépôt n'est plus opérationnel de manière permanente.
    //"En attente d'approvisionnement" : Le dépôt est en attente de nouvelles marchandises.
    //"Capacité maximale atteinte" : Le dépôt a atteint sa capacité maximale de stockage.
    private String statut;


    @OneToMany(mappedBy = "depot")
    private List<Produit> produits;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduit(Produit produit) {
        if (produits == null) {
            produits = new ArrayList<>();
        }

        // Vérifier si le produit n'est pas déjà dans la liste
        if (!produits.contains(produit)) {
            produits.add(produit);

            // Assurer la cohérence en affectant le dépôt au produit
            produit.setDepot(this);
        }
    }
}
