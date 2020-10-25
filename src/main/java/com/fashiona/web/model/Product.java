package com.fashiona.web.model;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;

public class Product extends AbstractBaseModel {
    private long id = NumberUtils.LONG_MINUS_ONE;
    private String code;
    private String name;
    private String fullName;
    private long categoryId;
    private String categoryName;
    private boolean allowsSale;
    private int type;
    private boolean hasVariants;
    private BigDecimal basePrice;
    private int conversionValue;
    private boolean isLotSerialControl;
    private boolean isBatchExpireControl;
    private List<String> images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isAllowsSale() {
        return allowsSale;
    }

    public void setAllowsSale(boolean allowsSale) {
        this.allowsSale = allowsSale;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHasVariants() {
        return hasVariants;
    }

    public void setHasVariants(boolean hasVariants) {
        this.hasVariants = hasVariants;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public int getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(int conversionValue) {
        this.conversionValue = conversionValue;
    }

    public boolean isLotSerialControl() {
        return isLotSerialControl;
    }

    public void setLotSerialControl(boolean lotSerialControl) {
        isLotSerialControl = lotSerialControl;
    }

    public boolean isBatchExpireControl() {
        return isBatchExpireControl;
    }

    public void setBatchExpireControl(boolean batchExpireControl) {
        isBatchExpireControl = batchExpireControl;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
