package com.archetype.anima.web.controller;

import com.archetype.anima.entity.Book;
import com.archetype.anima.entity.Isbn;
import com.archetype.anima.repository.BookRepository;
import com.archetype.anima.web.converter.BookConverter;
import com.archetype.anima.web.form.BookForm;
import com.archetype.anima.web.presenter.BookPresenter;
import com.archetype.anima.web.presenter.BooksPresenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/anima")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String index() {
        return "redirect:/anima/books";
    }

    @GetMapping("books")
    public String list(Model model) {
        Object alert = model.getAttribute("alert");
        Object message = model.getAttribute("message");
        if (alert != null) {
            model.addAttribute("alert", alert);
        }
        if (message != null) {
            model.addAttribute("message", message);
        }

        List<Book> books = bookRepository.findAll();
        model.addAttribute("booksPresenter", new BooksPresenter(books));

        return "index";
    }

    @GetMapping("books/{isbn}")
    public String show(@PathVariable String isbn, Model model) {
        Book book = bookRepository.findBy(new Isbn(isbn));
        model.addAttribute("bookPresenter", new BookPresenter(book));

        return "show";
    }

    @GetMapping("books/new")
    public String newBook(Model model) {
        Object alert = model.getAttribute("alert");
        if (alert != null) {
            log.debug("alert");
        }

        return "new";
    }

    @PostMapping("books/new")
    public String newBooks(
        @Validated @ModelAttribute BookForm form,
        BindingResult br,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        if (br.hasErrors()) {
            log.debug("validation error");
            model.addAttribute("alert", "validation error");
            return "new";
        }

        bookRepository.insert(BookConverter.convert(form));
        redirectAttributes.addFlashAttribute("message", "add new book success");

        return "redirect:/anima/books";
    }
}
