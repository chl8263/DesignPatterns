package decorator.decorator;

import decorator.component.Beverage;

public class Whip extends CondimentDecorator {

    Beverage beverage;  // 감싸고자 하는 음료를 저장하기 위한 인스턴스

    public Whip(Beverage beverage){      // 인스턴스 변수를 감싸고자 하는 객체로 설정하기 위한 생성자
        this.beverage = beverage;       // 데코레이터의 생성자에 감싸고자 하는 음료 객체를 전달하는
    }                                   // 방식을 사용했다.

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 휘핑";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}
