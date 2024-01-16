package com.example.livraison_transcend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intitule;

    private Date date_prevue_livraison;

    private LocalTime heure_livraison;

    private String statut_livraison;

    @OneToOne
    @JoinColumn(name = "id_commande") // Nom de la colonne de jointure dans la table Livraison
    private Commande commande;

    @OneToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;



}
