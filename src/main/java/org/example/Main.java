package org.example;

public class Main {
    public static void main(String[] args) {
        // 새로운 BookManager 인스턴스 생성
        BookManager manager = new BookManager();

        // 매니저에 책들을 추가
        manager.addBook(new Book(1L, "Book One", "Author A", 2020));
        manager.addBook(new Book(2L, "Book Two", "Author B", 2021));
        manager.addBook(new Book(3L, "Book Three", "Author C", 2022));

        try {
            // 이진 탐색을 통해 책을 검색
            Book foundBook = manager.search_bs(2L);
            System.out.println("찾은 책: " + foundBook.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            // 책을 삭제
            manager.deleteBook(2L);
            // 삭제된 책을 다시 검색 시도
            Book foundBook = manager.search_bs(2L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
