package br.com.erudio.data.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import org.springframework.hateoas.ResourceSupport;


public class BookVO extends ResourceSupport implements Serializable{
    private static final long serialVersionUID = 1l;


    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String author;

    private Date launchDate;

    private Double price;

    private String title;

    public BookVO() {}


    public String getAuthor() {
        return author;
    }
    public Long getKey() {
        return key;
    }
    public Date getLaunchDate() {
        return launchDate;
    }
    public Double getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }
    public void setKey(Long id) {
        this.key = id;
    }
    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    
}
