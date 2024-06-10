package org.example.minishoppingmall.service;

import org.example.minishoppingmall.dto.member.MemberCreateDto;
import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.MemberStatus;
import org.example.minishoppingmall.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    CartRepository cartRepository;
    //given
    //when
    //then
    @Test
    public void addMember(){
        //given
        MemberCreateDto memberDto = new MemberCreateDto(
                "test2","bbb","1111","00000000000","b@naver.com","하나빌딩"
                );
        //when
        int i = memberService.addMember(memberDto);
        Member memberById = memberService.getMemberById(i);

        Cart cart = cartRepository.findByMember(memberById);
        //List<Member> allMembers = memberService.getAllMembers();
        //then

        assertThat(memberById.getUserId()).isEqualTo("bbb");
        assertThat(cart.getMember()).isEqualTo(memberById);
//        assertThatThrownBy(() -> memberService.addMember(member))
//                .hasMessage("동일한 아이디가 존재합니다.");
    }

    @Test
    public void changePassword(){
        //given
        MemberCreateDto member = new MemberCreateDto(
                "test","aaa","1111","a@naver.com","00000000000","구미동 하나프라자빌딩"
                );
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