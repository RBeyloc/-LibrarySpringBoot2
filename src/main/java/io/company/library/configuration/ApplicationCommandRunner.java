package io.company.library.configuration;

import com.github.javafaker.Faker;
import io.company.library.model.Author;
import io.company.library.service.AuthorService;
import io.company.library.model.Book;
import io.company.library.service.BookService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Component
    public class ApplicationCommandRunner implements CommandLineRunner {

        protected final Log logger = LogFactory.getLog(getClass());

        @Autowired
        BookService bookService;
        @Autowired
        AuthorService authorService;

        @Override
        public void run(String... args) throws Exception {
            //Scanner reader = new Scanner(System.in);
            //createFakers();
            //createOneBook(reader);
        }

        public void createFakers(){
            logger.info("Welcome to the createFakers");

            Faker faker = new Faker();
            for (int i = 0; i < 500; i++) {
                String[] name = faker.book().author().split(" ");
                String firstName = name[0];
                String lastName = name[1];
                LocalDate dateOfBirth = LocalDate.now();
                Author author = new Author(firstName, lastName, dateOfBirth);
                authorService.createAuthor(author);

                String title = faker.book().title();
                int pages = ThreadLocalRandom.current().nextInt(60, 2500);
                int publishedYear = ThreadLocalRandom.current().nextInt(-500, 2022);
                String isbn = RandomStringUtils.randomAlphabetic(20);
                Book book = new Book(title, author, pages, publishedYear, isbn);
                bookService.createBook(book);
            }

            logger.info("finishing createFakers ...");

        }

        public void createOneBook(Scanner reader){

            logger.info("Welcome to the createBook");

            System.out.println("Title? ");
            String title = reader.nextLine();
            System.out.println("Author? ");
            String author = reader.nextLine();
            System.out.println("Pages? ");
            int pages = reader.nextInt();
            System.out.println("Year Published? ");
            int publishedYear = reader.nextInt();
            System.out.println("ISBN? ");
            String isbn = reader.next();

            bookService.createBook(new Book(title, pages, publishedYear, isbn));

            logger.info("bookService called with new book ...");
            logger.info("finishing createBook ...");
        }

    }

