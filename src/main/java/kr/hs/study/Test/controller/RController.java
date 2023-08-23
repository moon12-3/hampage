package kr.hs.study.Test.controller;

import kr.hs.study.Test.dto.Basket;
import kr.hs.study.Test.dto.Users;
import kr.hs.study.Test.service.HamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class RController {

    @Autowired
    HamService service;

    //@RequestMapping(method = RequestMethod.POST, path = "/login")
    @PostMapping("/login")
    String login2(@RequestParam String id, @RequestParam String pass, HttpServletRequest req) {
        Users user = service.login(id, pass);
        String message = "";
        if (user == null) {
            message = "<script>alert('아이디나 비밀번호가 맞지 않습니다.');location.href='/login';</script>";
        } else {
            message = "<script>alert('로그인 되었습니다!');location.href='/';</script>";
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            session.setMaxInactiveInterval(60 * 30);
        }

        return message;
    }

    @PostMapping("/add_basket")
    String add_basket(@RequestParam int order_cnt,
                      @RequestParam String goods_id,
                      @RequestParam int price,
                      HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("loginUser");
        System.out.println("로그인 유저 : " + user);
        if(user==null) return "<script>location.href='/login';</script>";
        Basket b = new Basket();
        b.setOrder_cnt(order_cnt);
        b.setGoods_id(goods_id);
        b.setUser_id(user.getUser_id());
        b.setPrice(order_cnt*price);
        service.basketInsert(b);
        return " <script>" +
                "if(confirm('장바구니에 추가하였습니다. 이동하시겠습니까?')) " +
                "location.href='/basket'; else {history.go(-1); location.reload();} </script>";
    }
}