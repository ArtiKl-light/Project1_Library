package ru.artikl.projectlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.artikl.projectlibrary.model.Book;
import ru.artikl.projectlibrary.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, birthday) VALUES (?, ?)",
                person.getName(), person.getBirthday());
    }

    public void update(Person person, int person_id) {
        jdbcTemplate.update("UPDATE Person SET name=?, birthday=? WHERE person_id=?",
                person.getName(), person.getBirthday(), person_id);
    }

    public Person read(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<Person>(Person.class))
                        .stream().findAny().orElse(null);
    }

    public List<Person> readListPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Book> readListBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
