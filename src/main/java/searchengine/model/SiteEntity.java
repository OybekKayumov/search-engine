package searchengine.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "site")
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,
            columnDefinition = "enum('INDEXED', 'INDEXING', 'FAILED')")
    private Status status;

    @Column(name = "status_time", nullable = false)
    private Date statusTime;

    @Column(name = "last_error", nullable = true, columnDefinition = "TEXT")
    private String lastError;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteId", cascade = CascadeType.ALL)
    protected List<PageEntity> pageEntityList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteEntityId", cascade =
            CascadeType.ALL)
    protected List<LemmaEntity> lemmaEntityList = new ArrayList<>();
}
