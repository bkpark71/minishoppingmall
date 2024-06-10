package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    @Column(length=20)
    private String memberName;
    @Column(length=6)
    private String userId;
    @Column(length=8)
    private String password;
    @Column(length=20, unique=true)
    private String email;
    @Column(length=11, unique=true)
    private String phone;
    @Column(length=100)
    private String address;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    private LocalDate registerDate;
    private LocalDate leaveDate;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Cart cart;

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void memberLeaveProcess(){ // 회원탈퇴 처리
        this.memberStatus = MemberStatus.B;
        this.leaveDate = LocalDate.now();
    }

    public boolean changePassword(String oldPassword, String newPassword){
        // 현재 암호와 일치하는 경우에만 새로운 암호로 변경처리
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", memberStatus=" + memberStatus +
                ", registerDate=" + registerDate +
                ", leaveDate=" + leaveDate +
                ", cart=" + cart +
                '}';
    }
}
