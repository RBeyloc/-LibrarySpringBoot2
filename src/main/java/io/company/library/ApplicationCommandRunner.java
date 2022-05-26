package io.company.library;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Component
    public class ApplicationCommandRunner implements CommandLineRunner {

        protected final Log logger = LogFactory.getLog(getClass());

        @Autowired
        BookService bookService;

        @Override
        public void run(String... args) throws Exception {
            //Scanner reader = new Scanner(System.in);
            //createBooks();
            //createOneBook(reader);
        }

        public void createBooks(){
            logger.info("Welcome to the createBooks");

            Faker faker = new Faker();
            for (int i = 0; i < 500; i++) {
                String title = faker.book().title();
                String author = faker.book().author();
                int pages = ThreadLocalRandom.current().nextInt(60, 2500);
                int publishedYear = ThreadLocalRandom.current().nextInt(-500, 2022);
                String isbn = RandomStringUtils.randomAlphabetic(20);
                Book book = new Book(title, author, pages, publishedYear, isbn);
                bookService.createBook(book);
            }

            logger.info("finishing createBooks ...");

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

            bookService.createBook(new Book(title, author, pages, publishedYear, isbn));

            logger.info("bookService called with new book ...");
            logger.info("finishing createBook ...");
        }

    }

