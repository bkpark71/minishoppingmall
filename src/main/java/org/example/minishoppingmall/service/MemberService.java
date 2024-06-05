package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.MemberCreateDto;
import org.example.minishoppingmall.dto.MemberUpdateDto;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.MemberStatus;
import org.example.minishoppingmall.exception.NotUniqueEmailException;
import org.example.minishoppingmall.exception.NotUniquePhoneException;
import org.example.minishoppingmall.exception.NotUniqueUserIdException;
import org.example.minishoppingmall.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.example.minishoppingmall.entity.QMember.member;

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
    public int addMember(MemberCreateDto memberDto) {
        uniqueUserIdCheck(memberDto.getUserId());
        uniqueEmailCheck(memberDto.getEmail());
        uniquePhoneCheck(memberDto.getPhone());
        Member member = new Member(
                0,memberDto.getMemberName(), memberDto.getUserId(), memberDto.getPassword(),memberDto.getEmail(),
                memberDto.getPhone(), memberDto.getAddress(), MemberStatus.A, LocalDate.now(), null);
        return memberRepository.save(member).getMemberId();// 장바구니 같이 만들어지는지 확인
    }

    @Transactional
    public void updateMember(MemberUpdateDto memberDto) { // 이름만 변경 가능
        Member member = memberRepository.findById(memberDto.getMemberId()).get();
        member.setMemberName(memberDto.getMemberName());
        // unique check 해서 update 여부 결정
        uniqueEmailCheck(memberDto.getEmail());
        member.setEmail(memberDto.getEmail());
        // unique check 해서 update 여부 결정
        uniquePhoneCheck(memberDto.getPhone());
        member.setPhone(memberDto.getPhone());
        member.setAddress(memberDto.getAddress());
        memberRepository.save(member);
    }

    private void uniqueUserIdCheck(String userId) {
        Member byUserId = memberRepository.findByUserId(userId);
        if(byUserId != null) {
            throw new NotUniqueUserIdException("동일한 아이디가 존재합니다.");
        }
    }

    private void uniquePhoneCheck(String phone) {
        Member byPhone = memberRepository.findByPhone(phone);
        if(byPhone != null) {
            throw new NotUniquePhoneException("동일한 전화번호가 존재합니다.");
        }
    }

    private void uniqueEmailCheck(String email) {
        Member byEmail = memberRepository.findByEmail(email);
        if(byEmail != null) {
            throw new NotUniqueEmailException("동일한 이메일주소가 존재합니다.");
        }
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
