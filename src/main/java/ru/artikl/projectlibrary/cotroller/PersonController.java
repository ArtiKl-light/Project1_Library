package ru.artikl.projectlibrary.cotroller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artikl.projectlibrary.dao.PersonDAO;
import ru.artikl.projectlibrary.model.Book;
import ru.artikl.projectlibrary.model.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("create")
    public String showCreate(@ModelAttribute("person") Person person) {
        return "people/create";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") Person person) {
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping()
    public String readAll(Model model) {
        model.addAttribute("people", personDAO.readListPeople());
        return "people/readAll";
    }

    @GetMapping("read/{id}")
    public String read(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.read(id));

        List<Book> book_list = personDAO.readListBook(id);
        if (!book_list.isEmpty()) {
            model.addAttribute("book_list", book_list);
            return "people/readIsNoEmptyBooks";
        }

        return "people/readIsEmptyBooks";
    }

    @GetMapping("update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.read(id));
        return "people/update";
    }

    @PatchMapping("update/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}