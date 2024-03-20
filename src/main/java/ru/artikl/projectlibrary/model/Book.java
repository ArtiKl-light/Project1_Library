package ru.artikl.projectlibrary.model;


import lombok.Data;

@Data
public class Book {

    private int book_id;
    private int person_id;
    private String name;
    private String author;
    private int released;

}
