package ru.amlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.amlet.dto.BookDto;
import ru.amlet.entity.Book;
import ru.amlet.exception.BookException;
import ru.amlet.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/create")
    public String createPage() {
        return "createBook";
    }

    @Validated
    @PostMapping("/books/create")
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createBook";
        }
        Book book = bookDto.toDomainObject();
        bookService.createBook(book.getName(), book.getAuthorName(), book.getGenreName());
        return "redirect:/books";
    }

    @GetMapping("books/find")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Book> books = bookService.findByName(name);
        model.addAttribute("books", books);
        return "findBook";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Optional<Book> book = Optional.ofNullable(bookService.findById(id).orElseThrow(BookException::new));
        model.addAttribute("book", book.get());
        return "editBook";
    }

    @Validated
    @PostMapping("/books/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editBook";
        }
        Book book = bookDto.toDomainObject();
        bookService.updateBook(book.getId(), book.getName(), book.getAuthorName(), book.getGenreName());
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deletePage(@RequestParam("id") int id, Model model) {
        Optional<Book> book = Optional.ofNullable(bookService.findById(id).orElseThrow(BookException::new));
        model.addAttribute("book", book.get());
        return "deleteBook";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/books/count")
    public String count(Model model) {
        long count = bookService.count();
        model.addAttribute("countBooks", count);
        return "countBooks";
    }

}