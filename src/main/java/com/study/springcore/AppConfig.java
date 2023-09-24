package com.study.springcore;

import com.study.springcore.discount.FixDiscountPolicy;
import com.study.springcore.member.MemberService;
import com.study.springcore.member.MemberServiceImpl;
import com.study.springcore.member.MemoryMemberRepository;
import com.study.springcore.order.OrderService;
import com.study.springcore.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자 주입.
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
