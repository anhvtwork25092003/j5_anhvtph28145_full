package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doi-mat-khau")
public class DoiMatKhauConTroller {

    @Autowired
    IAccountService accountService;

    @GetMapping
    public String getForm() {

        // check xem da dang nhao chua


        return "/user/doi-mat-khau";
    }

    // doi mat khau
    @PostMapping("")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("username") String username,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {
        // Xử lý logic đổi mật khẩu ở đây
        // Kiểm tra mật khẩu cũ và xác nhận mật khẩu mới
        // Nếu hợp lệ, thực hiện thay đổi mật khẩu
        // Nếu không hợp lệ, hiển thị thông báo lỗi

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp");
            // tro lai  trang doi mat khau
            System.out.println("loi khong khop");
            return "redirect:/doi-mat-khau";
        } else {
            // Thực hiện thay đổi mật khẩu ở đây
            // kiem tra useer name va mat khau co trung hop khong
            Account account = this.accountService.findByUsernameAndPassword(username, oldPassword);
            if (account == null) {
                model.addAttribute("error", "Sai tai Khoan hoac mat khau");
                System.out.println("loi sai tai khoan  hoac mat khau");
                return "redirect:/doi-mat-khau";
            } else {
                // thuc hien doi mat khau
                Account account1 = Account.builder()
                        .id(account.getId())
                        .username(account.getUsername())
                        .password(newPassword)
                        .role(account.getRole())
                        .fullname(account.getFullname())
                        .email(account.getEmail())
                        .build();
                this.accountService.addAccount(account1);
                model.addAttribute("success", "Đổi mật khẩu thành công");
            }
        }

        return "redirect:/login";
    }
}
