package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","공부좀하자~!");
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(String name, int age, Model model){
        Human human = new Human(name, age);
        model.addAttribute("human", human);
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3(Human human, Model model){
        model.addAttribute("human", human);
        return "hello2";
    }
}
class Human {
    private String name;
    private int age;

    public Human() {}

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}