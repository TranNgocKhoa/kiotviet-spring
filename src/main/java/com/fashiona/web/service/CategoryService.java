package com.fashiona.web.service;

import com.fashiona.web.client.KiotVietService;
import com.fashiona.web.config.KiotVietProperties;
import com.fashiona.web.model.Category;
import com.fashiona.web.model.Paging;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CategoryService {
    private final KiotVietProperties kiotVietProperties;
    private final KiotVietService kiotVietService;

    public CategoryService(KiotVietProperties kiotVietProperties, KiotVietService kiotVietService) {
        this.kiotVietProperties = kiotVietProperties;
        this.kiotVietService = kiotVietService;
    }

    public Paging<Category> getCategoryList(int offset, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(StringUtils.join(kiotVietProperties.getPublicUrl(), "/categories"))
                .queryParam("currentItem", offset)
                .queryParam("pageSize", size)
                .queryParam("orderBy", "rank")
                .queryParam("hierachicalData", true).build().toString();

        return kiotVietService.get(url, new ParameterizedTypeReference<>() {});
    }
}
