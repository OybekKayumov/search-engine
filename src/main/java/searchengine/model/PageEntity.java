package searchengine.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "page", indexes = {@Index(name = "path_list", columnList = "path")})
public class PageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "path", nullable = false, columnDefinition = "VARCHAR(515)")
    private String path;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
//    private SiteEntity siteId;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<IndexEntity> index = new ArrayList<>();
}
