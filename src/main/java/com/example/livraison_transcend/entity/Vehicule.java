package com.example.livraison_transcend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String immatricule;

    private String marque;

    private String type;

    private String capacite_de_charge;

    private String disponibilite;

    private String adr_parking_vehicule;

    @OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private Livraison livraison;
}
