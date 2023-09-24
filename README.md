# 스프링 핵심 원리 - 기본편 (Section 2)

## 비즈니스 요구사항과 설계 #1

- 회원
    - 회원을 가입하고 조회할 수 있다.
    - 회원은 일반과 VIP 두 가지 등급이 있다.
    - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
    - 회원 등급에 따라 할인 정책을 적용할 수 있다.
    - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경될 수 있다.)
    - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다.(미확정)

## 비즈니스 요구사항과 설계 #2

### 새로운 할인 정책 개발

- 할인 정책을 지금처럼 고정 금액 할인이 아니라 좀 더 합리적인 주문 금액당 할인하는 정률% 할인으로 변경하고 싶다.
- 예를 들어, 기존 정책은 VIP 10000원을 주문하든 20000원을 주문하든 항상 1000원을 할인했는데, 이번에 새로 나온 정책은 10%로 지정해두면 고객이 10000원 주문시 1000원을 할인해주고,
  20000원 주문 시에 2000원 할인하도록 변경한다.

### 새로운 할인 정책 적용과 문제점
```
  // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

```
- 클라이언트인 OrderServiceImpl이 DiscountPolicy 인터페이스 뿐만 아니라, FixDiscountPolicy인 구체 클래스도 함께 의존하고 있다. -> "DIP 위반"
- 그래서 FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스코드도 함께 변경해야 한다. -> "OCP 위반"
### 어떻게 문제를 해결할 수 있을까?
- 클라이언트 코드인: OrderServiceImpl은 DiscountPolicy의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존한다.
- 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다.
- DIP 위반 -> 추상(인터페이스)에만 의존하도록 변경해야 한다.
- DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다.
```agsl
private DiscountPolicy discountPolicy;
```
- 인터페이스에만 의존하도록 설계와 코드를 변경했지만, 구현체가 없어서 NPE 발생한다..

### 해결방안
- 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.