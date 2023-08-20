package kr.hs.study.Test.mapper;

import kr.hs.study.Test.dto.Goods;
import kr.hs.study.Test.dto.Post;
import kr.hs.study.Test.dto.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HamMapper {
    public List<Goods> goodslist();

    public List<Post> postList();   // 포스트 전체 불러오기

    public void userInsert(Users dto);  // 회원가입

    public Users login(@Param("id") String user_id, @Param("pass") String user_pass); // 로그인

    public List<Post> list();   // 모든 게시글 불러오기

    public List<Goods> find(String name);   // 상품 검색 결과 불러오기(index 사용)

    public Goods showGoods(String id);
}
