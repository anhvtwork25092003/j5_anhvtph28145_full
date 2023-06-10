package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.IOrderService;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession session;

    @GetMapping
    public String hienThiDanhSachHoaDon(Model model) {
        model.addAttribute("orders", this.orderService.getHoaDonViewModels());
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            if (account.getRole() == 0) {
                // Người dùng đã đăng nhập
                model.addAttribute("orders", this.orderService.getHoaDonViewModels());
                return "/admin/invoice";
            } else if (account.getRole() == 1) {
                return "redirect:/index";
            }
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            return "redirect:/login";
        }
        return  "redirect:/login";
    }

    @GetMapping("/hoa-don-chi-tiet/{orderId}")
    public String hienThiHoaDonChiTiet(Model model,
                                       @PathVariable("orderId") Integer orderId) {
        Account account = (Account) session.getAttribute("account");
        if (account.getRole() == 0) {
            List<OrderDetail> orderDetails = this.iOrderDetailService.getOrderDetailsByOrderId(orderId);

            model.addAttribute("order", orderDetails);
            return "/admin/chi-tiet-hoa-don";
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            return "redirect:/login";
        }

    }
}
