package org.example.authservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
