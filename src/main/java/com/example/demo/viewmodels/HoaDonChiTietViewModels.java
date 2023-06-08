package com.example.demo.viewmodels;

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
@Builder
@ToString
public class HoaDonChiTietViewModels {
    private int id;
    private int orderId;
    private int ten;// ten form daQuy
    private BigDecimal donGia;
    private int soLuongMua;
    private BigDecimal thanhTien;

    public BigDecimal getThanhTien() {
        return donGia.multiply(BigDecimal.valueOf(soLuongMua));
    }

}
