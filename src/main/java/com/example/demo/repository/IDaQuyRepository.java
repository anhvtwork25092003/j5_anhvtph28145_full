package com.example.demo.repository;

import com.example.demo.entity.DaQuy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface IDaQuyRepository extends JpaRepository<DaQuy, Integer> {
    Page<DaQuy> findByTenContainingIgnoreCaseAndDonGiaBetween(Pageable pageable, String keyword, BigDecimal minPrice, BigDecimal maxPrice);

    Page<DaQuy> findByTenContainingIgnoreCase(Pageable pageable, String keyword);

    Page<DaQuy> findByDonGiaBetween(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);
}
