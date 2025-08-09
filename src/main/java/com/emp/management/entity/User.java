package com.emp.management.entity;

import com.emp.management.util.EnumUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    protected String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private EnumUserRole role;

}
