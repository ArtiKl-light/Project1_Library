package ru.artikl.projectlibrary.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.artikl.projectlibrary.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();


        book.setPerson_id(rs.getInt("person_id"));
        book.setBook_id(rs.getInt("book_id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setReleased(rs.getInt("released"));

        return book;
    }
}
