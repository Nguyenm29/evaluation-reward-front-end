package com.vis.frontend.model.response;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserResponse {
    private Long employeeId;
    private String name;
    private String role;
    private String view;
}
