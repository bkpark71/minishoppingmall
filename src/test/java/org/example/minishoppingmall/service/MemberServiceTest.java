package org.example.minishoppingmall.service;

import org.assertj.core.api.Assertions;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.MemberStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    //given
    //when
    //then
    @Test
    public void addMember(){
        //given
        Member member = new Member(
                0,"test","aaa","1111","a@naver.com","00000000000","구미동 하나프라자빌딩",
                MemberStatus.A, LocalDate.now(), null);
        //when
        memberService.addMember(member);
        List<Member> allMembers = memberService.getAllMembers();
        //then
        assertThat(allMembers.size()).isEqualTo(1);
    }

    @Test
    public void changePassword(){
        //given
        Member member = new Member(
                0,"test","aaa","1111","a@naver.com","00000000000","구미동 하나프라자빌딩",
                MemberStatus.A, LocalDate.now(), null);
        int memberId = memberService.addMember(member);
        //when
        memberService.changePassword(memberId, "2222", "0000");
        //then
        assertThat(memberService.getMemberById(memberId).getPassword())
                .isEqualTo("1111");
    }

    @Test
    public void leaveProcess(){
        //given
        Member member = memberService.getMemberById(4);
        //when
        member.memberLeaveProcess();
        //then
        assertThat(member.getMemberStatus()).isEqualTo(MemberStatus.B);
        assertThat(member.getLeaveDate()).isEqualTo("2024-06-05");
    }
}