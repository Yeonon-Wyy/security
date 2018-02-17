package top.yeonon.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.junit.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.yeonon.dto.User;
import top.yeonon.dto.UserCondition;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    @JsonView(User.UserSampleView.class)
    public List<User> query(UserCondition condition,
                            @PageableDefault(size = 10, page = 2, sort = "username,asc") Pageable pageable) {
        System.out.println(pageable);
        List<User> userList = new ArrayList<>();
        System.out.println(condition);
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("{id}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("yeonon");
        user.setPassword("124563");
        return user;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        user.setId(2);
        System.out.println(user);
        System.out.println(user.getBirthday());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("delete " + id);
    }

}
