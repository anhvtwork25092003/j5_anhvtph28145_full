package com.example.demo.service;

import com.example.demo.entity.DaQuy;
import com.example.demo.repository.IDaQuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DaQuyService implements IDaQuyService {
    @Autowired
    IDaQuyRepository daQuyRepository;

    @Override
    public Page<DaQuy> getProducts(Pageable pageable) {
        return (Page<DaQuy>) this.daQuyRepository.findAll(pageable);
    }

    @Override
    public Page<DaQuy> timKiemTheoTenVaKhoangGia(Pageable pageable, String keyword, BigDecimal minPrice, BigDecimal maxPrice) {
        return this.daQuyRepository.findByTenContainingIgnoreCaseAndDonGiaBetween(pageable, keyword, minPrice, maxPrice);
    }

    @Override
    public Page<DaQuy> timKiemTheoTen(Pageable pageable, String keyword) {
        return this.daQuyRepository.findByTenContainingIgnoreCase(pageable, keyword);
    }

    @Override
    public Page<DaQuy> timKiemTheoKhoangGia(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice) {
        return daQuyRepository.findByDonGiaBetween( pageable,minPrice, maxPrice);
    }

    @Override
    public DaQuy getOneProduct(Integer id) {
        Optional<DaQuy> productOptional = daQuyRepository.findById(Integer.valueOf(id));
        return productOptional.orElse(null);
    }

    @Override
    public String saveoOrUpdateProduct(DaQuy daQuy) {
        if (this.daQuyRepository.save(daQuy) != null) {
            return "Thanh cong!";
        } else {
            return "That Bai!";
        }
    }

    @Override
    public String deleteProduct(Integer id) {
        try {
            this.daQuyRepository.deleteById(Integer.valueOf(id));
            return "Thanh cong!";
        } catch (Exception e) {
            return "That Bai!";
        }
    }

    @Override
    public long getDaQuyCount() {
        return this.daQuyRepository.count();
    }
}
