package org.example.authservice.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String userId;
    String email;
    String username;
    String password;
    LocalDateTime createdAt;
}
