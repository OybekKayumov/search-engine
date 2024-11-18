package searchengine.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import searchengine.dto.LemmaDto;
import searchengine.model.SiteEntity;

import java.util.List;

//@Component
public interface LemmaParser {

	void run(SiteEntity site);
	List<LemmaDto> getLemmaDtoList();
}
