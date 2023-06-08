package com.example.demo.repository;

import com.example.demo.entity.DaQuy;
import com.example.demo.viewmodels.HangBanChay;
import com.example.demo.viewmodels.HangTon;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IDaQuyRepository extends JpaRepository<DaQuy, Integer> {
    Page<DaQuy> findByTenContainingIgnoreCaseAndDonGiaBetween(Pageable pageable, String keyword, BigDecimal minPrice, BigDecimal maxPrice);

    Page<DaQuy> findByTenContainingIgnoreCase(Pageable pageable, String keyword);

    Page<DaQuy> findByDonGiaBetween(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);

    @Modifying
    @Transactional
    @Query("UPDATE DaQuy d SET d.soLuong = d.soLuong - :quantity WHERE d.id = :id")
    void updateInventory(@Param("id") Integer id, @Param("quantity") int quantity);


    @Query("SELECT new com.example.demo.viewmodels.HangBanChay(od.daQuy.id, od.daQuy.ten, SUM(od.soluong)) FROM OrderDetail od GROUP BY od.daQuy.id, od.daQuy.ten ORDER BY SUM(od.soluong) DESC")
    Page<HangBanChay> findTopSellingProducts(Pageable pageable);

    @Query("SELECT new com.example.demo.viewmodels.HangTon(dq.id, dq.ten, dq.soLuong) FROM DaQuy dq ORDER BY dq.soLuong DESC")
    Page<HangTon> findTopHangTonNhieuNhat(Pageable pageable);
}
