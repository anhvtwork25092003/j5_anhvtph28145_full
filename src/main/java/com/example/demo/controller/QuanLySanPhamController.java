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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/quan-ly")
public class QuanLySanPhamController {

    @Autowired
    IDaQuyService daQuyService;

    // xoa san pham
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        this.daQuyService.deleteProduct(Integer.parseInt(id));
        return "redirect:/quan-ly/view-all";
    }

    // trang chu
    @GetMapping("/view-all")
    public String getAllProduct(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String priceRange,
                                @RequestParam(defaultValue = "1") int page,
                                Model model
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

        return "QuanLyDaQuy";
    }

    //     detail
    @GetMapping("/detail/{id}")
    public String detailProduct(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("product", this.daQuyService.getOneProduct(Integer.valueOf(id)));
        return "QuanLyDaQuy";
    }

    //    tim kiem
    @GetMapping("/tim-kiem")
    public String timKiem(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String priceRange,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        //         cat lai cuoi range de lay min max
        String[] range = priceRange.split(",");
        //             phan trang
        Pageable pageable = PageRequest.of(page - 1, 5);
        if (page < 1) page = 1;
        if (keyword != null && priceRange != null) {
            // Gọi hàm tìm kiếm theo tên và khoảng giá
            Page<DaQuy> ketQua =
                    daQuyService.timKiemTheoTenVaKhoangGia(pageable, keyword, BigDecimal.valueOf(Long.parseLong(range[0])), BigDecimal.valueOf(Long.parseLong(range[1])));
            model.addAttribute("pageDaQuy", ketQua);
            return "QuanLyDaQuy";
        } else if (keyword != null) {
            // Gọi hàm tìm kiếm theo tên
            model.addAttribute("pageDaQuy", this.daQuyService.timKiemTheoTen(pageable, keyword));
            return "QuanLyDaQuy";
        } else if (priceRange != null) {
            // Gọi hàm tìm kiếm theo khoảng
            model.addAttribute("pageDaQuy", this.daQuyService.timKiemTheoKhoangGia(pageable, BigDecimal.valueOf(Long.parseLong(range[0])), BigDecimal.valueOf(Long.parseLong(range[1]))));
            return "QuanLyDaQuy";
        }
        return "redirect:/quan-ly/view-all";
    }

    // them moi san pham
    @PostMapping("/add")
    public String addProduct(Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(name = "maAdd") String ma,
                             @RequestParam(name = "tenAdd") String ten,
                             @RequestParam(name = "soLuongAdd") String soLuong,
                             @RequestParam(name = "donGiaAdd") String donGia,
                             @RequestParam(name = "trongLuongAdd") String trongLuong,
                             @RequestParam(name = "moTaAdd") String moTa
    ) {
        if (ten.trim().length() == 0 || soLuong.trim().length() == 0
                || donGia.trim().length() == 0 || trongLuong.trim().length() == 0 || moTa.trim().length() == 0) {
            redirectAttributes.addFlashAttribute("blankError", "Khong de trong thong tin!");
            return "redirect:/quan-ly/view-all";
        }
        BigDecimal giaTri = new BigDecimal(donGia);
        DaQuy daQuy = DaQuy.builder()
                .ten(ten)
                .soLuong(Integer.valueOf(soLuong))
                .donGia(giaTri)
                .trongLuong(Float.valueOf(trongLuong))
                .moTa(moTa)
                .build();

        this.daQuyService.saveoOrUpdateProduct(daQuy);
        return "redirect:/quan-ly/view-all";
    }

    //     cap nhat ssn pham
// them moi san pham
    @PostMapping("/update")
    public String updateProduct(Model model,
                                RedirectAttributes redirectAttributes,

                                @RequestParam(name = "id") String id,
                                @RequestParam(name = "ma") String ma,
                                @RequestParam(name = "ten") String ten,
                                @RequestParam(name = "soLuong") String soLuong,
                                @RequestParam(name = "donGia") String donGia,
                                @RequestParam(name = "trongLuong") String trongLuong,
                                @RequestParam(name = "moTa") String moTa
    ) {
        if (ten.trim().length() == 0 || soLuong.trim().length() == 0
                || donGia.trim().length() == 0 || trongLuong.trim().length() == 0 || moTa.trim().length() == 0) {
            redirectAttributes.addFlashAttribute("blankError", "Khong de trong thong tin!");
            return "redirect:/quan-ly/view-all";
        }
        BigDecimal giaTri = new BigDecimal(donGia);
        DaQuy daQuy = DaQuy.builder()
                .id(Integer.valueOf(id))
                .ma(ma)
                .ten(ten)
                .soLuong(Integer.valueOf(soLuong))
                .donGia(giaTri)
                .trongLuong(Float.valueOf(trongLuong))
                .moTa(moTa)
                .build();

        this.daQuyService.saveoOrUpdateProduct(daQuy);
        return "redirect:/quan-ly/view-all";
    }
}
