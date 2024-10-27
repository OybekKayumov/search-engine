package searchengine.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "lemma", indexes = {@Index(name = "lemma_list", columnList =
				"lemma")})
@NoArgsConstructor
public class LemmaEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteEntity siteEntityId;

	private String lemma;

	private int frequency;

	@OneToMany(mappedBy = "lemma", cascade = CascadeType.ALL)
	private List<IndexEntity> index = new ArrayList<>();

	public LemmaEntity(String lemma, int frequency, SiteEntity siteEntityId) {
		this.lemma = lemma;
		this.frequency = frequency;
		this.siteEntityId = siteEntityId;
	}
}
