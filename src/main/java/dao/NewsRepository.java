package web.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import web.test.model.News;
import java.util.List;


public interface NewsRepository extends CrudRepository<News, Long> {
      List<News> findAll();

      News findByName(String name);

      @Query(value = "SELECT * FROM news n JOIN news_category nc ON n.id=nc.news_id WHERE nc.category_id=?1", nativeQuery = true)
      List<News> getNewsByCategory(Long category_id);

}
