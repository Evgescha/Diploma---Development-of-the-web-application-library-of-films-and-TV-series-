package com.hescha.cinemalibrary.controller;

import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.service.ItemService;
import com.hescha.cinemalibrary.service.RoleService;
import com.hescha.cinemalibrary.service.SecurityService;
import com.hescha.cinemalibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping(UserController.CURRENT_ADDRESS)
public class UserController {
    public static final String API_PATH = "user";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final UserService service;
    private final SecurityService securityService;
    private final ItemService itemService;
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("entity", securityService.getLoggedIn());
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new User());
        } else {
            model.addAttribute("entity", service.read(id));
        }

        model.addAttribute("item_list", itemService.readAll());
        model.addAttribute("role_list", roleService.readAll());

        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute User entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                service.registerNew(entity);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS;
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
            return REDIRECT_TO_ALL_ITEMS + "/" + entity.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
    @GetMapping("/list/{itemId}/{action}/{listType}")
    public String manageUserList(@PathVariable("itemId") Long itemId,
                                 @PathVariable("action") String action,
                                 @PathVariable("listType") String listType) {
        // Получение текущего пользователя
        User currentUser = securityService.getLoggedIn();
        // Получение элемента по itemId
        Item item = itemService.read(itemId);
        // Обработка действий add или remove
        if ("add".equalsIgnoreCase(action)) {
            // Удаление элемента из всех списков
            currentUser.getFavouritesItems().remove(item);
            currentUser.getFeatureItems().remove(item);
            currentUser.getInprogresItems().remove(item);
            currentUser.getWatchedItems().remove(item);

            // Добавление элемента в выбранный список
            if ("favourites".equalsIgnoreCase(listType)) {
                currentUser.getFavouritesItems().add(item);
            } else if ("feature".equalsIgnoreCase(listType)) {
                currentUser.getFeatureItems().add(item);
            } else if ("inprogres".equalsIgnoreCase(listType)) {
                currentUser.getInprogresItems().add(item);
            } else if ("watched".equalsIgnoreCase(listType)) {
                currentUser.getWatchedItems().add(item);
            }
        } else if ("remove".equalsIgnoreCase(action)) {
            if ("favourites".equalsIgnoreCase(listType)) {
                currentUser.getFavouritesItems().remove(item);
            } else if ("feature".equalsIgnoreCase(listType)) {
                currentUser.getFeatureItems().remove(item);
            } else if ("inprogres".equalsIgnoreCase(listType)) {
                currentUser.getInprogresItems().remove(item);
            } else if ("watched".equalsIgnoreCase(listType)) {
                currentUser.getWatchedItems().remove(item);
            }
        }

        // Сохранение обновленного пользователя
        service.update(currentUser);

        // Возвращение пользователя на страницу элемента
        return "redirect:/item/" + itemId;
    }

}
