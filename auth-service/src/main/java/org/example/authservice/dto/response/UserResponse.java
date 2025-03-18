package org.example.authservice.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String userId;
    String email;
    String username;
    String password;
    LocalDateTime createdAt;
}
