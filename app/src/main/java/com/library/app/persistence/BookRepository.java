
package com.library.app.persistence;

import java.util.List;

import com.library.domain.books.Book;

public interface BookRepository {
	List<Book> getBookList();

	Book getBook(Long id);
}
