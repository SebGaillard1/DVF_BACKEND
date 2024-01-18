package com.example.DVFPROJECT.repository;

import com.example.DVFPROJECT.business.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transaction t WHERE " + "6371000 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) *" + "cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) *" + "sin(radians(t.latitude))) < :radius", nativeQuery = true)
    List<Transaction> findTransactionsInRadius(@Param("latitude") double latitude,
                                               @Param("longitude") double longitude,
                                               @Param("radius") double radius);
}

