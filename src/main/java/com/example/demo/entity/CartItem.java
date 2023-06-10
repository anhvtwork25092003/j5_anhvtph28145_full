package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartItem {
    private Integer id;
    private String maSanPham;
    private String tenSanPham;
    private BigDecimal donGia;
    private double mucGiamGia;
    private Integer soLuong;


    public BigDecimal getGiaSauKhiGiam() {
        BigDecimal mucGiamGiaDecimal = BigDecimal.valueOf(mucGiamGia);
        BigDecimal giamGia = donGia.multiply(mucGiamGiaDecimal);
        return donGia.subtract(giamGia);
    }


    public BigDecimal getThanhTien() {
        BigDecimal giaSauKhiGiam = getGiaSauKhiGiam();
        BigDecimal soLuongDecimal = BigDecimal.valueOf(soLuong);
        return giaSauKhiGiam.multiply(soLuongDecimal);
    }
}
