package searchengine.dto;

import lombok.Value;

@Value
public class PageDto {

    String url;
    String content;
    int code;
}
