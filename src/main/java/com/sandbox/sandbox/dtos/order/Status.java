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
@JsonPropertyOrder({ "progress", "value" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status implements Serializable {

    @JsonProperty("progress")
    private Integer progress = 50;
    @JsonProperty("value")
    private String value = "IN_PROGRESS";
    private static final long serialVersionUID = 6492079658984749833L;

    @JsonProperty("progress")
    public Integer getProgress() {
        return progress;
    }

    @JsonProperty("progress")
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("progress", progress)
            .append("value", value)
            .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(progress).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Status) == false) {
            return false;
        }
        Status rhs = ((Status) other);
        return new EqualsBuilder().append(progress, rhs.progress)
                .append(value, rhs.value).isEquals();
    }

}