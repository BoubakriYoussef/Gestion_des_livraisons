package com.example.livraison_transcend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intituleCommande;

    private Date date;

    private String adresse_livraison;

    //"En attente" : La commande a été créée mais n'a pas encore été traitée.
    //"En cours de préparation" : La commande est en cours de préparation.
    //"Prête pour la livraison" : La commande est prête à être livrée.
    //"Livraison en cours" : La commande est en cours de livraison.
    //"Livrée" : La commande a été livrée avec succès.

    private String statut_commande;

    @ManyToMany
    private List<Produit> produits;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "commande")
    private Livraison livraison;


    public List<Produit> getProduits() {
        return produits;
    }

    public List<Client> getClient() {
        return (List<Client>) client;
    }

   
}
