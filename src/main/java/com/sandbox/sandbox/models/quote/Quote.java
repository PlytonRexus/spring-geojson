package com.sandbox.sandbox.models.quote;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quotes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success", "contents", "baseurl", "copyright" })
public class Quote {

    @JsonProperty("success")
    private Success success;
    @JsonProperty("contents")
    private Contents contents;
    @JsonProperty("baseurl")
    private String baseurl;
    @JsonProperty("copyright")
    private Copyright copyright;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("success")
    public Success getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Success success) {
        this.success = success;
    }

    @JsonProperty("contents")
    public Contents getContents() {
        return contents;
    }

    @JsonProperty("contents")
    public void setContents(Contents contents) {
        this.contents = contents;
    }

    @JsonProperty("baseurl")
    public String getBaseurl() {
        return baseurl;
    }

    @JsonProperty("baseurl")
    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    @JsonProperty("copyright")
    public Copyright getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("success", success).append("contents", contents)
                .append("baseurl", baseurl).append("copyright", copyright)
                .append("additionalProperties", additionalProperties).toString();
    }

}