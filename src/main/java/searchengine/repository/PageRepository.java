package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaEntity;
import searchengine.model.PageEntity;
import searchengine.model.SiteEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {

    long countBySiteId(SiteEntity siteId);
    Iterable<PageEntity> findBySiteId(SiteEntity site);

    @Query(value = "select p.* from Page p join words_index i on p.id = i" +
            ".page_id where i.lemma_id in :lemmas", nativeQuery = true)
    List<PageEntity> findByLemmas(@Param("lemmas") Collection<LemmaEntity> lemmaListId);

//   @Query(value = "SELECT p.* FROM Page p WHERE p.id IN :pageId AND p.site_id IN :siteId")
//   List<PageEntity> findPathByPageIdAndSiteId(@Param("pageId") PageEntity pageId, @Param("siteId") SiteEntity siteId);

//   @Query(value = "INSERT INTO Page_tamp (site_Id, path, code, content) select site_id, path, code, content from Page")
//   void copy();


}
