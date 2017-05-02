package web.test.сonfig;

import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

@Configuration
@EnableAutoConfiguration
public class WebMvcConfig {

    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setPrefix("classpath:/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

}
