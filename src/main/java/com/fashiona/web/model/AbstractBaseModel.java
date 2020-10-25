package com.fashiona.web.model;

import com.fashiona.web.jackson.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class AbstractBaseModel {
    protected long retailerId;
    protected boolean isActive;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    protected LocalDateTime createdDate;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public long getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(long retailerId) {
        this.retailerId = retailerId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
