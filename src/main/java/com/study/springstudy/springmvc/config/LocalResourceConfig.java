package com.study.springstudy.springmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬 서버에 저장된 이미지를 웹브라우저에서 불러올 수 있도록
// 로컬 서버 파일경로를 웹 서버 URL을 생성하는 설정
@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload.root-path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
             ResourceLocations : 로컬에 있는 경로
             ResourceLocations : 해당 로컬 경로를 web url로 변환

             ex )

             D:/xxx/dog.jpg

             local접근 -  file:D:/xxx/dog.jpg
             web접근 - http://localhost:8383/local/dog.jpg
             /local만 붙혀주면 local에 있는 경로 접근 가능하게 해주겠다는 뜻.
             ex ) http://localhost:8383/local/2024/06/05/(파일명).jpg
         */
        registry
                .addResourceHandler("/local/**")
                .addResourceLocations("file:" + rootPath);
    }
}
