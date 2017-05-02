package web.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.test.dao.CategoryRepository;
import web.test.model.Category;
import web.test.model.CategoryDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    @Override
    public void delete(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDTO> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Category category : categories) {
            categoriesDTO.add(category.toDTO());
        }
        return categoriesDTO;
    }
}
