package com.example.demo.repository;

import com.example.demo.entity.OrderDetail;
import com.example.demo.viewmodels.HoaDonChiTietViewModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId = :orderId")
    List<OrderDetail> getOrderDetailsByOrderId(@Param("orderId") int orderId);
}
