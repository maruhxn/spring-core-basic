package com.study.springcore.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 여기서는 HashMap을 썼지만 동시성 이슈가 있을 수 있기에 원래라면 Concurrent HashMap을 쓰는게 맞음.
    @Override
    public void saev(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findbyId(Long memberId) {
        return store.get(memberId);
    }
}
