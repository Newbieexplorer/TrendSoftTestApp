package web.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import web.test.dao.CategoryRepository;
import web.test.dao.NewsRepository;
import web.test.model.Category;
import web.test.model.News;
import web.test.model.NewsDTO;

import javax.transaction.Transactional;
import java.util.*;


@Component("NewsService")
@Transactional
public class NewsServiceImpl implements NewsService {


    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, CategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<NewsDTO> findNewsByCategory(Long id) {

        List<News> news = newsRepository.getNewsByCategory(id);
        List<NewsDTO> newsDTO = new ArrayList<>();
        for (News curNews : news) {
            newsDTO.add(curNews.toDTO());
        }
        return newsDTO;
    }

    @Override
    public List<NewsDTO> findAll() {
        List<News> news = newsRepository.findAll();
        List<NewsDTO> newsDTO = new ArrayList<>();
        for (News curNews : news) {
            newsDTO.add(curNews.toDTO());
        }
        return newsDTO;
    }


    @Override
    public NewsDTO getNews(Long id) {
        return newsRepository.findOne(id).toDTO();
    }

    @Override
    public void addCategoryToNews(Long categoryId, Long newsId) {
        News news = newsRepository.findOne(newsId);
        Category category = categoryRepository.findOne(categoryId);
        news.getCategories().add(category);
        newsRepository.save(news);
    }


    @Override
    public void save(NewsDTO newsDTO) {
        News news ;
        if(newsDTO.getIdNews()!=null)
         news = newsRepository.findOne(newsDTO.getIdNews());
        else
        news = new News();
        news = fromDTO(newsDTO,news);
        newsRepository.save(news);

    }

    @Override
    @Rollback(false)
    public void delete(Long id) {
        newsRepository.delete(id);
    }



    public News fromDTO(NewsDTO newsDTO,News news){
        news.setName(newsDTO.getName());
        news.setBody(newsDTO.getBody());
        news.setCreated(newsDTO.getCreated());
        news.setCategories(new HashSet<>());
        for (Long id : newsDTO.getCategoriesId()) {
            Category category = categoryRepository.findOne(id);
            news.getCategories().add(category);
        }
        return  news;
    }

}
