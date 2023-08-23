package kr.hs.study.Test.dto;

import lombok.Data;

@Data
public class JoinBasket {
    int order_id;
    String goods_url;
    String goods_name;
    String price;
    String category;
    char order_ok;
}
