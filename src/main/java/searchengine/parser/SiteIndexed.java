package searchengine.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import searchengine.config.SitesList;
import searchengine.model.SiteEntity;
import searchengine.model.Status;
import searchengine.repository.IndexRepository;
import searchengine.repository.LemmaRepository;
import searchengine.repository.PageRepository;
import searchengine.repository.SiteRepository;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class SiteIndexed  implements Runnable{

    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private final PageRepository pageRepository;
    private final SiteRepository siteRepository;
    private final LemmaRepository lemmasRepository;
    private final IndexRepository indexRepository;

    private final LemmaParser lemmaParser;
    private final IndexParser indexParser;
    private final String url;
    private final SitesList sitesList;

    @Override
    public void run() {
        if (siteRepository.findByUrl(url) != null) {
            log.info("Начато удаление данных сайта: " + url);
            deleteDataFromSite();
        }
    }

    private void deleteDataFromSite() {
        SiteEntity site = new SiteEntity();
        site.setUrl(url);
        site.setName(getName());
        site.setStatus(Status.INDEXING);
        site.setStatusTime(new Date());
        siteRepository.flush();
        siteRepository.save(site);

    }
}
