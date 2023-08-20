package kr.hs.study.Test.dto;

import lombok.Data;

@Data
public class Goods {
    String goods_id;
    String goods_name;
    int price;
    String content;
    String goods_url;
    String category;
}
