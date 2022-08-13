package com.ezcload.user.demo.demo.controller;

import com.ezcload.user.demo.demo.dto.UserDTO;
import com.ezcload.user.demo.demo.entity.User;
import com.ezcload.user.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
//    @Value("${context.path}")
//    String urlImagePath;
    @GetMapping("/get-all")
    public String getALl(ModelMap map){
        List<User> users = userRepository.findAll();
        for (User u:users
             ) {
            u.setAvatar("http://localhost:8080/"+u.getAvatar());
        }
        map.addAttribute("users", users);
        return "userlist";
    }
    @GetMapping("/add-user")
    public String addUser(ModelMap map){
        map.addAttribute("user", new User());
        return "addUser";
    }
    @PostMapping("/add-user")
    public String addUser(UserDTO userDTO, ModelMap map){
        try{
            MultipartFile multipartFile = userDTO.getAvatar();
            String fileName = multipartFile.getOriginalFilename();
            Path absolutePath = Paths.get(System.getProperty("user.dir"));
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            if (!Files.exists(absolutePath.resolve(staticPath).resolve(imagePath))){
                Files.createDirectories(absolutePath.resolve(staticPath).resolve(imagePath));
            }
            Path filePath = absolutePath.resolve(staticPath).resolve(imagePath).resolve(fileName);
            File uploadedFile = filePath.toFile();
            multipartFile.transferTo(uploadedFile);
            System.out.println("Upload success: "+filePath.toFile());
            System.out.println("Lưu database: "+imagePath.resolve(fileName).toString());
            String urlImage = imagePath.resolve(fileName).toString();
            User user = new User(userDTO);
            user.setAvatar(urlImage);
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        List<User> users = userRepository.findAll();
        for (User u:users
        ) {
            u.setAvatar("http://localhost:8080/"+u.getAvatar());
        }
        map.addAttribute("users", users);
        return "userlist";
    }
    @GetMapping("/delete-user/{id}")
    public String deleteUser(ModelMap map, @PathVariable Long id){
        userRepository.deleteById(id);
        List<User> users = userRepository.findAll();
        for (User u:users
        ) {
            u.setAvatar("http://localhost:8080/"+u.getAvatar());
        }
        map.addAttribute("users", users);
        return "userlist";
    }
    @GetMapping("/update-user/{id}")
    public String updateUser(ModelMap map, @PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        //UserDTO userDTO = new UserDTO(user);
        user.setAvatar("http://localhost:8080/"+user.getAvatar());
        map.addAttribute("user", user);
        return "updateUser";
    }
    @PostMapping("/update-user")
    public String updateUser(UserDTO userDTO, ModelMap map){
        try{
            MultipartFile multipartFile = userDTO.getAvatar();
            User user;
            if (!multipartFile.getOriginalFilename().equals("")){
                String fileName = multipartFile.getOriginalFilename();
                Path absolutePath = Paths.get(System.getProperty("user.dir"));
                Path staticPath = Paths.get("static");
                Path imagePath = Paths.get("images");
                if (!Files.exists(absolutePath.resolve(staticPath).resolve(imagePath))){
                    Files.createDirectories(absolutePath.resolve(staticPath).resolve(imagePath));
                }
                Path filePath = absolutePath.resolve(staticPath).resolve(imagePath).resolve(fileName);
                File uploadedFile = filePath.toFile();
                multipartFile.transferTo(uploadedFile);
                System.out.println("Upload success: "+filePath.toFile());
                System.out.println("Lưu database: "+imagePath.resolve(fileName).toString());
                String urlImage = imagePath.resolve(fileName).toString();
                user = new User(userDTO);
                user.setAvatar(urlImage);
            }else {
                String avatar = userRepository.getReferenceById(userDTO.getId()).getAvatar();
                user = new User(userDTO);
                user.setAvatar(avatar);
            }
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        List<User> users = userRepository.findAll();
        for (User u:users
        ) {
            u.setAvatar("http://localhost:8080/"+u.getAvatar());
        }
        map.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
