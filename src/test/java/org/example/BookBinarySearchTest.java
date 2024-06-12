package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 각 테스트 인스턴스당 하나의 인스턴스를 사용
class BookBinarySearchTest {

    private BookManager bookManager;

    @BeforeAll
    void setUp() {
        bookManager = new BookManager();

        // 테스트용 책 1000권 추가
        for (long i = 1; i <= 1000; i++) {
            bookManager.addBook(new Book(i, "Book " + i, "Author" + i, 2000 + (int)(i % 20)));
        }
    }

    @Test
    void testSearchBookBinarySearchPerformance() {
        long bookIdToSearch = 500L;

        // 성능 측정 시작
        long startTime = System.nanoTime();

        // 이진 탐색 수행
        Book book = bookManager.search_bs(bookIdToSearch);

        // 성능 측정 종료
        long endTime = System.nanoTime();

        // 수행 시간 계산
        long duration = endTime - startTime;

        System.out.println("Binary search time: " + duration + " nanoseconds");

        // 검색 결과 검증
        assertNotNull(book, "Book should not be null");
        assertEquals(bookIdToSearch, book.getId(), "Book ID should match the searched ID");
    }

    @Test
    void testSearchBookBinarySearchPerformanceForNonExistingBook() {
        // 존재하지 않는 책의 id
        long nonExistingBookId = 1500L;

        // 성능 측정 시작
        long startTime = System.nanoTime();

        // 이진 탐색 수행 및 예외 확인
        assertThrows(IllegalArgumentException.class, () -> {
            bookManager.search_bs(nonExistingBookId);
        });

        // 성능 측정 종료
        long endTime = System.nanoTime();

        // 수행 시간 계산
        long duration = endTime - startTime;

        System.out.println("Binary search time for non-existing book: " + duration + " nanoseconds");
    }

    @Test
    void testSearchBookBinarySearchPerformanceForMultipleBooks() {
        // 각 책들의 탐색 시간을 기록할 리스트 자료구조
        List<Long> searchDurations = new ArrayList<>();

        for (long i = 1; i <= 1000; i++) {
            long startTime = System.nanoTime();
            // 이진 탐색을 통해 매 책들을 찾는다
            Book book = bookManager.search_bs(i);
            long endTime = System.nanoTime();
            searchDurations.add(endTime - startTime);

            assertNotNull(book, "Book should not be null");
            assertEquals(i, book.getId(), "Book ID should match the searched ID");
        }

        long totalDuration = searchDurations.stream().mapToLong(Long::longValue).sum();
        // 평균 시간을 측정
        long averageDuration = totalDuration / searchDurations.size();

        System.out.println("Average binary search time for multiple books: " + averageDuration + " nanoseconds");
    }
}
