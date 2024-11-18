package searchengine.dto.statistics;

import lombok.Data;
import lombok.Value;

@Data
//!@Value
public class DetailedStatisticsItem {
    private String url;
    private String name;
    private String status;
    private long statusTime;
    private String error;
    private int pages;
    private int lemmas;

}
