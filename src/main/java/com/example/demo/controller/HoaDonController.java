package com.example.demo.controller;


import com.example.demo.entity.OrderDetail;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class HoaDonController {

    // hien thi danh sach hoa don
    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService iOrderDetailService;

    @GetMapping
    public String hienThiDanhSachHoaDon(Model model) {
        model.addAttribute("orders", this.orderService.getHoaDonViewModels());
        return "/admin/invoice";
    }

    @GetMapping("/hoa-don-chi-tiet/{orderId}")
    public String hienThiHoaDonChiTiet(Model model,
                                       @PathVariable("orderId") Integer orderId){


        List<OrderDetail> orderDetails = this.iOrderDetailService.getOrderDetailsByOrderId(orderId);

        model.addAttribute("order", orderDetails);
        return "/admin/chi-tiet-hoa-don";
    }
}
