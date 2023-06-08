package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.DaQuy;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IDaQuyService;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;

    @Autowired
    IDaQuyService daQuyService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;


    // get date
    public static String getCurrentDate(String format) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }

    //     xem gio hang
    @GetMapping
    public String xemGioHang(Model model) {
        model.addAttribute("cart", shoppingCartService.getAll());
        model.addAttribute("total", shoppingCartService.getAmount());
        return "/user/gio-hang";
    }

    //     clear cart
    @GetMapping("/clear")
    public String ClearCart() {
        shoppingCartService.clear();
        return "redirect:/cart";
    }

    //     xoa hoan toan san pham khoi gio hang
    @GetMapping("/del/{id}")
    public String removeProducts(@PathVariable("id") Integer id) {

        shoppingCartService.remove(id);
        return "redirect:/cart";
    }

    // add cart
    @GetMapping("/add/{maSanPham}")
    public String addCart(Model model,
                          @PathVariable("maSanPham") Integer daQuyId) {
        DaQuy product = daQuyService.getOneProduct(daQuyId);
        System.out.println(product);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setId(product.getId());
            cartItem.setMaSanPham(product.getMa());
            cartItem.setTenSanPham(product.getTen());
            cartItem.setDonGia(product.getDonGia());
            cartItem.setSoLuong(1);
            shoppingCartService.add(cartItem);
        }
        return "redirect:/index";
    }


    @PostMapping("/update")
    public String updateSoLuongSanPhamTrongGio(
            @RequestParam("id") Integer id, @RequestParam("qty") int soLuong
    ) {
        shoppingCartService.update(id, soLuong);
        return "redirect:/cart";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan() {
        // laays usser
        Integer userId = 1;

        Account account = this.accountService.getAccount(userId).get();


        // getdate
        String currentDate = getCurrentDate("yyyy-MM-dd");
        Order order = Order.builder()
                .createDate(Date.valueOf(currentDate))
                .account(account)
                .build();

        // insert order
        Order order1 = orderService.save(order);
        // insert orderdetail
        Collection<CartItem> cartItems = shoppingCartService.getAll();
        for (CartItem cartItem : cartItems) {
            Integer productId = cartItem.getId();
            Integer soLuongMua = cartItem.getSoLuong();
            BigDecimal donGia = cartItem.getDonGia();

            // Sử dụng các thông tin lấy được cho các mục đích khác
            // Ví dụ: lưu vào cơ sở dữ liệu
            // ...
            OrderDetail orderDetail = OrderDetail.builder()
                    .daQuy(this.daQuyService.getOneProduct(productId))
                    .donGia(donGia)
                    .soluong(soLuongMua)
                    .order(order1)
                    .build();
            this.orderDetailService.save(orderDetail);

            // giam so luong san pham ton kho
            this.daQuyService.capNhatSoLuongton(productId,soLuongMua );

            // sau khi thanh toan- xoa gio hang
            this.shoppingCartService.clear();
        }

        return "redirect:/index";
    }
}
