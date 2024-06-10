package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.member.MemberCreateDto;
import org.example.minishoppingmall.dto.member.MemberCreateResponseDto;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public MemberCreateResponseDto memberRegister(@RequestBody MemberCreateDto memberDto) {
        int memberId = memberService.addMember(memberDto);
        Member memberById = memberService.getMemberById(memberId);
        return new MemberCreateResponseDto(memberId, memberById.getMemberName(), memberById.getUserId());
    }

}
