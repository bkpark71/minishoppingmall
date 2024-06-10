package org.example.minishoppingmall.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInquiryDto {
    private int memberId;
    private String memberName;
    private String userId;
}
