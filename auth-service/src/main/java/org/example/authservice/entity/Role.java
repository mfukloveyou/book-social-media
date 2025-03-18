package org.example.authservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    String roleId;
    String roleName;

    @ManyToMany
    Set<Permission> permissions;
}
