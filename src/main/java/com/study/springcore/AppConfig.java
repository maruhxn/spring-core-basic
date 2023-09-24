package com.study.springcore;

import com.study.springcore.discount.DiscountPolicy;
import com.study.springcore.discount.RateDiscountPolicy;
import com.study.springcore.member.MemberService;
import com.study.springcore.member.MemberServiceImpl;
import com.study.springcore.member.MemoryMemberRepository;
import com.study.springcore.order.OrderService;
import com.study.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean // @Bean을 통해 스프링 컨테이너에 등록됨.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 주입.
    }
    @Bean
    public static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
