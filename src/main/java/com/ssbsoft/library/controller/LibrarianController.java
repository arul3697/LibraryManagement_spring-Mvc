package com.ssbsoft.library.controller;


import com.ssbsoft.library.common.Constants;
import com.ssbsoft.library.model.*;
import com.ssbsoft.library.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {
    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping(value = "/addOrUpdateBook", method = RequestMethod.POST)
    public String addOrUpdateBook(@ModelAttribute("book") Book book, ModelMap modelMap, HttpServletRequest request,
                                  HttpServletResponse response, @RequestParam("booksPdf") MultipartFile multipartFile) throws IOException, ServletException {

        if (book.getId() == 0) {
            book.setAccount_enabled(true);
            book.setDownload_count(0);
            String folderName = "resource";
            String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
            File dir = new File(uploads);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Part filePart = request.getPart("booksPdf");
            String fileName = multipartFile.getOriginalFilename();
            String author = book.getAuthor_name();
            String path = folderName + File.separator + fileName;

            book.setFileName(fileName);
            book.setPath(path);
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploads + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

            bookService.add(book);
            modelMap.addAttribute("success", Constants.BOOK_ADDED);

        } else {
            book.setAccount_enabled(true);
            book.setDownload_count(0);
            String folderName = "resource";
            String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
            File dir = new File(uploads);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Part filePart = request.getPart("booksPdf");
            String fileName = multipartFile.getOriginalFilename();
            String author = book.getAuthor_name();
            String path = folderName + File.separator + fileName;

            book.setFileName(fileName);
            book.setPath(path);
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploads + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
            bookService.add(book);
            modelMap.addAttribute("success", Constants.BOOK_UPDATED);
        }
        return "redirect:/librarian/viewBook";
    }

    @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable int id, ModelMap modelMap) {
        Book book = bookService.getBookId(id);
        modelMap.addAttribute("book", book);
        return "addLibrarianBook";
    }

    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id, ModelMap modelMap) {
        Book book = bookService.getBookId(id);
        book.setAccount_enabled(false);
        bookService.add(book);
        modelMap.addAttribute("deleteMessage", Constants.BOOK_DELETE);
        return "redirect:/librarian/viewBook";
    }

    @RequestMapping(value = "/viewBook", method = RequestMethod.GET)
    public String viewBook(ModelMap modelMap, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "deleteMessage", required = false) String deleteMessage) {
        List<Book> books = bookService.viewBook();
        modelMap.addAttribute("view_id", 2);
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("success", success);
        modelMap.addAttribute("deleteMessage", deleteMessage);
        return "librarian";
    }

    @RequestMapping(value = "/viewRequest", method = RequestMethod.GET)
    public String viewRequest(ModelMap modelMap) {
        List<Request> request = bookService.viewRequest();
        modelMap.addAttribute("view_id", 4);
        modelMap.addAttribute("request", request);
        return "librarian";
    }

    @RequestMapping(value = "/viewAdminReply", method = RequestMethod.GET)
    public String viewAdminReply(ModelMap modelMap) {
        List<AdminReply> adminReply = bookService.viewAdminReply();
        modelMap.addAttribute("view_id", 6);
        modelMap.addAttribute("reply", adminReply);
        return "librarian";
    }

    @RequestMapping(value = "/addLibrarianBook")
    public String add() {
        return "addLibrarianBook";
    }

    @RequestMapping(value = "/request")
    public String request() {
        return "librarianTextBox";
    }

    @RequestMapping(value = "/requestAdd", method = RequestMethod.POST)
    public String requestAdd(@ModelAttribute("librarianRequest") LibrarianRequest librarianRequest, ModelMap modelMap) {

        bookService.addLibrarianRequest(librarianRequest);
        modelMap.addAttribute("success", Constants.LIBRARIANREQUEST);
        return "redirect:/librarian/viewBook";
    }

    @RequestMapping(value = "/viewLibrarianRequest", method = RequestMethod.GET)
    public String viewLibrarianRequest(ModelMap modelMap) {
        List<LibrarianRequest> librarianRequest = bookService.viewLibrarianRequest();
        modelMap.addAttribute("view_id", 5);
        modelMap.addAttribute("request", librarianRequest);
        return "admin";
    }

    @RequestMapping(value = "/userNotification")
    public String userNotification() {
        return "notification";
    }


    @RequestMapping(value = "/notificationAdd", method = RequestMethod.POST)
    public String addNotification(@ModelAttribute("notification") Notification notification, ModelMap modelMap) {
        bookService.addNotification(notification);
        modelMap.addAttribute("success", Constants.NOTIFICATION);
        return "redirect:/librarian/viewBook";
    }
    @RequestMapping(value = "/viewNotification", method = RequestMethod.GET)
    public String viewNotification(ModelMap modelMap) {
        List<Notification> notification = bookService.viewNotification();
        modelMap.addAttribute("view_id", 7);
        modelMap.addAttribute("notification", notification);
        return "user";
    }

}