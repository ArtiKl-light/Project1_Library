package ru.artikl.projectlibrary.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainLibraryController {

    @GetMapping()
    public String lib() {
        return "lib/main";
    }

}