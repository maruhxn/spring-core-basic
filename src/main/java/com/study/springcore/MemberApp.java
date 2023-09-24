package com.study.springcore;

import com.study.springcore.member.Grade;
import com.study.springcore.member.Member;
import com.study.springcore.member.MemberService;
import com.study.springcore.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member);
        System.out.println("findMember = " + findMember);
    }
}
