package web.test.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "NEWS")
public class News implements Serializable {
    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @GeneratedValue
    private Long id;


    private String name;


    private String body;


    private Date created;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "news_category", catalog = "", joinColumns = {
            @JoinColumn(name = "NEWS_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID",
                    nullable = false, updatable = false)})
    private Set<Category> categories;


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return
                "body='" + body + '\'' +
                        ", idNews=" + id +
                        ", name='" + name + '\'' +
                        ", created=" + created
                ;
    }

    public NewsDTO toDTO() {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setIdNews(id);
        newsDTO.setName(name);
        newsDTO.setBody(body);
        newsDTO.setCreated(created);
        newsDTO.setCategories(categories);
        return newsDTO;
    }
}

