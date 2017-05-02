
package web.test.service;

import org.springframework.data.jpa.repository.Query;

import web.test.model.Category;
import web.test.model.News;
import web.test.model.NewsDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface NewsService {
    List<NewsDTO> findNewsByCategory(Long id);

    List<NewsDTO> findAll();

    NewsDTO getNews(Long id);

    void save(NewsDTO newsDTO);

    void delete(Long Id);

    public void addCategoryToNews(Long categoriesId, Long newsId);



}