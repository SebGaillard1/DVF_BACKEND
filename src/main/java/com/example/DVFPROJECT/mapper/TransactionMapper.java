package com.example.DVFPROJECT.mapper;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.dto.TransactionDTO;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDTO toDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setIdMutation(transaction.getIdMutation());
        transactionDTO.setDateMutation(transaction.getDateMutation());
        transactionDTO.setNumeroDisposition(transaction.getNumeroDisposition());
        transactionDTO.setNatureMutation(transaction.getNatureMutation());
        transactionDTO.setValeurFonciere(transaction.getValeurFonciere());
        transactionDTO.setAdresseNumero(transaction.getAdresseNumero());
        transactionDTO.setAdresseSuffixe(transaction.getAdresseSuffixe());
        transactionDTO.setAdresseNomVoie(transaction.getAdresseNomVoie());
        transactionDTO.setAdresseCodeVoie(transaction.getAdresseCodeVoie());
        transactionDTO.setCodePostal(transaction.getCodePostal());
        transactionDTO.setCodeCommune(transaction.getCodeCommune());
        transactionDTO.setNomCommune(transaction.getNomCommune());
        transactionDTO.setCodeDepartement(transaction.getCodeDepartement());
        transactionDTO.setAncienCodeCommune(transaction.getAncienCodeCommune());
        transactionDTO.setAncienNomCommune(transaction.getAncienNomCommune());
        transactionDTO.setIdParcelle(transaction.getIdParcelle());


        return transactionDTO;
    }

}
