package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.viewmodels.HoaDonViewModels;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order save(Order order);

    Optional<Order> getOrder(Integer orderId);

    List<Order> getOrderList();
    List<HoaDonViewModels> getHoaDonViewModels();

}
