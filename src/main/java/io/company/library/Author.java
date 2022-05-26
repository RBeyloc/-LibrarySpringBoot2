package io.company.library;

//https://projectlombok.org/features/all
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AUTHOR_ID")
    private  long authorId;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE")
    private LocalDate dob;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL )
    private List<Book> books = new ArrayList<Book>();

    //constructor without ID and books
    public Author(String firstName, String lastName, LocalDate dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;

    }
    public void addBook(Book book) {
        this.books.add(book);
        if (book.getAuthor() != null) book.getAuthor().getBooks().remove(book);
        book.setAuthor(this);
    }
    //method to add books to books

}