package com.bookstoreapplication.repository;

import com.bookstoreapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Ability to provide CRUD operations and create table for given entity
 */
@Repository
public interface BookStoreRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where book_name like :bookName%", nativeQuery = true)
    List<Book> findByBookName(String bookName);

    @Query(value = "select * from book where author_name like :authorName%", nativeQuery = true)
    List<Book> findByBookAuthorName(String authorName);

    @Query(value = "select * from book order by price", nativeQuery = true)
    List<Book> getSortedListOfBooksInAsc();

    @Query(value = "select * from book order by price desc", nativeQuery = true)
    List<Book> getSortedListOfBooksInDesc();


}
