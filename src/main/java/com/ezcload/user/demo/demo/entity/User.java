package com.ezcload.user.demo.demo.entity;

import com.ezcload.user.demo.demo.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private int age;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String avatar;
    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.age = userDTO.getAge();
        this.address = userDTO.getAddress();
        this.phone = userDTO.getPhone();
        this.avatar = userDTO.getName();
    }
}
