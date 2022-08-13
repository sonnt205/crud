package com.ezcload.user.demo.demo.dto;

import com.ezcload.user.demo.demo.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String phone;
    private MultipartFile avatar;
    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        //this.avatar = user.getAvatar();
    }
}
