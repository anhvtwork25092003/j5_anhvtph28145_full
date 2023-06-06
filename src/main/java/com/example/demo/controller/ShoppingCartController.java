package com.example.demo.controller;


import com.example.demo.entity.CartItem;
import com.example.demo.entity.DaQuy;
import com.example.demo.service.IDaQuyService;
import com.example.demo.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;

    @Autowired
    IDaQuyService daQuyService;

    //     xem gio hang
    @GetMapping
    public String xemGioHang(Model model) {

        model.addAttribute("cart", shoppingCartService.getAll());
        model.addAttribute("total", shoppingCartService.getAmount());
        return "/user/gio-hang";
    }

    // add cart
    @GetMapping("/add/{maSanPham}")
    public String addCart(Model model,
                          @PathVariable("maSanPham") Integer daQuyId) {
        DaQuy product = daQuyService.getOneProduct(daQuyId);
        System.out.println(product);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setMaSanPham(product.getId());
            cartItem.setTenSanPham(product.getTen());
            cartItem.setDonGia(product.getDonGia());
            cartItem.setSoLuong(1);
            shoppingCartService.add(cartItem);
        }
        return "redirect:/cart";
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

    @PostMapping("/update")
    public String updateSoLuongSanPhamTrongGio(
            @RequestParam("id") Integer id, @RequestParam("qty") int soLuong
    ) {
        shoppingCartService.update(id, soLuong);
        return "redirect:/cart";
    }
}
