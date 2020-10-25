package com.fashiona.web.config;

import com.fashiona.web.client.KiotVietAuthenticationInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration {
    private final KiotVietProperties kiotVietProperties;

    public WebConfiguration(KiotVietProperties kiotVietProperties) {
        this.kiotVietProperties = kiotVietProperties;
    }

    @Bean
    public WebMvcConfigurer messageConverter(ObjectMapper objectMapper) {
        return new WebMvcConfigurer() {
            @Override
            public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
                converters.add(new FormHttpMessageConverter());
                converters.add(this.customJackson2HttpMessageConverter(objectMapper));
                converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
            }


            private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(ObjectMapper objectMapper) {
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
                    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                        return method.hasReturnType() && (method.getRawReturnType() == Boolean.class || method.getRawReturnType() == Boolean.TYPE) && method.getName().startsWith("is") ? method.getName() : super.nameForGetterMethod(config, method, defaultName);
                    }

                    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                        return this.isBoolSetter(method) ? "is" + StringUtils.capitalize(defaultName) : super.nameForSetterMethod(config, method, defaultName);
                    }

                    private boolean isBoolSetter(AnnotatedMethod method) {
                        return (method.getParameterCount() == NumberUtils.INTEGER_ONE
                                && (method.getRawParameterType(NumberUtils.INTEGER_ZERO) == Boolean.class || method.getRawParameterType(NumberUtils.INTEGER_ZERO) == Boolean.TYPE))
                                && (!method.getName().startsWith("is"));
                    }
                });

                return new MappingJackson2HttpMessageConverter(objectMapper);
            }
        };
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new KiotVietAuthenticationInterceptor(kiotVietProperties));

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    @Bean(name = {"objectMapper"})
    @ConditionalOnMissingBean(name = {"objectMapper"})
    @Order(Ordered.HIGHEST_PRECEDENCE + 20)
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        return objectMapper;
    }
}
