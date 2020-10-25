package com.fashiona.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.math.NumberUtils;

public class Category extends AbstractBaseModel {
    @JsonProperty("categoryId")
    private long id = NumberUtils.LONG_MINUS_ONE;
    @JsonProperty("categoryName")
    private String name;
    private boolean hasChild;
    private int rank;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
