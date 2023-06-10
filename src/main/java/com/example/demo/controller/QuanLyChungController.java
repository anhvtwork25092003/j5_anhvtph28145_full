package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly-chung")
public class QuanLyChungController {

    @Autowired
    IAccountService accountService;
    @Autowired
    private HttpSession session;

    @GetMapping()
    public String getDanhSachQuanLy() {
        // check xem dang nhap dung role = 0 khong
        Account account = (Account) session.getAttribute("account");

        if (account == null ) {
            return "redirect:/login";
        } else if(account.getRole() == 1) {
            return "redirect:/index";
        }else{
            return "/admin/quan-ly";
        }
    }
}
