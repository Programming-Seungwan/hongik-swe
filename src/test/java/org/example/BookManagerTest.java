package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
        System.out.println("Book Manager 인스턴스 생성");
    }

    @Test
    void addOneBook() {
        System.out.println("책 1 저장 시작");
        Book addingOneBook = new Book(1501L, "자바 기초", "Jane", 2021);
        bookManager.addBook(addingOneBook);
        Book insertedOneBook = bookManager.searchBook(1501L);
        assertEquals(insertedOneBook, addingOneBook);
        System.out.println("id :" + insertedOneBook.getId()+ " 제목 :" + insertedOneBook.getTitle() + " 저자 :" + insertedOneBook.getAuthor() + " 출판 년도 :" + insertedOneBook.getReleaseYear() + " 도서가 추가되었습니다.");
    }

    @Test
    void addTwoBook() {
        System.out.println("책 2 저장 시작");
        Book addingTwoBook = new Book(1502L, "소프트웨어 공학", "TOM", 2014);
        bookManager.addBook(addingTwoBook);
        Book insertedTwoBook = bookManager.searchBook(1502L);
        assertEquals(insertedTwoBook, addingTwoBook);
        System.out.println("id :" + insertedTwoBook.getId()+ " 제목 :" + insertedTwoBook.getTitle() + " 저자 :" + insertedTwoBook.getAuthor() + " 출판 년도 :" + insertedTwoBook.getReleaseYear() + " 도서가 추가되었습니다.");
    }

    @Test
    void addThirdBook() {
        System.out.println("책 3 저장 시작");
        Book addingThirdBook = new Book(1503L, "분산 컴퓨팅", "YOON", 2024);
        bookManager.addBook(addingThirdBook);
        Book insertedThirdBook = bookManager.searchBook(1503L);
        assertEquals(insertedThirdBook, addingThirdBook);
        System.out.println("id :" + insertedThirdBook.getId()+ " 제목 :" + insertedThirdBook.getTitle() + " 저자 :" + insertedThirdBook.getAuthor() + " 출판 년도 :" + insertedThirdBook.getReleaseYear() + " 도서가 추가되었습니다.");
    }

    @Test
    void addAlreadyBook(){
        Book alreadyBook = new Book(1503L, "분산 컴퓨팅", "YOON", 2024);
        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(alreadyBook));
        System.out.println("id :" + alreadyBook.getId()+ " 제목 :" + alreadyBook.getTitle() + " 저자 :" + alreadyBook.getAuthor() + " 출판 년도 :" + alreadyBook.getReleaseYear() + " 도서는 이미 있습니다.");
    }

    @Test
    void searchBook() {
        Long id = 1503L;
        Book threeBook = bookManager.searchBook(id);
        assertEquals(id , threeBook.getId());
        System.out.println("id :" + threeBook.getId()+ " 제목 :" + threeBook.getTitle() + " 저자 :" + threeBook.getAuthor() + " 출판 년도 :" + threeBook.getReleaseYear() + " 도서를 찾았습니다!.");
    }

    @Test
    void searchNoBook(){
        Long noId = 1505L;
        assertThrows(IllegalArgumentException.class, () -> bookManager.searchBook(noId));

        System.out.println(noId + "에 대한 책은 없습니다");
    }

    @Test
    void deleteBook() {
        Long deletingBookId = 1502L;
        System.out.println(deletingBookId + "id 책 삭제 시작");
        bookManager.deleteBook(deletingBookId);
        assertThrows(IllegalArgumentException.class, () -> bookManager.searchBook(deletingBookId));
        System.out.println(deletingBookId + "의 id를 가진 책은 삭제됐습니다");

    }

    @Test
    void deleteNoBook(){
        Long deletingNoBookId = 1507L;
        assertThrows(IllegalArgumentException.class, () -> bookManager.deleteBook(deletingNoBookId));
        System.out.println(deletingNoBookId + " id를 가진 삭제할 책은 없습니다");
    }
}