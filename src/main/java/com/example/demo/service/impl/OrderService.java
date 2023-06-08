package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.viewmodels.HoaDonViewModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {

        return this.orderRepository.save(order);

    }

    @Override
    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getOrderList() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<HoaDonViewModels> getHoaDonViewModels() {

        return this.orderRepository.getHoaDon();
    }


}

