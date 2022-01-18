package br.com.erudio.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "books")
public class Book implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 180)
    private String author;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 250)
    private String title;

    public Book() {}


    public String getAuthor() {
        return author;
    }
    public Long getId() {
        return id;
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
    public void setId(Long id) {
        this.id = id;
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
