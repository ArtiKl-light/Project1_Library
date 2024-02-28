package ru.artikl.projectlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.artikl.projectlibrary.model.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, released) VALUES ( ?, ?, ?)",
                book.getName(), book.getAuthor(), book.getReleased());
    }

    public void update(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",
                person_id, book_id);
    }

    public void update(Book book, int book_id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, released=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getReleased(), book_id);
    }

    public void update(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
    }

    public Book read(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                         new BookMapper())
                        .stream().findAny().orElse(null);
    }

    public List<Book> readList() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
