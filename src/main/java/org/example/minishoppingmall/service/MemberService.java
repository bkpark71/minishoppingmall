package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.MemberUpdateDto;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(int memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public int addMember(Member member) {
        return memberRepository.save(member).getMemberId();// 장바구니 같이 만들어지는지 확인
    }

    @Transactional
    public void updateMember(MemberUpdateDto memberDto) { // 이름만 변경 가능
        Member member = memberRepository.findById(memberDto.getMemberId()).get();
        member.setMemberName(memberDto.getMemberName());
        // unique check 해서 update 여부 결정
        member.setEmail(memberDto.getEmail());
        // unique check 해서 update 여부 결정
        member.setPhone(memberDto.getPhone());
        member.setAddress(memberDto.getAddress());
        memberRepository.save(member);
    }

    @Transactional
    public boolean changePassword(int memberId, String oldPassword, String newPassword) {
        // old-password가 일치하는 경우에만 수정한다.
        Member member = memberRepository.findById(memberId).get();
        boolean result = member.changePassword(oldPassword, newPassword);
        if (result) {
            memberRepository.save(member);
        }
        return result;
    }
}
