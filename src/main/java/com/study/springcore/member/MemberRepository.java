package com.study.springcore.member;

public interface MemberRepository {
    void saev(Member member);

    Member findbyId(Long memberId);
}
