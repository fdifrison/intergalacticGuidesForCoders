package com.sample.spring5webapp_v01.bootstrap;

import com.sample.spring5webapp_v01.models.Author;
import com.sample.spring5webapp_v01.models.Book;
import com.sample.spring5webapp_v01.models.Publisher;
import com.sample.spring5webapp_v01.repositories.AuthorRepository;
import com.sample.spring5webapp_v01.repositories.BookRepository;
import com.sample.spring5webapp_v01.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    // Dependency Injection
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher salani = new Publisher("Salani", "via dei matti", "Italy");
        Publisher bompiani = new Publisher("Bompiani", "via dei fatti", "Italy");

        // N.B. Order MATTERS, first save the object to repo then add it in join entity (in this case book.setPublisher)
        publisherRepository.save(salani);
        publisherRepository.save(bompiani);

        Author jkr = new Author("J.K.", "Rowling");
        Book hp1 = new Book("Harry potter and the philosopher Stone", "9788869183157");
        Book hp2 = new Book("Harry potter and chamber of the secrets", "978886918315234");
        Book hp3 = new Book("Harry potter and the prisoner of Azkaban", "97888691831456");
        jkr.getBooks().add(hp1);
        jkr.getBooks().add(hp2);
        jkr.getBooks().add(hp3);
        hp1.getAuthors().add(jkr);
        hp2.getAuthors().add(jkr);
        hp3.getAuthors().add(jkr);
        hp1.setPublisher(salani);
        hp2.setPublisher(salani);
        hp3.setPublisher(salani);
        salani.getBooks().add(hp1);
        salani.getBooks().add(hp2);
        salani.getBooks().add(hp3);

        Author jjrt = new Author("J.R.R", "Tolkien");
        Book isda1 = new Book("The lord of the rings", "97888777831456");
        Book hobb = new Book("The Hobbit", "97888254331456");
        jjrt.getBooks().add(isda1);
        jjrt.getBooks().add(hobb);
        isda1.getAuthors().add(jjrt);
        hobb.getAuthors().add(jjrt);
        bompiani.getBooks().add(isda1);
        bompiani.getBooks().add(hobb);
        isda1.setPublisher(bompiani);
        hobb.setPublisher(bompiani);

        authorRepository.save(jjrt);
        authorRepository.save(jkr);

        bookRepository.save(hp1);
        bookRepository.save(hp2);
        bookRepository.save(hp3);
        bookRepository.save(isda1);
        bookRepository.save(hobb);


        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());

    }
}
