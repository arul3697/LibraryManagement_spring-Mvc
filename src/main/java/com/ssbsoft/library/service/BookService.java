package com.ssbsoft.library.service;


import com.ssbsoft.library.model.*;

import java.util.List;

public interface BookService {
	Book add(Book book);

	Request addRequest(Request request);

	AdminReply addAdminReply(AdminReply adminReply);

	Notification addNotification(Notification notification);

	List<Book> viewBook();

	List<Request> viewRequest();

	List<AdminReply> viewAdminReply();

	List<LibrarianRequest> viewLibrarianRequest();

	List<Notification> viewNotification();

	void disableBook(Book book);

	Book getBookId(int id);

	void updateBook(Book book);

	Book getFileName(String fileName);

    LibrarianRequest addLibrarianRequest(LibrarianRequest librarianRequest);

}
