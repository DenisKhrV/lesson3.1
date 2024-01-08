package pro.sky.lesson31;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import pro.sky.lesson31.controllers.BookController;
import pro.sky.lesson31.model.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTestsExample {
    @LocalServerPort
    private int port;

    @Autowired
    private BookController bookController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(bookController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
//				.contains()
				.isEqualTo("WebApp is working");
    }
    @Test
    public void testGetBooks() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/books", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostBooks() throws Exception {
        Book book = new Book();
        book.setName("TEST");
        book.setAuthor("AUTHOR");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/books", book, String.class))
                .isNotNull();
//        Assertions
//                .assertThat(this.restTemplate.delete("http://localhost:" + port + "/books/" + book.getId(), book, String.class))
//                .isNotNull();
    }
}
