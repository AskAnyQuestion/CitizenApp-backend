package com.example.citizen.service;


import com.example.citizen.model.News;
import com.example.citizen.model.User;
import com.example.citizen.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews() {
        return newsRepository.findAll();
    }

    public void save(News news) {
        newsRepository.save(news);
    }

    public Resource getMaterials() throws Exception {
        Path zipFile = Paths.get("./news/resources.zip");
        Path sourcePath = Paths.get("./news/id/" );
        File source = new File(sourcePath.toUri());
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(zipFile.toFile()));
        addFilesToZip("", source, stream);
        stream.close();
        InputStream inputStream = new FileInputStream(zipFile.toFile());
        return new InputStreamResource(inputStream);
    }

    private void addFilesToZip(String prefix, File folder, ZipOutputStream zos) throws IOException {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                addFilesToZip(prefix + file.getName() + "/", file, zos);
            } else {
                zos.putNextEntry(new ZipEntry(prefix + file.getName()));
                zos.write(Files.readAllBytes(file.toPath()));
                zos.closeEntry();
            }
        }
    }
}
