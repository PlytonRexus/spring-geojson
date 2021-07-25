package com.sandbox.sandbox.dtos.order;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "description", "id", "status", "customerId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("customerId")
    private String customerId;
    private static final long serialVersionUID = -2918730200629470094L;

    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("productId", productId)
            .append("description", description)
            .append("id", id)
            .append("status", status)
            .append("customerId", customerId)
            .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(description)
            .append(id)
            .append(productId)
            .append(customerId)
            .append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OrderDTO)) {
            return false;
        }
        OrderDTO rhs = ((OrderDTO) other);
        return new EqualsBuilder()
            .append(description, rhs.description)
            .append(id, rhs.id)
            .append(productId, rhs.productId)
            .append(customerId, rhs.customerId)
            .append(status, rhs.status).isEquals();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}