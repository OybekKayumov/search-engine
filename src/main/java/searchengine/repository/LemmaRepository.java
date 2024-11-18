package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaEntity;
import searchengine.model.SiteEntity;

import java.util.List;

@Repository
public interface LemmaRepository extends JpaRepository<LemmaEntity, Long> {

	long countBySiteEntityId(SiteEntity site);

	List<LemmaEntity> findBySiteEntityId(SiteEntity siteId);

	@Query(value = "select l.* from lemma l where l.lemma in :lemmas and l" +
					".site_id = :site", nativeQuery = true)
	List<LemmaEntity> findLemmaListBySite(@Param("lemmas") List<String> lemmaList,
	                                      @Param("site") SiteEntity site);

	@Query(value = "select l* from lemma l where l.lemma = :lemma order by " +
					"frequuncy ASC", nativeQuery = true)
	List<LemmaEntity> findByLemma(@Param("lemma") String lemma);

}
