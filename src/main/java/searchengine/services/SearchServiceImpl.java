package searchengine.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import searchengine.dto.SearchDto;
import searchengine.morphology.Morphology;
import searchengine.repository.LemmaRepository;
import searchengine.repository.PageRepository;
import searchengine.repository.SiteRepository;
import searchengine.repository.IndexRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

	private final Morphology morphology;
	private final LemmaRepository lemmaRepository;
	private final PageRepository pageRepository;
	private final IndexRepository indexRepository;
	private final SiteRepository siteRepository;

	@Override
	public List<SearchDto> allSiteSearch(String text, int offset, int limit) {
		return List.of();
	}

	@Override
	public List<SearchDto> siteSearch(String searchText, String url, int offset, int limit) {
		return List.of();
	}
}
