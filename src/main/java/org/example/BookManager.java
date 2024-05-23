package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookManager {

    private static ConcurrentMap<Long, Book> store = new ConcurrentHashMap<>();

    // 이미 있는 것이면 추가할 수 없어야함
    public void addBook(Book book) {
        if (store.containsKey(book.getId())) {
            throw new IllegalArgumentException("There is already a book with this id");
        }
        store.put(book.getId(), book);
    }

    // 없는 것이면 존재하지 않는다는 메시지를, 존재하는 것이면 관련 data를 보여줘야 함
    public Book searchBook(Long bookId) {
        if (!store.containsKey(bookId)) {
            throw new IllegalArgumentException("There is no book with this id");
        }

        Book foundBook = store.get(bookId);
        return foundBook;
    }

    // 없는 책을 지우려 하면 없다고 하고, 있으면 지우고 관련 메시지를 반환함
    public void deleteBook(Long bookId) {
        if (!store.containsKey(bookId)) {
            throw new IllegalArgumentException("There is no book to delete with this id");
        }

        store.remove(bookId);
    }
}
