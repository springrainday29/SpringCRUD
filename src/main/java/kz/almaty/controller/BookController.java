package kz.almaty.controller;


import kz.almaty.model.Book;
import kz.almaty.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required=true)
    @Qualifier(value = "bookService")
    public  void setBookService(BookService bookService){
        this.bookService=bookService;}
        @RequestMapping(value= "pages",method=RequestMethod.GET)
        public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listbooks",this.bookService.listBooks());
        return "pages";
        }

        @RequestMapping(value= "/pages/add",method=RequestMethod.POST)
        public String addBook(@ModelAttribute("book") Book  book){
        if(book.getId()==0){
            this.bookService.addBook(book);
        }else{
            this.bookService.updateBook(book);
        }
        return "redirect:/books";
        }

        @RequestMapping("/remove/id")
        public String remove(@PathVariable("id") int id) {
        this.bookService.removeBook(id);
            return "redirect:/books";
        }

        @RequestMapping("edit/{id}")
        public String editBook(@PathVariable("id") int   id, Model  model){
        model.addAttribute("book",this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "pages";
        }

        @RequestMapping("bookdata/{id}")
        public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book",this.bookService.getBookById(id));
        return "bookdata";
        }




}
