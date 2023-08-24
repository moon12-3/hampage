package kr.hs.study.Test.mapper;

import kr.hs.study.Test.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HamMapper {
    public List<Goods> goodslist();

    public void userInsert(Users dto);  // 회원가입

    public Users login(@Param("id") String user_id, @Param("pass") String user_pass); // 로그인

    public List<Post> list();   // 모든 게시글 불러오기

    public List<Goods> find(String name);   // 상품 검색 결과 불러오기(index 사용)

    public Goods showGoods(String id);

    public void basketInsert(Basket dto);

    public void basketDelete(int order_id);

    public List<JoinBasket> basketList(String id);

    List<Map<String, Object>> getRollupResults(String userId); // RollUp 결과

    void insertPost(Post post); // post insert

    public List<Post> postList();   // 포스트 전체 불러오기

    public Post showPost(int id);   // 포스트 하나 보기

    public void updatePost(Post post);
    
    public List<Chat> chatList(int id); // 댓글 리스트

    public void insertChat(Chat chat);  // 댓글 입력

    public List<String> maxPostUser();  // 가장 많은 포스트를 작성한 유저
}
