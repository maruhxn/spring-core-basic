package com.study.springcore.autowired.allbean;

import com.study.springcore.AutoAppConfig;
import com.study.springcore.discount.DiscountPolicy;
import com.study.springcore.member.Grade;
import com.study.springcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean() {
        // Given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // When
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrices = discountService.discount(member, 10000, "fixDiscountPolicy");
        int rateDiscountPrices = discountService.discount(member, 20000, "rateDiscountPolicy");
        // Then
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrices).isEqualTo(1000);
        assertThat(rateDiscountPrices).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
