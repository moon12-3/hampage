package kr.hs.study.Test.service;

import kr.hs.study.Test.dto.Goods;
import kr.hs.study.Test.dto.Post;
import kr.hs.study.Test.dto.Users;

import java.util.List;

public interface HamService {
    List<Goods> goodsList();

    void userInsert(Users dto);

    public Users login(String id, String pass); // 로그인

    public List<Goods> find(String name);   // 검색 결과 불러오기(index 사용)

    public Goods showGoods(String id);
}
