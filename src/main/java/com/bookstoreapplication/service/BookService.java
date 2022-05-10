package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.exception.BookStoreException;
import com.bookstoreapplication.model.Book;
import com.bookstoreapplication.repository.BookStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

/**
 * Created BookService class to serve api calls done by controller layer
 */
public class BookService implements IBookService{

    /**
     * Autowired BookStoreRepository interface to inject its dependency here
     */
    @Autowired
    BookStoreRepository bookStoreRepository;

    /**
     * create a method name as createBook
     * Ability to save book details to repository
     * @param bookDTO - all book data
     * @return - save all data
     */
    @Override
    public Book createBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO);
        return  bookStoreRepository.save(newBook);

    }

    /**
     * create a method name as getBookDataById
     * - Ability to get book data by id
     * @param BookId - book id
     * @return - book data by id
     */
    @Override
    public Optional<Book> getBookDataById(int BookId) {
        Optional<Book> getBook=bookStoreRepository.findById(BookId);
        if(getBook.isEmpty()){
            throw new BookStoreException("Book Store Details for id not found");
        }
        return getBook;

    }

    /**
     * create a method name as getAllBookData
     * - Ability to get all book' data by findAll() method
     * @return - all data
     */
    @Override
    public List<Book> getAllBookData() {
        List<Book> getBooks=bookStoreRepository.findAll();
        return getBooks;
    }


    /**
     * create a method name as updateRecordById
     * Ability to update book data for particular id
     * @param BookId - book id
     * @param bookDTO - book data
     * @return - updated book information in JSON format
     */
    @Override
    public Book updateRecordById(Integer BookId, BookDTO bookDTO) {

        Optional<Book> updateBook = bookStoreRepository.findById(BookId);
        if (updateBook.isEmpty()) {
            throw new BookStoreException("Book record does not found");
        } else {
            Book updateUser = new Book(BookId, bookDTO);
            bookStoreRepository.save(updateUser);
            return updateUser;

        }
    }

    /**
     * create a method name as deleteRecordById
     * ability to delete data by particular book id
     * @param BookId - book id
     * @return - bookId and Acknowledgment message
     */
    @Override
    public String deleteRecordById(int BookId) {
        Optional<Book> newBook = bookStoreRepository.findById(BookId);
        if (newBook.isEmpty()) {
            throw new BookStoreException("Book record does not found");
        } else {
            bookStoreRepository.deleteById(BookId);
        }
        return "data deleted succesfull";
    }

    /**
     * create a method name as getBookByName
     * ability to get Book data by particular book name
     * @param bookName -bookName
     * @return -return book Data
     */
    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> findBook= bookStoreRepository.findByBookName(bookName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }

    /**
     * create a method name as sortedListOfBooksInAscendingOrder
     * ability to sort the Books by its Price in Ascending Order
     * @return -return books in ascending order
     */

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList=  bookStoreRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    /**
     * create a method name as sortedListOfBooksInDescendingOrder
     * ability to sort the Books by its Price in Descending Order
     * @return -return books in descending order
     */

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc=  bookStoreRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }


}


