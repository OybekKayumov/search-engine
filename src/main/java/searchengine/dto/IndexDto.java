package searchengine.dto;

import lombok.Value;

@Value
public class IndexDto {

    Long pageId;
    Long lemmaId;
    Float rank;
}
