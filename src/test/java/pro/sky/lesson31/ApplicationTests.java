package pro.sky.lesson31;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.lesson31.controllers.BookController;
import pro.sky.lesson31.model.Book;
import pro.sky.lesson31.repositories.BookCoverRepository;
import pro.sky.lesson31.repositories.BookRepository;
import pro.sky.lesson31.services.BookCoverService;
import pro.sky.lesson31.services.BookService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookCoverRepository bookCoverRepository;

    @SpyBean
    private BookService bookService;

    @SpyBean
    private BookCoverService bookCoverService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void saveBookTest() throws Exception {
        final String name = "123123";
        final String author = "asdasd";
        final long id = 1;


        JSONObject bookObject = new JSONObject();
        bookObject.put("name", name);
        bookObject.put("author", author);

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/books")
                        .content(bookObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));

    }

}
