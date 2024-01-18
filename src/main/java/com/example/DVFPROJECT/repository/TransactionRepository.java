package com.example.DVFPROJECT.repository;

import com.example.DVFPROJECT.business.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCodeCommuneAndCodePostal(String codeCommune, String codePostal);
    @Query("SELECT t FROM Transaction t WHERE t.longitude BETWEEN :minLon AND :maxLon AND t.latitude BETWEEN :minLat AND :maxLat")
    List<Transaction> findByLocationWithin(@Param("minLon") Double minLon, @Param("maxLon") Double maxLon, @Param("minLat") Double minLat, @Param("maxLat") Double maxLat);

    // Exemple de méthode pour trouver des transactions dans un rayon
    // La requête exacte dépendra de votre base de données et de votre modèle
    @Query(value = "SELECT * FROM transaction t WHERE " + "6371000 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) *" + "cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) *" + "sin(radians(t.latitude))) < :radius", nativeQuery = true)
    List<Transaction> findTransactionsInRadius(@Param("latitude") double latitude,
                                               @Param("longitude") double longitude,
                                               @Param("radius") double radius);
}

