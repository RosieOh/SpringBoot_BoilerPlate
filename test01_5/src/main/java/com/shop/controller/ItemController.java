package com.shop.controller;

import com.shop.domain.Item;
import com.shop.service.ItemService;
import com.shop.util.ItemSaveForm;
import com.shop.util.ItemUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
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
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //특정 필드 아닌 복합 룰 검증
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }

        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            return "item/addForm";
        }

        // 검증 성공
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        int cnt = itemService.save(item);
        Item addedItem = itemService.getLatestItem();
        redirectAttributes.addAttribute("itemId", addedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/item/items";
    }

    @GetMapping("/edit/{itemId}")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/editForm";
    }

    @PostMapping("/edit/{itemId}")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        //특정 필드 아닌 복합 룰 검증
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }

        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            return "item/editForm";
        }

        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());

        itemService.update(itemId, itemParam);
        return "redirect:/item/items";
    }
}
