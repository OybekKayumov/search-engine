package searchengine.services;

public interface IndexingService {

//    void startIndexing();
//
//    void stopIndexing();
//
//    void addOrUpgrade();

    boolean urlIndexing(String url);
    boolean indexingAll();
    boolean stopIndexing();
}
