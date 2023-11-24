package com.shop.controller;

import com.shop.entity.Book;
import com.shop.entity.Sample;
import com.shop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/ex")
public class ExamController {

    @Autowired
    private BookService bookService;

    @GetMapping("/sample1")
    public String getSample1(Model model){
        List<String> nameList = Arrays.asList("kim","lee","park","oh");
        Sample sample = new Sample(1000L,"신승원",1004);
        model.addAttribute("sample", sample);
        model.addAttribute("names", nameList);
        return "exam/sample1";
    }

    @GetMapping("/sample2")
    public String getSample2(Model model){
        //Name1~Name10
        List<String> strList = IntStream.range(1,10).mapToObj(i -> "Name"+i).collect(Collectors.toList());
        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();
        map.put("k", "KIM");
        map.put("l", "LEE");
        map.put("p", "PARK");
        map.put("s", "SHIN");
        model.addAttribute("map", map);

        Set<String> set = new HashSet<>();
        set.add("OH");
        set.add("HAN");
        set.add("SEO");
        set.add("LEE");
        set.add("HWANG");
        set.add("HAN");
        model.addAttribute("set", set);

        return "exam/sample2";
    }

    @GetMapping("/sample3")
    public String getSample3(Model model){
        String[] cate = new String[]{"Company","Product","Service","Community"};
        model.addAttribute("cate", cate);
        return "exam/sample3";
    }

    @GetMapping("/sample4")
    public String getSample4(Model model){
        return "exam/sample4";
    }

    @GetMapping("/sample5")
    public String getSample5(Model model, Book book){
        List<Book> bookMap = bookService.readBookAll();
        model.addAttribute("bookMap", bookMap);
        model.addAttribute("book", book);
        return "exam/sample5";
    }

    @PostMapping("/test5")
    public String checkBook(Model model, @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "exam/sample5 :: #form";
        }
        int cnt = bookService.createBook(book);
        List<Book> bookMap = bookService.readBookAll();
        model.addAttribute("bookMap", bookMap);
        return "exam/sample5 :: #list";
    }

}
