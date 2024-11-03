package searchengine.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.dto.statistics.response.FalseResponse;
import searchengine.dto.statistics.response.TrueResponse;
import searchengine.repository.SiteRepository;
import searchengine.services.IndexingService;
import searchengine.services.SearchService;
import searchengine.services.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "API контролер поискового движка",
        description = "Индексация всех страниц, переиндексация отдельного сайта, " +
                "остановка индексации, поиск, статистика по сайтам")
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexingService indexingService;;
    private final SiteRepository siteRepository;
    private final SearchService searchService;

    public ApiController(StatisticsService statisticsService, IndexingService indexingService, SiteRepository siteRepository, SearchService searchService) {
      this.statisticsService = statisticsService;
	    this.indexingService = indexingService;
	    this.siteRepository = siteRepository;
	    this.searchService = searchService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    @Operation(summary = "Получение статистики по сайтам")
    public ResponseEntity<Object> startIndexing() {

        if (indexingService.indexingAll()) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FalseResponse(false, "Индексация " +
                    "не запущена"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stopIndexing")
    @Operation(summary = "Остановка индексации сайтов")
    public ResponseEntity<Object> stopIndexing() {

        if (indexingService.stopIndexing()) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FalseResponse(false, "Индексация не " +
                    "остановлена т.к." + " не запущена"),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
