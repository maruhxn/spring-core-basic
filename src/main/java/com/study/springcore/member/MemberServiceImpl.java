package com.study.springcore.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 추상화에도 의존하고, 구체화에도 의존하고 있음.. => DIP 위반
    @Override
    public void join(Member member) {
        memberRepository.saev(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findbyId(memberId);
    }
}
