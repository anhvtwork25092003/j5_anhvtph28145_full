package com.example.demo.service;

import com.example.demo.entity.DaQuy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDaQuyService {

    Page<DaQuy> getProducts(Pageable pageable);

    // get one product
    DaQuy getOneProduct(Integer id);

    String saveoOrUpdateProduct(DaQuy daQuy);

    String deleteProduct(Integer id);

    long getDaQuyCount();
}
