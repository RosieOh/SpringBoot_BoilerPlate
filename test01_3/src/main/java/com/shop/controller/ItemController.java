package com.shop.controller;

import com.shop.domain.Item;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public String getItemAll(Model model){
        List<Item> itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        return "item/items";
    }

    @GetMapping("/get/{itemId}")
    public String getItem(@PathVariable long itemId, Model model){
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        int cnt = itemService.save(item);
        Item addedItem = itemService.getLatestItem();
        redirectAttributes.addAttribute("itemId", addedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/item/items";
    }
}
