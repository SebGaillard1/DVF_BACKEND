package com.example.DVFPROJECT.business;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue // Pour que le framework choisisse un ID unique
    private Long id;

    // TODO: Changer String en data type approprié
    private String idMutation;
    private String dateMutation;
    private String numeroDisposition;
    private String natureMutation;
    private String valeurFonciere;
    private String adresseNumero;
    private String adresseSuffixe;
    private String adresseNomVoie;
    private String adresseCodeVoie;
    private String codePostal;
    private String codeCommune;
    private String nomCommune;
    private String codeDepartement;
    private String ancienCodeCommune;
    private String ancienNomCommune;
    private String idParcelle;
    private String ancienIdParcelle;
    private String numeroVolume;
    private String lot1Numero;
    private String lot1SurfaceCarrez;
    private String lot2Numero;
    private String lot2SurfaceCarrez;
    private String lot3Numero;
    private String lot3SurfaceCarrez;
    private String lot4Numero;
    private String lot4SurfaceCarrez;
    private String lot5Numero;
    private String lot5SurfaceCarrez;
    private String nombreLots;
    private String codeTypeLocal;
    private String typeLocal;
    private String surfaceReelleBati;
    private String nombrePiecesPrincipales;
    private String codeNatureCulture;
    private String natureCulture;
    private String codeNatureCultureSpeciale;
    private String natureCultureSpeciale;
    private String surfaceTerrain;
    private String longitude;
    private String latitude;

    @Override
    public String toString() {
        return "TransactionImmobiliere{" +
                "idMutation='" + idMutation + '\'' +
                ", dateMutation='" + dateMutation + '\'' +
                ", numeroDisposition='" + numeroDisposition + '\'' +
                ", natureMutation='" + natureMutation + '\'' +
                ", valeurFonciere='" + valeurFonciere + '\'' +
                ", adresseNumero='" + adresseNumero + '\'' +
                // ... autres champs
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
