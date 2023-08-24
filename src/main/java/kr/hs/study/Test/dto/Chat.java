package kr.hs.study.Test.dto;

import lombok.Data;

@Data
public class Chat {
    public int chat_id;
    public String user_id;
    public int post_id;
    public String chat_content;
}
