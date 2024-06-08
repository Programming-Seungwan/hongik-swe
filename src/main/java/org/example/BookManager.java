package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookManager {

    // 책을 ID로 저장할 ConcurrentMap
    private static ConcurrentMap<Long, Book> store = new ConcurrentHashMap<>();
    // 이진 탐색을 위해 책들을 정렬된 상태로 유지할 리스트
    private static List<Book> sortedBooks = new ArrayList<>();

    // 책을 저장소에 추가하는 메서드
    public void addBook(Book book) {
        if (store.containsKey(book.getId())) {
            throw new IllegalArgumentException("이미 해당 ID를 가진 책이 존재합니다.");
        }
        store.put(book.getId(), book);
        sortedBooks.add(book);
        // 책 ID를 기준으로 리스트를 정렬
        Collections.sort(sortedBooks, Comparator.comparingLong(Book::getId));
    }

    // 책 ID로 책을 검색하는 메서드
    public Book searchBook(Long bookId) {
        if (!store.containsKey(bookId)) {
            throw new IllegalArgumentException("해당 ID를 가진 책이 존재하지 않습니다.");
        }
        return store.get(bookId);
    }

    // 책 ID로 책을 삭제하는 메서드
    public void deleteBook(Long bookId) {
        if (!store.containsKey(bookId)) {
            throw new IllegalArgumentException("해당 ID를 가진 책이 존재하지 않습니다.");
        }
        Book removedBook = store.remove(bookId);
        sortedBooks.remove(removedBook);
    }
}
