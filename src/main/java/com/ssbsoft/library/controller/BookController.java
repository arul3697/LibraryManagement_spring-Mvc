package com.ssbsoft.library.controller;


import com.ssbsoft.library.common.Constants;
import com.ssbsoft.library.model.AdminReply;
import com.ssbsoft.library.model.Book;
import com.ssbsoft.library.model.Request;
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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping( value = "/addOrUpdateBook" , method = RequestMethod.POST)
    public String addOrUpdateBook(@ModelAttribute("book") Book book,ModelMap modelMap ,HttpServletRequest request,
                                  HttpServletResponse response, @RequestParam("booksPdf") MultipartFile multipartFile) throws IOException, ServletException {

        if(book.getId() == 0) {
        book.setAccount_enabled(true);
        book.setDownload_count(0);
        String folderName = "resource";
        String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
        File dir = new File(uploads);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        Part filePart = request.getPart("booksPdf");
        String fileName =multipartFile.getOriginalFilename();
        String author = book.getAuthor_name();
        String path = folderName + File.separator + fileName;

        book.setFileName(fileName);
        book.setPath(path);
        InputStream inputStream = filePart.getInputStream();
        Files.copy(inputStream, Paths.get(uploads + File.separator + fileName ), StandardCopyOption.REPLACE_EXISTING);

            bookService.add(book);
            modelMap.addAttribute("success", Constants.BOOK_ADDED);

      }else {
            book.setAccount_enabled(true);
            book.setDownload_count(0);
            String folderName = "resource";
            String uploads = request.getServletContext().getRealPath("") + File.separator + folderName;
            File dir = new File(uploads);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            Part filePart = request.getPart("booksPdf");
            String fileName =multipartFile.getOriginalFilename();
            String author = book.getAuthor_name();
            String path = folderName + File.separator + fileName;

            book.setFileName(fileName);
            book.setPath(path);
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploads + File.separator + fileName ), StandardCopyOption.REPLACE_EXISTING);
            bookService.add(book);
            modelMap.addAttribute("success", Constants.BOOK_UPDATED);
      }
        return "redirect:/book/viewBook";
    }
    @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable int id,ModelMap modelMap) {
        Book book = bookService.getBookId(id);
        modelMap.addAttribute("book",book);
        return "addbook";
    }
    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id,ModelMap modelMap) {
        Book book = bookService.getBookId(id);
        book.setAccount_enabled(false);
        bookService.add(book);
        modelMap.addAttribute("deleteMessage", Constants.BOOK_DELETE);
        return "redirect:/book/viewBook";
    }
    @RequestMapping(value = "/viewBook", method = RequestMethod.GET)
    public String viewBook(ModelMap modelMap,@RequestParam(value ="success", required = false)String success,@RequestParam(value ="deleteMessage", required = false)String deleteMessage) {
        List<Book> books = bookService.viewBook();
        modelMap.addAttribute("view_id", 2);
        modelMap.addAttribute("books",books);
        modelMap.addAttribute("success",success);
        modelMap.addAttribute("deleteMessage",deleteMessage);
        return "admin";
    }

    @RequestMapping(value = "/viewRequest", method = RequestMethod.GET)
    public String viewRequest(ModelMap modelMap) {
        List<Request> request = bookService.viewRequest();
        modelMap.addAttribute("view_id", 4);
        modelMap.addAttribute("request",request);
        return "admin";
    }

    @RequestMapping(value = "/userViewBook", method = RequestMethod.GET)
    public String viewBooks(ModelMap modelMap,@RequestParam(value = "request", required = false) String request,@RequestParam(value = "error", required = false) String error) {
        List<Book> books = bookService.viewBook();
        modelMap.addAttribute("view_id", 3);
        modelMap.addAttribute("book", books);
        modelMap.addAttribute("success", request);
        modelMap.addAttribute("error", error);
        return "user";
    }

    @RequestMapping(value = "/addbook")
    public String add() {
        return "addbook";
    }

    @RequestMapping(value = "/request")
    public String request() {
        return "request";
    }

    @RequestMapping( value = "/requestAdd" , method = RequestMethod.POST)
    public String requestAdd(@ModelAttribute("request") Request request, ModelMap modelMap) {

        bookService.addRequest(request);
        modelMap.addAttribute("request", Constants.REQUEST);
        return "redirect:/book/userViewBook";
    }

    @RequestMapping(value = "/adminReplyBox")
    public String adminReply() {
        return "adminReplyBox";
    }

    @RequestMapping(value = "/adminReplyAdd", method = RequestMethod.POST)
    public String  adminReplyAdd(@ModelAttribute("adminReply") AdminReply adminReply, ModelMap modelMap) {
        bookService.addAdminReply(adminReply);
        modelMap.addAttribute("success_reply", Constants.ADMIN_REPLY);
        return "redirect:/user/admin";
    }

    @RequestMapping(value = {"/download/{id}"}, method = RequestMethod.GET)
    public String downloadBook(@PathVariable int id, HttpServletResponse response,HttpServletRequest request,ModelMap modelMap) throws IOException, ServletException {

         int BUFFER_SIZE = 1024 * 100;
         String UPLOAD_DIR = "resource";
         String fileName = null;
        try {
            Book book = bookService.getBookId(id);
            if(book.getId()!=0){
                book.setFileName(book.getFileName());
                book.setPath(book.getPath());
                int initial = book.getDownload_count();
               int sum = initial+1;
               book.setDownload_count(sum);
               bookService.add(book);
                }
            fileName = book.getFileName();

            if (fileName == null || fileName.equals("")) {
                /**
                 * *** Set Response Content Type ****
                 */
                response.setContentType("text/html");

                /**
                 * *** Print The Response ****
                 */
                response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
            }else {
                String applicationPath = request.getServletContext().getRealPath("");
                String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
                String filePath = downloadPath + File.separator + fileName;
                System.out.println(fileName);
                System.out.println(filePath);
                System.out.println("fileName:" + fileName);
                System.out.println("filePath :" + filePath);
                File file = new File(filePath);
                OutputStream outStream = null;
                FileInputStream inputStream = null;

               // if (file.exists()) {

                    /**
                     * ** Setting The Content Attributes For The Response Object
                     * ***
                     */
                    String mimeType = "application/octet-stream";
                    response.setContentType(mimeType);

                    /**
                     * ** Setting The Headers For The Response Object ***
                     */
                    String headerKey = "Content-Disposition";
                    String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                    response.setHeader(headerKey, headerValue);

                    try {

                        /**
                         * ** Get The Output Stream Of The Response ***
                         */
                        outStream = response.getOutputStream();
                        inputStream = new FileInputStream(file);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int bytesRead = -1;

                        /**
                         * ** Write Each Byte Of Data Read From The Input Stream
                         * Write Each Byte Of Data Read From The Input Stream Into
                         * The Output Stream ***
                         */
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outStream.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException ioExObj) {
                        System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }

                        outStream.flush();
                        if (outStream != null) {
                            outStream.close();
                        }
                    }
              //  } else {

                    /**
                     * *** Set Response Content Type ****
                     */
                    response.setContentType("text/html");

                    /**
                     * *** Print The Response ****
                     */
                    response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
                }

           // }

        } catch (Exception ex) {
            System.out.println(ex);
            modelMap.addAttribute("error", Constants.DOWNLOAD_FAILED);
            return "redirect:/book/userViewBook";
        }
        return null;
    }
}
