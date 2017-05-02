package web.test.service;


import web.test.model.CategoryDTO;
import java.util.List;



public interface CategoryService {

    void save(String name);

    void delete(String name);

    List<CategoryDTO> findCategories();
}
