package com.Areusus.music.api.config;

import com.Areusus.music.api.constant.StaticResource;
import com.Areusus.music.api.handler.LoginHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LoginHandler getHandler(){
        return new LoginHandler();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true) //这个配置很重要，不加会报cors错误，不清楚原理
                .allowedOriginPatterns("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations(StaticResource.SINGER_PIC_PATH);
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations(StaticResource.SONGLIST_PIC_PATH);
        registry.addResourceHandler("/song/**")
                .addResourceLocations(StaticResource.SONG_PATH);
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations(StaticResource.SONG_PIC_PATH);
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations(StaticResource.AVATOR_IMAGE_PATH);
    }

    // 解码器配置
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandler()).addPathPatterns("/comment/add","/comment/like","/comment/update");
    }
}
