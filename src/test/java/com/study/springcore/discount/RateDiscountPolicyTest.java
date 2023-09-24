package com.study.springcore.discount;

import com.study.springcore.member.Grade;
import com.study.springcore.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // Given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // When
        int discount = discountPolicy.discount(member, 10000);
        // Then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_x() {
        // Given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        // When
        int discount = discountPolicy.discount(member, 10000);
        // Then
        assertThat(discount).isEqualTo(0);
    }
}