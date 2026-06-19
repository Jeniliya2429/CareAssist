package com.evaluation.HireTrack.repository;

import com.evaluation.HireTrack.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
