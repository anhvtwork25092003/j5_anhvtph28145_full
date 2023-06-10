package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    IAccountService accountService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public String hienThiFormLogin() {
        return "/othes/login-form";
    }

    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Account account = accountService.findByUsernameAndPassword(username, password);
        if (account != null) {
            // Đăng nhập thành công
            session.setAttribute("account", account);
            if (account.getRole() == 0) {
                //  chuyen den trang voi quyen quan ly
                return "redirect:/index";
            } else {
                // chuyen den trang voi quyen khach hang
                return "redirect:/index";
            }
        } else {
            // Đăng nhập thất bại
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa toàn bộ thông tin lưu trữ trong session
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
    }
}
