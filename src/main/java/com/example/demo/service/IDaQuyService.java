package com.example.demo.service;

import com.example.demo.entity.DaQuy;
import com.example.demo.viewmodels.HangBanChay;
import com.example.demo.viewmodels.HangTon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface IDaQuyService {

    Page<DaQuy> getProducts(Pageable pageable);

    public Page<DaQuy> timKiemTheoTenVaKhoangGia(Pageable pageable, String keyword, BigDecimal minPrice, BigDecimal maxPrice);

    public Page<DaQuy> timKiemTheoTen(Pageable pageable, String keyword);

    public Page<DaQuy> timKiemTheoKhoangGia(Pageable pageable,BigDecimal minPrice, BigDecimal maxPrice);

    // get one product
    DaQuy getOneProduct(Integer id);

    String saveoOrUpdateProduct(DaQuy daQuy);

    String deleteProduct(Integer id);

    long getDaQuyCount();

    void capNhatSoLuongton(Integer id, int quantity);

    Page<HangBanChay> findTopSellingProducts(Pageable pageable);
    Page<HangTon> findTopHangTonNhieuNhat(Pageable pageable);
}
