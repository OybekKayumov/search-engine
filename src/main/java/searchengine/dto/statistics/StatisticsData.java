package searchengine.dto.statistics;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Value
public class StatisticsData {
    private TotalStatistics total;
    private List<DetailedStatisticsItem> detailed;
}
