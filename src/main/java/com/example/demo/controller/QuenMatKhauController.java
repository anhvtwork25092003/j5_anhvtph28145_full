package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/quen-mat-khau")
public class QuenMatKhauController {


    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    IAccountService accountService;
    @Autowired
    private HttpSession session;

    // hien thi form quen mat khau- nhap email de lay lai mat khau
    @GetMapping
    public String hienThiFormQuenMatKhau() {
        return "/othes/quen-mat-khau";
    }


    // hien thi form nhap ma xac minh da duoc gui ve email
    @GetMapping("/nhap-ma")
    public String hienThiNhapMa() {
        return "/othes/nhap-ma";
    }

    // hien thi trang nhap mat khau moi
    @GetMapping("/dat-lai-mat-khau")
    public String nhapMatKhauMoi() {
        return "/othes/dat-lai-mat-khau";
    }
//
//    @PostMapping
//    public String guiCodequnMatKhau(@RequestParam("email") String email,
//                                     RedirectAttributes redirectAttributes) {
//        //         kiem tra email da dang ki tai khoan chua
//        Account account = this.accountService.findAccountByEmail(email);
//        if (account == null) {
//            return "redirect:/quen-mat-khau";
//        }
//        Random random = new Random();
//        int code = random.nextInt(900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999
//        // Gửi email chứa mã đến địa chỉ email
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(email);
//        mailMessage.setSubject("Mã xác nhận quên mật khẩu");
//        mailMessage.setText("Mã xác nhận của bạn là: " + code);
//        javaMailSender.send(mailMessage);
//        return "redirect:/quen-mat-khau/xac-nhan";
//    }

    // gui ma den email da duoc nhap
    @PostMapping("/gui-ma")
    public String kiemtraEmail(@RequestParam("email") String email) {
        // kiem tra email co tai khoan khong
        Account account = this.accountService.findAccountByEmail(email);
        if (account == null) {
            // email chua dang ki tai khoan -> chuyen ve trang nhap email
            return "redirect:/quen-mat-khau";
        } else {

            // luu accoun vao session
            session.setAttribute("account", account);
            // emmail da dang ki tai khaon-> gui code qua email, chuyen den trang nhap code
            Random random = new Random();
            int code = random.nextInt(900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999

            session.setAttribute("code", code);
            // Gửi email chứa mã đến địa chỉ email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Mã xác nhận quên mật khẩu");
            mailMessage.setText("Mã xác nhận của bạn là: " + code);
            javaMailSender.send(mailMessage);
            return "redirect:/quen-mat-khau/nhap-ma";
        }
    }

    // xac minh ma code
    @PostMapping("/xac-minh")
    public String xacMinhCode(@RequestParam("code") int userCode,
                              RedirectAttributes redirectAttributes) {
        // lay ra code dung tu session
        int code = (int) session.getAttribute("code");

        // so sanh 2 code
        if (code != userCode) {
            // neu code khong dung-> gui thong bao, quay lai trang nhap code
            redirectAttributes.addFlashAttribute("error", "Sai ma roi");
            return "redirect:/quen-mat-khau/nhap-ma";
        } else {
            // neu dung-> chuyen trang nhap mat khau
            return "redirect:/quen-mat-khau/dat-lai-mat-khau";
        }
    }

    // update mat khau
    @PostMapping("/update")
    public String update(@RequestParam("newpass") String newpass,
                         @RequestParam("repass") String repass,
                         RedirectAttributes redirectAttributes
    ) {
        // kiem tra 2 mat khau trung nhau chua
        if (newpass.equals(repass) == false){
            redirectAttributes.addFlashAttribute("error", "Sai ma roi");
            return "redirect:/quen-mat-khau/dat-lai-mat-khau";
        }else{
            Account account= (Account) session.getAttribute("account");
            Account account1 = Account.builder()
                    .id(account.getId())
                    .username(account.getUsername())
                    .fullname(account.getFullname())
                    .password(newpass)
                    .role(account.getRole())
                    .email(account.getEmail())
                    .build();
            this.accountService.addAccount(account1);
            return "redirect:/login";
        }
        }
}
