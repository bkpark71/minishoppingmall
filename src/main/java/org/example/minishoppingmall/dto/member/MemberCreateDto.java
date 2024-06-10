package org.example.minishoppingmall.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDto {
    private String memberName;
    private String userId;
    private String password;
    private String phone;
    private String email;
    private String address;
}
