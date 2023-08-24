package kr.hs.study.Test.dto;

import lombok.Data;

@Data
public class Basket {
    int order_id;
    String goods_id;
    String user_id;
    int price;
    int order_cnt;
}
