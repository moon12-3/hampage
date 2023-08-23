package kr.hs.study.Test.controller;

import kr.hs.study.Test.dto.Goods;
import kr.hs.study.Test.dto.JoinBasket;
import kr.hs.study.Test.dto.Post;
import kr.hs.study.Test.dto.Users;
import kr.hs.study.Test.service.HamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    static Users check_login(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("loginUser");
        return user;
    }

    @Autowired
    HamService service;   // 상품

    @GetMapping("/shop")
    String shop(Model model, HttpServletRequest req) {
        List<Goods> list = service.goodsList();
        model.addAttribute("list", list);
        model.addAttribute("user", check_login(req));

        return "store/store";
    }

    @GetMapping("/")
    String home(Model model, HttpServletRequest req) {
        model.addAttribute("user", check_login(req));
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/sign")
    String signIn() {
        return "signin";
    }

    @PostMapping("/sign")   // 회원가입
    String signIn2(Users dto, HttpServletRequest request) {

        service.userInsert(dto);
        return "redirect:/";
    }

    @PostMapping("/find")
    String find(@RequestParam String fname, Model model) {
        List<Goods> list = service.find(fname);
        model.addAttribute("name", fname);
        model.addAttribute("list", list);
        return "find";
    }

    @GetMapping("show/{id}")
    String show(@PathVariable String id, Model model, HttpServletRequest req) {
        Goods g = service.showGoods(id);
        model.addAttribute("g", g);
        model.addAttribute("user", check_login(req));
        return "store/show";
    }

    @GetMapping("/basket")
    String basket(Model model, HttpServletRequest req) {
        Users user = check_login(req);
        List<JoinBasket> b = service.basketList(user.getUser_id());
        model.addAttribute("user", check_login(req));
        model.addAttribute("basket",b);
        System.out.println(b.get(0));
        return "store/basket";
    }
}
