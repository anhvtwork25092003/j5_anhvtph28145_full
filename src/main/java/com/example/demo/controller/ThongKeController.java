package com.example.demo.controller;


import com.example.demo.service.IDaQuyService;
import com.example.demo.viewmodels.HangBanChay;
import com.example.demo.viewmodels.HangTon;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {


    @Autowired
    IDaQuyService daQuyService;

    @GetMapping("")
    public String top10SanPhamBanChayNhat(Model model){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<HangBanChay> hangBanChayList = this.daQuyService.findTopSellingProducts(pageRequest);
        model.addAttribute("hangBanChayList", hangBanChayList);
        Page<HangTon> HangE = this.daQuyService.findTopHangTonNhieuNhat(pageRequest);
        model.addAttribute("HangE", HangE);
        return "/admin/thong-ke-bieu-do";
    }

//    @GetMapping("/top-hang-ton-kho")
//    public String topHangE(Model model){
//        PageRequest pageRequest = PageRequest.of(0, 10);
//        Page<HangTon> HangE = this.daQuyService.findTopHangTonNhieuNhat(pageRequest);
//        model.addAttribute("HangE", HangE);
//        return "/admin/hang-e";
//    }
}
