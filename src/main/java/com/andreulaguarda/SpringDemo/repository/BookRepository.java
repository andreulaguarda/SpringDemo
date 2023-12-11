package com.andreulaguarda.SpringDemo.repository;

import com.andreulaguarda.SpringDemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
