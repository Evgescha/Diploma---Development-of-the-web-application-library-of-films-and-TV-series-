package com.hescha.cinemalibrary.controller;

import com.hescha.cinemalibrary.model.Comment;
import com.hescha.cinemalibrary.model.Comment;
import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.service.CommentService;
import com.hescha.cinemalibrary.service.ItemService;
import com.hescha.cinemalibrary.service.SecurityService;
import com.hescha.cinemalibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
@RequestMapping(CommentController.CURRENT_ADDRESS)
public class CommentController {
    public static final String API_PATH = "comment";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";

    private final CommentService service;

    private final UserService userService;
    private final ItemService itemService;
    private final SecurityService securityService;

    @PostMapping
    public String save(@ModelAttribute Comment entity, Integer itemId, RedirectAttributes ra) {
        Item item = itemService.read(itemId);
        User loggedIn = securityService.getLoggedIn();
        if (entity.getId() == null) {
            try {
                entity.setItem(item);
                entity.setOwner(loggedIn);
                Comment createdEntity = service.create(entity);

                item.getComments().add(createdEntity);
                itemService.update(item);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
        } else {
            try {
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
        }
        return "redirect:/item/" + entity.getItem().getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        Long itemId = service.read(id).getItem().getId();
        try {
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return "redirect:/item/" + itemId;
    }
}
