package com.example.demo.service.impl;

import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository iOrderDetailRepository;


    @Override
    public String save(OrderDetail order) {
        if (this.iOrderDetailRepository.save(order) != null) {
            return "Thanh cong!";
        } else {
            return "That Bai!";
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        List<OrderDetail> orderDetail = iOrderDetailRepository.getOrderDetailsByOrderId(orderId);
        return orderDetail;
    }


    ;
}
