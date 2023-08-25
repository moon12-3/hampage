package kr.hs.study.Test.controller;

import kr.hs.study.Test.dto.*;
import kr.hs.study.Test.service.HamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        List<Post> p = service.postList();
        model.addAttribute("post", p);
        model.addAttribute("max_user", service.maxPostUser());
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
    String find(@RequestParam String fname, Model model, HttpServletRequest req) {
        List<Goods> list = service.find(fname);
        model.addAttribute("name", fname);
        model.addAttribute("list", list);
        model.addAttribute("user", check_login(req));
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
        if(b.size()==0) b = null;
        model.addAttribute("user", check_login(req));
        model.addAttribute("basket",b);
        return "store/basket";
    }

    @GetMapping("/basket/delete/{id}")
    String b_delete(@PathVariable int id) {
        service.basketDelete(id);
        return "redirect:/basket";
    }

    @GetMapping("/buy")
    String buy(Model model, HttpServletRequest req) {
        List<Map<String, Object>> rollupResults = service.getRollupResults(check_login(req).getUser_id());
        System.out.println(rollupResults.get(0));
        model.addAttribute("rollupResults", rollupResults);
        return "store/buy";
    }

    @GetMapping("/write")
    String write(HttpServletRequest req) {
        if(check_login(req)==null) return "redirect:/login";
        return "write";
    }

    @PostMapping("/write")
    String write2(@RequestParam String post_title, @RequestParam String post_content, HttpServletRequest req) {
        Post p = new Post();
        p.setUser_id(check_login(req).getUser_id());
        p.setPost_title(post_title);
        p.setPost_content(post_content);
        p.setPost_date(new Date());
        service.insertPost(p);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    String view(@PathVariable int id, HttpServletRequest req, Model model) {
        Users user = check_login(req);
        if(user==null) return "redirect:/login";
        model.addAttribute("userid", user.getUser_id());
        Post post = service.showPost(id);
        model.addAttribute("view", post);
        return "view";
    }

    @GetMapping("/view/update/{id}")
    String postUpdate(@PathVariable int id, Model model) {
        Post post = service.showPost(id);
        model.addAttribute("post", post);
        return "update";
    }

    @GetMapping("/view/delete/{id}")
    String postDelete(@PathVariable int id) {
        service.deletePost(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    String postUpdate2(@RequestParam String post_title,
                       @RequestParam String post_content,
                       @RequestParam int post_id) {
        Post post = service.showPost(post_id);
        post.setPost_content(post_content);
        post.setPost_title(post_title);
        service.updatePost(post);
        return "redirect:/";
    }

    @GetMapping("/chat/{id}")
    String chat(@PathVariable int id, Model model) {
        model.addAttribute("post_id", id);
        List<Chat> chats = service.chatList(id);
        model.addAttribute("chats", chats);
        return "chat";
    }

    @PostMapping("/chat")
    String chat2(@RequestParam int post_id, @RequestParam String content, HttpServletRequest req) {
        Users user = check_login(req);
        Chat chat = new Chat();
        chat.setPost_id(post_id);
        chat.setUser_id(user.getUser_id());
        chat.setChat_content(content);
        service.insertChat(chat);
        return "redirect:/chat/"+post_id;
    }
}
