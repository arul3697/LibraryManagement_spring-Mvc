package com.ssbsoft.library.service.impl;

import com.ssbsoft.library.dao.BookDao;
import com.ssbsoft.library.model.*;
import com.ssbsoft.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	/**
	 * add book in list
	 * 
	 * @param book object
	 * @return
	 * @throws Exception
	 */
	public Book add(Book book) {
		return bookDao.add(book);
	}

	@Override
	public Request addRequest(Request request) {
		return bookDao.addRequest(request);
	}

	@Override
	public AdminReply addAdminReply(AdminReply adminReply) {
		return bookDao.addAdminReply(adminReply);
	}

	@Override
	public Notification addNotification(Notification notification) {
		return bookDao.addNotification(notification);
	}

	/**
	 * List of Book
	 * 
	 * @return boolean value
	 * @throws Exception
	 */
	public List<Book> viewBook() {
		return bookDao.viewBook();
	}

	public List<Request> viewRequest() {
		return bookDao.viewRequest();
	}

	@Override
	public List<AdminReply> viewAdminReply() {
		return bookDao.viewAdminReply();
	}

	@Override
	public List<LibrarianRequest> viewLibrarianRequest() {
		return bookDao.viewLibrarianRequest();
	}

	@Override
	public List<Notification> viewNotification() {
		return bookDao.viewNotification();
	}

	/**
	 * delete book
	 *
	 * @return boolean value
	 * @throws Exception
	 */
	public void disableBook(Book book) {
		bookDao.disableBook(book);
	}

	@Override
	public Book getBookId(int id) {
		return bookDao.getBookId(id);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);

	}

	@Override
	public Book getFileName(String fileName) {
		return bookDao.getFileName(fileName);
	}

	@Override
	public LibrarianRequest addLibrarianRequest(LibrarianRequest librarianRequest) {
		return bookDao.addLibrarianRequest(librarianRequest);
	}
}
