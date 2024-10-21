package searchengine.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "page", indexes = @Index(name = "pathIndex", columnList = "path", unique = true))
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "path", nullable = false, unique = true, columnDefinition = "TEXT")
    private String path;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
    private Site siteId;
}
