package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BookList {
    ArrayList<Book> bookList = new ArrayList<>();
    public BookList(){
        bookList.add(new Book("Metro 2033","450","Dimitri Glukhovsky","2002"));
        bookList.add(new Book("2001: A space odyssey","191","Arthur C. Clarke","1968"));
        bookList.add(new Book("Roadside Picnic","102","Arkady and Boris","1972"));
        bookList.add(new Book("Neuromancer","256","William Gibson","1984"));
    }
    public Book getBookByName(String name){
        for(int i = 0; i < bookList.size(); i++){
            if (name.equals(bookList.get(i).getName())){
                return bookList.get(i);
            }
        }
        System.out.println("Book not found");
        return null;
    }
    public Book[] getAllBooks(){
        Book[] bookArray = new Book[bookList.size()];
        for(int i = 0; i < bookList.size(); i++){
            bookArray[i] = bookList.get(i);
        }
        return bookArray;
    }
    public void addBook(Book book){
        if (bookList.contains(book)){
            System.out.println("This book already exists");
        }
        else {
            bookList.add(book);
        }
    }
    public boolean replaceBook(String nameOfTheBookToBeReplaced, Book book){
        for(int i = 0; i < bookList.size(); i++){
            if (nameOfTheBookToBeReplaced.equals(bookList.get(i).getName())){
                bookList.remove(i);
                bookList.add(book);
                return true;
            }
        }
        System.out.println("Unable to find book");
        return false;
    }
    public boolean deleteBook(String name){
        for (int i = 0; i < bookList.size(); i++){
            if (name.equals(bookList.get(i).getName())){
                bookList.remove(i);
                return true;
            }
        }
        System.out.println("Book not found");
        return false;
    }
}
