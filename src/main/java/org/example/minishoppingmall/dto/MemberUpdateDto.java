package org.example.minishoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.MemberStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {
    private int memberId;
    private String memberName;
    private String phone;
    private String email;
    private String address;
}
