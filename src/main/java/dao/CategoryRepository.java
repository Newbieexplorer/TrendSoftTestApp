package web.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import web.test.model.Category;
import java.util.List;
import java.util.Set;



public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findAll();

    Category getByName(String name);

    @Query(value = "SELECT * FROM category c JOIN news_category nc ON c.id=nc.category_id WHERE nc.news_id=?1", nativeQuery = true)
    Set<Category> getCategoryByNews(Long news_id);
}
