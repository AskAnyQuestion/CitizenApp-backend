package com.example.citizen.loader;

import com.example.citizen.model.News;
import com.example.citizen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class NewsLoader implements CommandLineRunner {
    @Autowired
    private NewsService newsService;
    public NewsLoader(NewsService newsService) {
        this.newsService = newsService;
    }
    @Override
    public void run(String... args) {
        News news = new News();
        news.setIdNews(1);
        news.setLatitude(57.216746);
        news.setLongitude(41.932818);
        news.setEventTime(new Timestamp(System.currentTimeMillis()));
        news.setDescription("2 вооружённых людей устроили стрельбу возле частных домов");
        news.setAddition("Медицинский персонал подтверждает, что общее число погибших людей достигло 3 человек");
        newsService.save(news);

        News newsTwo = new News();
        newsTwo.setIdNews(2);
        newsTwo.setLatitude(57.214255);
        newsTwo.setLongitude(41.958810);
        newsTwo.setEventTime(new Timestamp(System.currentTimeMillis()));
        newsTwo.setDescription("Хулиганы похитили молодую женщину");
        newsTwo.setAddition("Настоящее время ведутся поиски");
        newsService.save(newsTwo);
    }
}
