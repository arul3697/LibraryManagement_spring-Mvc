package com.ssbsoft.library.dao;

import com.ssbsoft.library.model.*;

import java.util.List;



public interface BookDao {
	List<Book> viewBook();

	void disableBook(Book book);

	Book add(Book book);

	Book getBookId(int id);

	void updateBook(Book book);

	Book getFileName(String fileName);

    Request addRequest(Request request);

	List<Request> viewRequest();

    LibrarianRequest addLibrarianRequest(LibrarianRequest librarianRequest);

	List<LibrarianRequest> viewLibrarianRequest();

	AdminReply addAdminReply(AdminReply adminReply);

	List<AdminReply> viewAdminReply();

	Notification addNotification(Notification notification);

	List<Notification> viewNotification();
}
