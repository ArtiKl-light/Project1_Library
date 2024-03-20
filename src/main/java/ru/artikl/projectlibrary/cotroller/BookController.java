package ru.artikl.projectlibrary.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artikl.projectlibrary.dao.BookDAO;
import ru.artikl.projectlibrary.dao.PersonDAO;
import ru.artikl.projectlibrary.model.Book;
import ru.artikl.projectlibrary.model.Person;

import java.util.SortedSet;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("create")
    public String showCreate(@ModelAttribute("book") Book book) {
        return "books/create";
    }

    @PostMapping("")
    public String create(@ModelAttribute("book") Book book) { // переписать название
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping()
    public String readAll(Model model) {
        model.addAttribute("books", bookDAO.readList());
        return "books/books";
    }

    @GetMapping("read/{id}")
    public String read(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",  bookDAO.read(id));
        Person person = personDAO.read(bookDAO.read(id).getPerson_id());

        if (person != null) {
            model.addAttribute("person", person);
            return "books/readPersonIsNotNull";
        }

        model.addAttribute("people", personDAO.readListPeople());
        return "books/readPersonIsNull";
    }

    @GetMapping("update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.read(id));
        return "books/update";
    }

    @PatchMapping("update/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(book, id);
        return "redirect:/books";
    }

    @PatchMapping("assign/{id}")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.update(id, person.getPerson_id());
        return "redirect:/books";
    }

    @PatchMapping("update/delete/{id}")
    public String showDelete(@PathVariable("id") int id) {
        bookDAO.update(id);
        return "redirect:/books/read/{id}";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
