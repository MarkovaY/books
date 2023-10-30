package bg.softuni.books.service.impl;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.BookRepository;
import bg.softuni.books.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll().stream().map(BooksServiceImpl::mapBookToDTO).toList();
    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {

        return bookRepository.findById(id).map(BooksServiceImpl::mapBookToDTO);
    }

    private static BookDTO mapBookToDTO(BookEntity bookEntity) {

        AuthorDTO authorDTO = new AuthorDTO().setName(bookEntity.getAuthor().getName());
        return new BookDTO()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setAuthor(authorDTO)
                .setIsbn(bookEntity.getIsbn());
    }
}
