package kr.hs.study.Test.controller;

import kr.hs.study.Test.dto.Goods;
import kr.hs.study.Test.dto.Post;
import kr.hs.study.Test.dto.Users;
import kr.hs.study.Test.service.HamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    HamService service;   // 상품

    @GetMapping("/shop")
    String shop(Model model) {
        List<Goods> list = service.goodsList();
        model.addAttribute("list", list);

        return "store/store";
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
    String show(@PathVariable String id, Model model) {
        Goods g = service.showGoods(id);
        model.addAttribute("g", g);
        return "store/show";
    }
}
