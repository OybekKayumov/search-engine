package searchengine.parser;

import org.springframework.stereotype.Component;
import searchengine.dto.IndexDto;
import searchengine.model.SiteEntity;

import java.util.List;

//@Component
public interface IndexParser {

    void run(SiteEntity site);

    List<IndexDto> getIndexList();
}
