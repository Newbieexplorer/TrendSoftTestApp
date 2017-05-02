package web.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.test.model.CategoryDTO;
import web.test.model.NewsDTO;
import web.test.service.CategoryService;
import web.test.service.NewsService;

import java.util.*;


@Controller
public class MainController {


    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/news";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews(@RequestParam Map<String, String> params, Model model) {
        List<NewsDTO> news = new ArrayList<NewsDTO>();
        List<CategoryDTO> categories = categoryService.findCategories();
        if (params.size() != 0) {
            Long id = null;
            for (CategoryDTO category : categories) {

                String param = "option";
                id = category.getId();

                param = param + id;

                if (params.get(param) != null) {
                    id = Long.valueOf(params.get(param));
                    break;
                }
            }
            news = this.newsService.findNewsByCategory(id);
        } else {

            news = this.newsService.findAll();
        }
        model.addAttribute("news", news);
        model.addAttribute("categories", categories);

        return "news";
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.GET)
    public String getAdd(Model model) {


        List<CategoryDTO> categories = categoryService.findCategories();


        model.addAttribute("categories", categories);


        return "addnews";
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name, @RequestParam(value = "body", required = false) String body, @RequestParam(value = "created") Date created, @RequestParam Map<String, String> params, Model model) {
        List<Long> idsCategory = new ArrayList<>();

        NewsDTO newsDTO = new NewsDTO();

        List<CategoryDTO> categories = categoryService.findCategories();

        for (CategoryDTO category : categories) {
            String param = "option";
            Long id = category.getId();

            param = param + id;

            if (params.get(param) != null) {
                id = Long.valueOf(params.get(param));
                idsCategory.add(id);
            }
        }
        newsDTO.setName(name);
        newsDTO.setBody(body);
        newsDTO.setCategoriesId(idsCategory);
        newsDTO.setCreated(created);
        this.newsService.save(newsDTO);
        model.addAttribute("name", name);
        model.addAttribute("body", body);
        model.addAttribute("putdate", created);

        return "addednews";
    }

    @RequestMapping(value = "/news/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Long id,
                         Model model) {
        newsService.delete(id);
        model.addAttribute("id", id);
        return "deletedpage";
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.GET)
    public String updatepage(@RequestParam(value = "id") Long id,
                             Model model) {
        NewsDTO newsDTO = newsService.getNews(id);
        List<CategoryDTO> categories = categoryService.findCategories();
        model.addAttribute("idNews", id);
        model.addAttribute("news", newsDTO);
        model.addAttribute("categories", categories);

        return "updatepage";
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "name") String name, @RequestParam Map<String, String> params, @RequestParam(value = "created") Date created, @RequestParam(value = "idNews") Long idNews, @RequestParam(value = "body", required = false) String body, Model model) {
        List<Long> idsCategory = new ArrayList<>();
        List<CategoryDTO> categories = categoryService.findCategories();

        for (CategoryDTO category : categories) {
            String param = "option";
            Long id = category.getId();

            param = param + id;

            if (params.get(param) != null) {
                id = Long.valueOf(params.get(param));
                idsCategory.add(id);
            }
        }
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setIdNews(idNews);
        newsDTO.setName(name);
        newsDTO.setBody(body);
        newsDTO.setCategoriesId(idsCategory);
        newsDTO.setCreated(created);
        this.newsService.save(newsDTO);
        model.addAttribute("name", name);
        model.addAttribute("body", body);

        return "updatednews";
    }
}
