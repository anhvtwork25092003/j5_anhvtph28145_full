package com.example.demo.controller;

import com.example.demo.entity.DaQuy;
import com.example.demo.service.IDaQuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/index")
public class TrangChuController {

    @Autowired
    IDaQuyService daQuyService;

//     hien thi danh sach san pham- trang chu
    @GetMapping
    public String hienThiTrangChu(Model model, @RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) String priceRange,
                                  @RequestParam(defaultValue = "1") int page
                                  ) {
        //         cat lai cuoi range de lay min max
        // phan trang
        if (page < 1) {
            page = 1;
        }
        Page<DaQuy> pageSanPham;
        Pageable pageable = PageRequest.of(page - 1, 5);
        // hien thi tat ca
        if (keyword == null && priceRange == null) {
            model.addAttribute("pageDaQuy", this.daQuyService.getProducts(pageable));
        } else if (keyword != null && priceRange != null) {
            String[] range = priceRange.split(",");
            String minPrice;
            String maxPrice;

            if (range.length == 1) {
                minPrice = range[0];
                maxPrice = "99999999999999999";
            } else {
                minPrice = range[0];
                maxPrice = range[1];
            }

            if (minPrice.isEmpty()) {
                minPrice = "0";
            }

            if (maxPrice.isEmpty()) {
                maxPrice = "99999999999999999";
            }
//             tim kiem theo ten va khoang gia
            Page<DaQuy> ketQua =
                    daQuyService.timKiemTheoTenVaKhoangGia(pageable, keyword, BigDecimal.valueOf(Long.parseLong(minPrice)), BigDecimal.valueOf(Long.parseLong(maxPrice)));
            model.addAttribute("pageDaQuy", ketQua);
        } else if (keyword != null && priceRange == null) {
//             tim kiem theo ten
            model.addAttribute("pageDaQuy", this.daQuyService.timKiemTheoTen(pageable, keyword));
        } else if (keyword == null && priceRange != null) {
            String[] range = priceRange.split(",");
            String minPrice;
            String maxPrice;

            if (range.length == 1) {
                minPrice = range[0];
                maxPrice = "99999999999999999";
            } else {
                minPrice = range[0];
                maxPrice = range[1];
            }

            if (minPrice.isEmpty()) {
                minPrice = "0";
            }

            if (maxPrice.isEmpty()) {
                maxPrice = "99999999999999999";
            }

//             tim kiem theo khoang gia
            model.addAttribute("pageDaQuy", this.daQuyService.timKiemTheoKhoangGia(pageable, BigDecimal.valueOf(Long.parseLong(minPrice)), BigDecimal.valueOf(Long.parseLong(maxPrice))));

        }
        return "othes/trang-chu";
    }
}
