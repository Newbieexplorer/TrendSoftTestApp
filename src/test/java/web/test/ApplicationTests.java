package web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import web.test.model.NewsDTO;
import web.test.service.NewsService;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Autowired
	NewsService newsService;

	@Test
	public void Test() {
		List<NewsDTO> pastBdNews =newsService.findAll();
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setName("News2");
		newsDTO.setBody("News2");
		newsDTO.setCreated(new Date(2016, 2, 2));
		List<Long> categories = new ArrayList<>();
		categories.add(Long.valueOf(2));
		newsDTO.setCategoriesId(categories);
		newsService.save(newsDTO);
		List<NewsDTO> curBdNews =newsService.findAll();
		Assert.assertEquals(1,curBdNews.size()-pastBdNews.size());
	}

}
