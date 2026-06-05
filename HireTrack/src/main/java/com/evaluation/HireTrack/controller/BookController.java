package com.evaluation.HireTrack.controller;

import com.evaluation.HireTrack.dto.BookReqDto;
import com.evaluation.HireTrack.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("api/book/add/{id}")
    public void addBook(@Valid  @RequestBody BookReqDto dto,
                        @PathVariable int authorId){
        bookService.addBook(dto,authorId);
    }
}
