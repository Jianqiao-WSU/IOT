package com.main.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * CORS 配置
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedMethods(ALL)
                .allowedHeaders(ALL)
                .allowCredentials(true);
    }
////	解决跨域
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration conf = new CorsConfiguration();
//        conf.addAllowedHeader("*");
//        conf.addAllowedMethod("*");
//        conf.addAllowedOrigin("*");
//        conf.setAllowCredentials(true);
//        conf.setMaxAge(3600L);
//        conf.addExposedHeader("set-cookie");
//        conf.addExposedHeader("access-control-allow-headers");
//        conf.addExposedHeader("access-control-allow-methods");
//        conf.addExposedHeader("access-control-allow-origin");
//        conf.addExposedHeader("access-control-max-age");
//        conf.addExposedHeader("X-Frame-Options");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", conf); // 4 对接口配置跨域设置
//        return new CorsFilter(source);
//    }
}
