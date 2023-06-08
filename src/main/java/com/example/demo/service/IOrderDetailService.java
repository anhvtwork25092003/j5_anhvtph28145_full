package com.example.demo.service;

import com.example.demo.entity.OrderDetail;
import com.example.demo.viewmodels.HoaDonChiTietViewModels;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    String save(OrderDetail order);

    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);
}
