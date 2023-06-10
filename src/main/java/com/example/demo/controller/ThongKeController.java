package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IDaQuyService;
import com.example.demo.viewmodels.HangBanChay;
import com.example.demo.viewmodels.HangTon;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    IAccountService accountService;
    @Autowired
    IDaQuyService daQuyService;
    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String top10SanPhamBanChayNhat(Model model) {

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            return "redirect:/login";
        } else {
            if (account.getRole() == 0) {
                // Người dùng đã đăng nhập
                PageRequest pageRequest = PageRequest.of(0, 10);
                Page<HangBanChay> hangBanChayList = this.daQuyService.findTopSellingProducts(pageRequest);
                model.addAttribute("hangBanChayList", hangBanChayList);
                Page<HangTon> HangE = this.daQuyService.findTopHangTonNhieuNhat(pageRequest);
                model.addAttribute("HangE", HangE);
                return "/admin/thong-ke-bieu-do";
            } else if (account.getRole() == 1) {
                return "redirect:/index";

            }
        }
        return "redirect:/login";
    }


}
