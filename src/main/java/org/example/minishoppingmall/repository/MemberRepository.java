package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByMemberName(String memberName);
    List<Member> findByMemberStatus(MemberStatus memberStatus);
    Member findByEmail(String email);
    Member findByPhone(String phone);
    Member findByUserId(String userId);
}
