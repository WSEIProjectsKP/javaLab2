package pl.karol.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {
    private Map<Integer, UserEntity> users = new HashMap<>();
    private Integer iterator = 1;

    @GetMapping("/users")
//    http://localhost:8080/users
    @ResponseBody
    public Object getUsers() {
        List<UserEntity> list = new ArrayList<>();
        for (UserEntity val : users.values()) {
            list.add(val);
        }
        return list;
    }

    @GetMapping("/users/{id}/get")
//    http://localhost:8080/users/2/get
    @ResponseBody
    public Object getUser(@PathVariable Integer id) {
        for (Integer key : users.keySet()) {
            if (users.containsKey(id))
                return users.get(id);
        }
        return "user not found";
    }

    @GetMapping("/user/add")
//    http://localhost:8080/user/add?name=Karol&surname=Platek&age=23
    @ResponseBody
    public boolean addUser(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer age) {

        if (name != null && surname != null && age != 0) {
            UserEntity newUser = new UserEntity(name, surname, age);
            users.put(iterator++, newUser);
            return true;
        }
        return false;
    }

    @GetMapping("/users/{id}/remove")
//    http://localhost:8080/users/1/remove
    @ResponseBody
    public boolean removeUser(@PathVariable Integer id) {
        for (Integer key : users.keySet()) {
            if (users.containsKey(id)) {
                users.remove(id);
                return true;
            }
        }
        return false;
    }

}