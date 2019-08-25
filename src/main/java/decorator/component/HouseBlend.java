package decorator.component;

public class HouseBlend extends Beverage{

    public HouseBlend(){
        description = "하우스 블랜드";
    }

    @Override
    public double cost() {
        return .89;
    }
}
