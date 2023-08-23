package kr.hs.study.Test.service;

import kr.hs.study.Test.dto.*;
import kr.hs.study.Test.mapper.HamMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HamServiceImpl implements HamService {

    @Autowired
    HamMapper mapper;

    @Override
    public List<Goods> goodsList() {
        return mapper.goodslist();
    }

    @Override
    public void userInsert(Users dto) {
        mapper.userInsert(dto);
    }

    @Override
    public Users login(String id, String pass) {
        Users user = mapper.login(id, pass);
        if(user!=null) user.setUser_pass(null); // 정보 보안 위해 비밀번호 제거
        return user;
    }

    @Override
    public List<Goods> find(String name) {
        return mapper.find(name);
    }

    @Override
    public Goods showGoods(String id) {
        return mapper.showGoods(id);
    }

    @Override
    public void basketInsert(Basket dto) {
        mapper.basketInsert(dto);
    }

    @Override
    public List<JoinBasket> basketList(String id) {
        return mapper.basketList(id);
    }
}
