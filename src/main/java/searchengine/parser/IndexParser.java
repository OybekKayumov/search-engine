package searchengine.parser;

import searchengine.dto.IndexDto;
import searchengine.model.SiteEntity;

import java.util.List;

public interface IndexParser {

    void run(SiteEntity site);

    List<IndexDto> getIndexList();
}
