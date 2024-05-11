package com.project.proiectspring.controller;

import com.project.proiectspring.exception.UserNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.AuthorRepository;
import com.project.proiectspring.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final BookController bookController;

    private final AuthorController authorController;
    private final AuthorService authorService;

    private final UserController userController;

    private final RentalController rentalController;

    public MainController(BookController bookController, AuthorController authorController,
                          AuthorService authorService, UserController userController,
                          RentalController rentalController) {
        this.bookController = bookController;
        this.authorController = authorController;
        this.authorService = authorService;
        this.userController = userController;
        this.rentalController = rentalController;
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) { return getHome(); }

        logger.info("Displaying login view");
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView viewSignup() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) { return getHome(); }

        logger.info("Displaying signup view");
        return new ModelAndView("signup");
    }

    @GetMapping("/view-books")
    public ModelAndView viewBooks(@RequestParam(required = false) Long authorId) {
        List<Book> books = new ArrayList<>();

        if (authorId != null){
            logger.info("Displaying author's books view");
            books = bookController.get(authorId);
        }
        else {
            books = bookController.get(null);
            logger.info("Displaying books view");
        }


        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", books);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                logger.info("Sending ADMIN role to View");
                modelAndView.addObject("role","ADMIN");
            }
            else {
                logger.info("Sending USER role to View");
                modelAndView.addObject("role","USER");
            }
        }
        else {
            logger.info("Sending NONE role to View");
            modelAndView.addObject("role","NONE");
        }

        logger.info("Sending current user's email to View: " + authentication.getName());
        String currentUserEmail = authentication.getName();
        modelAndView.addObject("currentUserEmail", currentUserEmail);

        return modelAndView;
    }

    @GetMapping("/view-rentals")
    public ModelAndView viewRentalsOfUser(@RequestParam Long userId) {
        List<BookRental> rentals = new ArrayList<>();

        User user = userController.get(userId, null, null, null).get(0);

        if (userId != null)
        {
            rentals = rentalController.get(user);
            logger.info("Displaying user's rentals");
        }
        else
            throw new UserNotFoundException();


        ModelAndView modelAndView = new ModelAndView("rentals");
        modelAndView.addObject("rentals", rentals);
        modelAndView.addObject("user",user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                logger.info("Sending ADMIN role to View");
                modelAndView.addObject("role","ADMIN");
            }
            else {
                logger.info("Sending USER role to View");
                modelAndView.addObject("role","USER");
            }
        }
        else {
            logger.info("Sending NONE role to View");
            modelAndView.addObject("role","NONE");
        }

        return modelAndView;
    }

    @GetMapping("/view-authors")
    public ModelAndView viewAuthors(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {

        List<Author> authors;

        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, sortBy);
            authors = authorService.getSortedAuthors(sort);
        } else {
            authors = authorController.get(null);
        }

        ModelAndView modelAndView = new ModelAndView("authors");
        modelAndView.addObject("authors", authors);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                logger.info("Sending ADMIN role to View");
                modelAndView.addObject("role","ADMIN");
            }
            else {
                logger.info("Sending USER role to View");
                modelAndView.addObject("role","USER");
            }
        }
        else {
            logger.info("Sending NONE role to View");
            modelAndView.addObject("role","NONE");
        }

        return modelAndView;
    }

    @GetMapping("/view-users")
    public ModelAndView viewUsers() {
        List<User> users = userController.get(null, null, null,null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                logger.info("Sending ADMIN role to View");
                logger.info("Sending user data to View");
                ModelAndView modelAndView = new ModelAndView("users");
                modelAndView.addObject("users", users);
                modelAndView.addObject("role", "ADMIN");
                return modelAndView;
            }
        }

        logger.info("Redirecting unauthorized user to main menu");
        return new ModelAndView("/");
    }


    @RequestMapping({"", "/"})
    public ModelAndView getHome() {
        logger.info("Accessing home page");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                logger.info("Sending ADMIN role to View");
                return new ModelAndView("main").addObject("role","ADMIN");
            }
            else {
                logger.info("Sending USER role to View");
                return new ModelAndView("main").addObject("role","USER");
            }
        }

        logger.info("Sending NONE role to View");
        return new ModelAndView("main").addObject("role","NONE");
    }
}