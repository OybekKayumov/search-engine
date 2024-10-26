package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaEntity;
import searchengine.model.SiteEntity;

import java.util.List;

@Repository
public interface LemmaRepository extends JpaRepository<LemmaEntity, Long> {

	long countBySiteEntityId(SiteEntity site);

	List<LemmaEntity> findBySiteEntityId(SiteEntity siteId);

}
