package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbu.bg.logisticscompany.model.dto.UserUpdateDto;
import nbu.bg.logisticscompany.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * Update order string.
     *
     * @param id     the id
     * @param user   the user
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PutMapping("/user/{id}")
    public String updateOrder(@PathVariable("id") String id, @Valid @ModelAttribute UserUpdateDto user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("has errors");
            return "profile";
        }
        userService.updateUser(id, user);
        return "redirect:/logout";
    }

    /**
     * Profile page string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/profile")
    public String profilePage(Model model) {
        return "profile";
    }
}
