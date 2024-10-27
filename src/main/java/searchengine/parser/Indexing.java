package searchengine.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import searchengine.dto.IndexDto;
import searchengine.model.SiteEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Indexing implements IndexParser{

	@Override
	public void run(SiteEntity site) {

	}

	@Override
	public List<IndexDto> getIndexList() {
		return List.of();
	}
}
