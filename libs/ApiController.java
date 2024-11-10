import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.dto.statistics.SearchDto;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.dto.statistics.response.FalseResponse;
import searchengine.dto.statistics.response.SearchResponse;
import searchengine.dto.statistics.response.TrueResponse;
import searchengine.repository.SiteRepository;
import searchengine.services.IndexingService;
import searchengine.services.SearchService;
import searchengine.services.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "API контролер поискового движка", description = "Индексация всех страниц, переиндексация отдельного сайта, " + "остановка индексации, поиск, статистика по сайтам")
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexingService indexingService;
    private final SiteRepository siteRepository;
    private final SearchService searchService;

    public ApiController(StatisticsService statisticsService, IndexingService indexingService, SiteRepository siteRepository, SearchService searchService) {

        this.statisticsService = statisticsService;
        this.indexingService = indexingService;
        this.siteRepository = siteRepository;
        this.searchService = searchService;
    }

    @GetMapping("/statistics")
    @Operation(summary = "Получение статистики по сайтам")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    @Operation(summary = "Запуск индексации всех указанных сайтов")
    public ResponseEntity<Object> startIndexing() {
        if (indexingService.indexingAll()) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FalseResponse(false, "Индексация не запущена"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stopIndexing")
    @Operation(summary = "Остановка индексации сайтов")
    public ResponseEntity<Object> stopIndexing() {
        if (indexingService.stopIndexing()) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FalseResponse(false, "Индексация не остановлена т.к." + " не запущена"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск информации")
    public ResponseEntity<Object> search(@RequestParam(name = "query", required = false, defaultValue = "") String query, @RequestParam(name = "site", required = false, defaultValue = "") String site, @RequestParam(name = "offset", required = false, defaultValue = "0") int offset, @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        if (query.isEmpty()) {
            return new ResponseEntity<>(new FalseResponse(false, "Задан пустой поисковый запрос"), HttpStatus.BAD_REQUEST);
        } else {
            List<SearchDto> searchData;
            if (!site.isEmpty()) {
                if (siteRepository.findByUrl(site) == null) {
                    return new ResponseEntity<>(new FalseResponse(false, "Указанная страница не найдена"), HttpStatus.BAD_REQUEST);
                } else {
                    searchData = searchService.siteSearch(query, site, offset, limit);
                }
            } else {
                searchData = searchService.allSiteSearch(query, offset, limit);
            }

            return new ResponseEntity<>(new SearchResponse(true, searchData.size(), searchData), HttpStatus.OK);
        }
    }

    @PostMapping("/indexPage")

    @Operation(summary = "Индексация отдельной страницы")
    public ResponseEntity<Object> indexPage(@RequestParam(name = "url") String url) {
        if (url.isEmpty()) {
            log.info("Страница не указана");
            return new ResponseEntity<>(new FalseResponse(false, "Страница не указана"), HttpStatus.BAD_REQUEST);
        } else {
            if (indexingService.urlIndexing(url) == true) {
                log.info("Страница - " + url + " - добавлена на переиндексацию");
                return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
            } else {
                log.info("Указанная страница" + "за пределами конфигурационного файла");
                return new ResponseEntity<>(new FalseResponse(false, "Указанная страница" + "за пределами конфигурационного файла"), HttpStatus.BAD_REQUEST);
            }
        }
    }
}

