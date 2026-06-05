package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.dto.BookReqDto;
import com.evaluation.HireTrack.model.Author;
import com.evaluation.HireTrack.model.Book;
import com.evaluation.HireTrack.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void addBook(BookReqDto dto,int authorId) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setSummary(dto.summary());

        bookRepository.save(book);

    }
}
