package teplateMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CoffeineBeverage {
    private static final Logger logger = LoggerFactory.getLogger(CoffeineBeverage.class);

    final void prepareRecipe(){   // 레시피는 같기때문에 서브 클래스에서 오버라이드해서 수정하지 못하게 하기위해 final 선언
        boilWater();
        grew();
        pourInCup();
        addCondiments();
    }

    abstract void grew();

    abstract void addCondiments();

    public void boilWater(){
        logger.info("물 끓이는 중");
    }

    public void pourInCup(){
        logger.info("컵에 따르는중");
    }
}
