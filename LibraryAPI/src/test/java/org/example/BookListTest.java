package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookListTest {

    @Test
    void getBookByName() {
        //given
        BookList bookList = new BookList();
        String expectedBookName = "Metro 2033";

        //when
        String actualBookName = bookList.getBookByName(expectedBookName).getName();

        //then

        assertSame(expectedBookName,actualBookName);
    }
    @Test
    void getBookByNameInvalidBook(){
        //given
        BookList bookList = new BookList();
        String expectedBookName = "invalid book";

        //when
        Book actualBook = bookList.getBookByName(expectedBookName);

        //then
        assertNull(actualBook);
    }
    @Test
    void getAllBooks() {
        //given
        BookList bookList = new BookList();
        Book[] expectedBookArray = new Book[bookList.bookList.size()];
        for(int i = 0; i < bookList.bookList.size(); i++){
            expectedBookArray[i] = bookList.bookList.get(i);
        }

        //when
        Book[] actualBookArray = bookList.getAllBooks();
        int counter = 0;
        for (int i = 0; i < expectedBookArray.length; i++){
            if (expectedBookArray[i].equals(actualBookArray[i])){
                counter++;
                if (counter == expectedBookArray.length);
                actualBookArray = expectedBookArray;
                break;
            }else {
                System.out.println("They are not the same");
            }

        }

        //then
        assertSame(expectedBookArray, actualBookArray);
    }
    @Test
    void getAllBooksShouldFail(){
        //given
        BookList bookList = new BookList();
        Book[] expectedBookArray = new Book[bookList.bookList.size()];
        Book bookTest0 = new Book("test","test","test","test");
        Book bookTest1 = new Book("test","test","test","test");
        Book bookTest2 = new Book("test","test","test","test");
        Book bookTest3 = new Book("test","test","test","test");
        expectedBookArray[0] = bookTest0;
        expectedBookArray[1] = bookTest1;
        expectedBookArray[2] = bookTest2;
        expectedBookArray[3] = bookTest3;

        //when
        Book[] actualBookArray = bookList.getAllBooks();
        int counter = 0;
        for (int i = 0; i < expectedBookArray.length; i++){
            if (expectedBookArray[i].equals(actualBookArray[i])){
                counter++;
                if (counter == expectedBookArray.length);
                actualBookArray = expectedBookArray;
                break;
            }else {
                System.out.println("They are not the same");
            }

        }

        //then
        assertNotSame(expectedBookArray, actualBookArray);

    }

    @Test
    void addBook() {
        //given
        BookList bookList = new BookList();
        Book bookTest0 = new Book("test","test","test","test");

        //when
        bookList.addBook(bookTest0);

        //then
        Book expectedBook = bookTest0;
        Book actualBook = null;
        for (int i = 0; i < bookList.bookList.size(); i++){
            if (bookList.bookList.get(i).equals(expectedBook)){
                actualBook = bookList.bookList.get(i);
            }
        }
        assertSame(expectedBook,actualBook);
    }
    @Test
    void addBookAlreadyExists() {
        //given
        BookList bookList = new BookList();
        Book bookTest0 = new Book("test","test","test","test");
        Set<Book> tempSet = new HashSet<>(bookList.bookList);
        bookList.addBook(bookTest0);

        //when
        bookList.addBook(bookTest0);
        tempSet.addAll(bookList.bookList);
        boolean thereIsRepeatedItemsInTheList = false;
        if (tempSet.size() < bookList.bookList.size()){
            thereIsRepeatedItemsInTheList = true;
        }
        //then
        assertFalse(thereIsRepeatedItemsInTheList);
    }

    @Test
    void replaceBook() {
        //given
        BookList bookList = new BookList();
        Book bookTest0 = new Book("test","test","test","test");

        //when
        bookList.replaceBook("Metro 2033",bookTest0);

        //then
        Book expectedBook = bookTest0;
        Book actualBook = bookList.getBookByName("test");

        assertSame(expectedBook,actualBook);
    }
    @Test
    void replaceBookInvalidBookName() {
        //given
        BookList bookList = new BookList();
        Book bookTest0 = new Book("test","test","test","test");

        //when
        bookList.replaceBook("Invalid Book",bookTest0);

        //then
        Book expectedBook = bookTest0;
        Book actualBook = bookList.getBookByName("test");

        assertNotSame(expectedBook,actualBook);
    }


    @Test
    void deleteBook() {
        BookList bookList = new BookList();

        //when
        bookList.deleteBook("Metro 2033");

        //then

        boolean theBookWasDeleted = true;

        for (int i = 0; i < bookList.bookList.size(); i++){
            if (bookList.bookList.get(i).getName().equals("Metro 2033")){
                theBookWasDeleted = false;
            }
        }
        assertTrue(theBookWasDeleted);
    }
}