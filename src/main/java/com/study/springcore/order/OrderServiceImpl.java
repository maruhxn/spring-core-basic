package com.study.springcore.order;

import com.study.springcore.annotation.MainDiscountPolicy;
import com.study.springcore.discount.DiscountPolicy;
import com.study.springcore.member.Member;
import com.study.springcore.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
// final이 붙은 필드를 가지고 생성자를 자동으로 만들어줌.
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findbyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
