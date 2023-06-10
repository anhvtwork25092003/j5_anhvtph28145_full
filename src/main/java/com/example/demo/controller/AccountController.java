package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tai-khoan")
public class AccountController {


    @Autowired
    IAccountService accountService;

    @GetMapping("/dang-ki")
    public String dangKi(Model model
    ) {
        return "/othes/dang-ki";
    }

    @PostMapping("/dang-ki/add")
    public String addTaiKhoan(Model model,
                              @RequestParam("fullname") String fullname,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("email") String email

    ) {
        int role = 1;
        Account account = Account.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .fullname(fullname)
                .build();

        this.accountService.addAccount(account);
        // quay vè trang dang nhap
        return "redirect:/login";
    }

    // hioen thi form update
    @GetMapping("/update")
    public String formUpdate(Model model,
                             @RequestParam("id") String id
                             ) {
        Account acc = accountService.getAccount(Integer.valueOf(id)).get();
        model.addAttribute("acc",acc);
        return "/user/cap-nhat-tai-khoan";
    }

    //update
    @PostMapping("/update/{id}")
    public String updateTaiKhoan(Model model,
                                 @PathVariable("id") String id,
                                 @RequestParam("fullname") String fullname,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email,
                                 @RequestParam("role") int role

    ) {
        Account account = Account.builder()
                .id(Integer.parseInt(id))
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .fullname(fullname)
                .build();
        this.accountService.addAccount(account);
        return "redirect:/login";
    }

}
