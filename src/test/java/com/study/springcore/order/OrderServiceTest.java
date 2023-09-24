package com.study.springcore.order;


import com.study.springcore.member.Grade;
import com.study.springcore.member.Member;
import com.study.springcore.member.MemberService;
import com.study.springcore.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // Given
        Long memberId = 1L; // primitve type을 쓰면 null을 넣을 수 없음. Null일 수도 있어서 Wrapper class 사용
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // When
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // Then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}