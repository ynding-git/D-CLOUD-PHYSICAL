package com.ynding.cloud.book.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * 资源服务器
 * 配置了@EnableResourceServer ，所有发往physical-book-meta的请求，都会去请求头里找token，找不到不让你过
 * @author ynding
 * @version 2020/9/9
 **/
@Slf4j
@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 声名OAuth2RestTemplate
     * 会从请求的上下文里拿到jwt令牌，放到请求头里，发出去。需要两个参数，springboot会自动出入进来
     * @param resource
     * @param context
     * @return
     */
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context){
        return new OAuth2RestTemplate(resource,context);
    }

    /**
     * 使用feign
     * @return
     */
    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
                        SecurityContextHolder.getContext().getAuthentication().getDetails();

                requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
            }
        };
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，“现在我就是资源服务器book-server！！！”
        resources.resourceId("book-server");
    }

    /**
     * 资源服务器保护的端点，优先级高于 spring security 的配置。不配置的话，第三方应用通过认证后，可访问本服务所有接口
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 进入physical-book-meta的所有请求，哪些要拦截，哪些要放过，在这里配置
         */
        http.authorizeRequests()
                .antMatchers("/h2-console/**",
                        "/doc.html","/swagger**/**", "/webjars/**", "/v3/**")
                .permitAll();

        log.info("ResourceServerConfig 中配置 HttpSecurity对象执行");
        // 只有/book 端点作为资源服务器的资源
        http.requestMatchers().antMatchers("/book/**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
