package com.example.DVFPROJECT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
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

    // Getters et setters
}