package searchengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndexResponse {

    Long pageId;
    Long lemmaId;
    Float rank;
}
