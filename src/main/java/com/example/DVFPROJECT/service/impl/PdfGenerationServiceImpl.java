package com.example.DVFPROJECT.service.impl;

import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 64,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 24,
            Font.BOLD);
    @Override
    public byte[] generatePdfReport(java.util.List<TransactionDTO> transactions) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A2.rotate());
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document, transactions);
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Rapport des transactions");
        document.addSubject("Transactions immobilières effectuées");
        document.addKeywords("Java, PDF, Transactions");
        document.addAuthor("Matteo, Sébastien");
        document.addCreator("Matteo, Sébastien");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Rapport des transactions immobilières", catFont));

        addEmptyLine(preface, 1);

        preface.add(new Paragraph(
                "Rapport généré le: " + new Date(),
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Ce document contient les transactions immobilières effectuées dans le rayon que vous avez choisi.",
                smallBold));

        addEmptyLine(preface, 3);

        document.add(preface);
    }

    private void addContent(Document document, java.util.List<TransactionDTO> transactions) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{4, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2});
        table.setWidthPercentage(100);

        addTableHeader(table);

        for (TransactionDTO transaction : transactions) {
            addRow(table, transaction);
        }

        document.add(table);
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Nom Voie Adresse", "Numéro adresse", "Suffixe adresse",
                        "Code Voie Adresse", "Code Postal", "Nom Commune", "Code Departement",
                        "Valeur Foncière", "id Mutation", "Date Mutation", "Nature Mutation",
                        "id Parcelle")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRow(PdfPTable table, TransactionDTO transaction) {
        table.addCell(transaction.getAdresseNomVoie());
        table.addCell(transaction.getAdresseNumero());
        table.addCell(transaction.getAdresseSuffixe());
        table.addCell(transaction.getAdresseCodeVoie());
        table.addCell(transaction.getCodePostal());
        table.addCell(transaction.getNomCommune());
        table.addCell(transaction.getCodeDepartement());
        table.addCell(transaction.getValeurFonciere());
        table.addCell(transaction.getIdMutation());
        table.addCell(transaction.getDateMutation());
        table.addCell(transaction.getNatureMutation());
        table.addCell(transaction.getIdParcelle());
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}