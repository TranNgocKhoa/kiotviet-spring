package com.fashiona.web.service;

import com.fashiona.web.model.Category;
import com.fashiona.web.model.Paging;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCategoryList() {
        Paging<Category> categoryList = categoryService.getCategoryList(0, 3);

        Assertions.assertEquals(categoryList.getData().size(), 3);
    }
}