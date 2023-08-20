package kr.hs.study.Test.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Post {
    int post_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date post_date;
    boolean post_ok;
    String user_id;
    String post_title;
    String post_content;
    String post_url;
}
