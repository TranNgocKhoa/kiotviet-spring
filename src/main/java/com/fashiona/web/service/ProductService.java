package com.fashiona.web.service;

import com.fashiona.web.client.KiotVietService;
import com.fashiona.web.config.KiotVietProperties;
import com.fashiona.web.model.Paging;
import com.fashiona.web.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductService {
    private final KiotVietProperties kiotVietProperties;
    private final KiotVietService kiotVietService;

    public ProductService(KiotVietProperties kiotVietProperties, KiotVietService kiotVietService) {
        this.kiotVietProperties = kiotVietProperties;
        this.kiotVietService = kiotVietService;
    }

    public Paging<Product> getListProduct(String orderBy, String orderDirection, int offset, int size, boolean includeInventory, boolean includePricebook, boolean includeMaterial, long masterProductId, int categoryId) {
        String url = StringUtils.join(kiotVietProperties.getPublicUrl(), "/products");

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("currentItem", String.valueOf(offset));
        queryParams.add("pageSize", String.valueOf(size));
        queryParams.add("includeInventory", String.valueOf(includeInventory));
        queryParams.add("includePricebook", String.valueOf(includePricebook));
        queryParams.add("includeMaterial", String.valueOf(includeMaterial));
        if (StringUtils.isNotEmpty(orderBy)) {
            queryParams.add("orderBy", orderBy);
        } else {
            queryParams.add("orderBy", "createdDate");
        }

        if (StringUtils.isNotEmpty(orderDirection)) {
            queryParams.add("orderDirection", orderDirection);
        }

        if (masterProductId > NumberUtils.INTEGER_ZERO) {
            queryParams.add("masterProductId", String.valueOf(masterProductId));
        }

        if (categoryId > NumberUtils.INTEGER_ZERO) {
            queryParams.add("categoryId", String.valueOf(categoryId));
        }

        url = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(queryParams)
                .build().toString();

        return kiotVietService.get(url, new ParameterizedTypeReference<>() {});
    }
}
