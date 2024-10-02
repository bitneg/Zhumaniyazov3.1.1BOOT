package zhumaniyazov.boot.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhumaniyazov.boot.model.User;
import zhumaniyazov.boot.service.UserService;



@Controller
public class UserController {
    private final UserService userService;
    private static final String REDIRECT_USERS = "redirect:/users";
    private static final String USER_LIST = "userlist";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        logger.info("Получение списка пользователей");
        return USER_LIST;
    }

    @PostMapping("/users/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            logger.warn("Ошибка валидации при добавлении пользователя: {}", user);
            return USER_LIST;
        }
        userService.save(user);
        logger.info("Пользователь добавлен: {}", user);
        return REDIRECT_USERS;
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam int id) {
        userService.delete(id);
        logger.info("Пользователь с ID {} удален", id);
        return REDIRECT_USERS;
    }

    @PostMapping("/users/update")
    public String updateUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.warn("Ошибка валидации при обновлении пользователя: {}", user);
            model.addAttribute("user", user);
            model.addAttribute("users", userService.findAll());
            return USER_LIST;
        }
        userService.update(user);
        logger.info("Пользователь обновлен: {}", user);
        return REDIRECT_USERS;
    }
}