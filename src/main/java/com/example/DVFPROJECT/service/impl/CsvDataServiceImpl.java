package com.example.DVFPROJECT.service.impl;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.service.CsvDataService;
import com.example.DVFPROJECT.service.TransactionService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvDataServiceImpl implements CsvDataService {

    public List<Transaction> readCsvData(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) { // Skip header

            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Transaction transaction = new Transaction();

                transaction.setIdMutation(nextRecord[0]);
                transaction.setDateMutation(nextRecord[1]);
                transaction.setNumeroDisposition(nextRecord[2]);
                transaction.setNatureMutation(nextRecord[3]);
                transaction.setValeurFonciere(nextRecord[4]);
                transaction.setAdresseNumero(nextRecord[5]);
                transaction.setAdresseSuffixe(nextRecord[6]);
                transaction.setAdresseNomVoie(nextRecord[7]);
                transaction.setAdresseCodeVoie(nextRecord[8]);
                transaction.setCodePostal(nextRecord[9]);
                transaction.setCodeCommune(nextRecord[10]);
                transaction.setNomCommune(nextRecord[11]);
                transaction.setCodeDepartement(nextRecord[12]);
                transaction.setAncienCodeCommune(nextRecord[13]);
                transaction.setAncienNomCommune(nextRecord[14]);
                transaction.setIdParcelle(nextRecord[15]);
                transaction.setAncienIdParcelle(nextRecord[16]);
                transaction.setNumeroVolume(nextRecord[17]);
                transaction.setLot1Numero(nextRecord[18]);
                transaction.setLot1SurfaceCarrez(nextRecord[19]);
                transaction.setLot2Numero(nextRecord[20]);
                transaction.setLot2SurfaceCarrez(nextRecord[21]);
                transaction.setLot3Numero(nextRecord[22]);
                transaction.setLot3SurfaceCarrez(nextRecord[23]);
                transaction.setLot4Numero(nextRecord[24]);
                transaction.setLot4SurfaceCarrez(nextRecord[25]);
                transaction.setLot5Numero(nextRecord[26]);
                transaction.setLot5SurfaceCarrez(nextRecord[27]);
                transaction.setNombreLots(nextRecord[28]);
                transaction.setCodeTypeLocal(nextRecord[29]);
                transaction.setTypeLocal(nextRecord[30]);
                transaction.setSurfaceReelleBati(nextRecord[31]);
                transaction.setNombrePiecesPrincipales(nextRecord[32]);
                transaction.setCodeNatureCulture(nextRecord[33]);
                transaction.setNatureCulture(nextRecord[34]);
                transaction.setCodeNatureCultureSpeciale(nextRecord[35]);
                transaction.setNatureCultureSpeciale(nextRecord[36]);
                transaction.setSurfaceTerrain(nextRecord[37]);
                transaction.setLongitude(nextRecord[38]);
                transaction.setLatitude(nextRecord[39]);

                transactions.add(transaction);
            }

            transactions.stream().limit(10).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
}


