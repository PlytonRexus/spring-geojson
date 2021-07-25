package com.sandbox.sandbox.dtos.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "age", "gender", "hairColour", "eyeColour" })
public class Characteristics implements Serializable {

    @JsonProperty("age")
    private Integer age = 100;
    @JsonProperty("gender")
    private String gender = "Not provided";
    @JsonProperty("hairColour")
    private String hairColour = "Black";
    @JsonProperty("eyeColour")
    private String eyeColour = "Brown";
    private static final long serialVersionUID = 7706152337060173355L;

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("hairColour")
    public String getHairColour() {
        return hairColour;
    }

    @JsonProperty("hairColour")
    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    @JsonProperty("eyeColour")
    public String getEyeColour() {
        return eyeColour;
    }

    @JsonProperty("eyeColour")
    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("age", age).append("gender", gender).append("hairColour", hairColour)
                .append("eyeColour", eyeColour).toString();
    }

    public Characteristics(Integer age, String gender, String hairColour, String eyeColour) {
        this.age = age;
        this.gender = gender;
        this.hairColour = hairColour;
        this.eyeColour = eyeColour;
    }

    public Characteristics() {
    }

}