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

    private Integer maSanPham;
    private String tenSanPham;
    private BigDecimal donGia;
    private Integer soLuong;
    public BigDecimal getThanhTien() {
        return donGia.multiply(BigDecimal.valueOf(soLuong));
    }
}
