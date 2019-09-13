## 9. Template Method Pattern

### 정의
> 메소드에서 알고리즘의 골격을 정의한다.
> 알고리즘의 여러 단계 중 일부의 서브클래스에서 구현할 수 있다.
> 템플릿 메소드를 이용하면 알고리즘의 구조는 그대로 유지하면서 서브클래스에서 특정 단계를 재정의할 수 있다.

템플릿 메소드는 상위 클래스(추상 클래스)에서 템플릿에 해당하는 메소드들이 정의되어 있고, 하위 클래스 에서 이를 상속받아 메소드를 구현하는 것이다.
이렇게하면 연관된 것들끼리 뼈대를 통일할 수 있고 하위 클래스에서 코드량 또한 매우 줄어들며 핵심 로직만 작성할 수 있다.

![base](/src/main/md/template_method/img/template1.PNG)

### 예제

__커피 전문점에서 커피와 많은 음료를 생산할때 아래의 레시피를 보고 생산한다.__

- 커피 만드는법
1. 물을 끓인다.
2. 끓는 물에 커피를 우려낸다.
3. 커피를 컵에 따른다.
4. 설탕과 우유를 추가한다.

- 홍차 만드는법
1. 물을 끓인다.
2. 끓는 물에 차를 우려낸다.
3. 차를 컵에 따른다.
4. 레몬을 추가한다.

----------

커피를 만드는 코드와 홍차를 만드는 코드를 작성해 보자.

~~~java
public class Coffee {
    private static final Logger logger = LoggerFactory.getLogger(Coffee.class);

    void prepareRecipe(){   // 커피의 레시피, 아래의 순서대로 커피가 만들어진다.
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugerAndMilk();
    }

    public void boilWater(){
        logger.info("물 끓이는 중");
    }
    
    public void brewCoffeeGrinds(){
        logger.info("필터를 통해서 커피를 우려내는 중");
    }
    
    public void pourInCup(){
        logger.info("컵에 따르는중");
    }
    
    public void addSugerAndMilk(){
        logger.info("설탕과 우류를 추가하는 중");
    }
}
~~~

~~~java
public class Tea {
    private static final Logger logger = LoggerFactory.getLogger(Tea.class);

    void prepareRecipe(){   // 홍차의 레시피, 아래의 순서대로 차가 만들어진다.
        boilWater();
        stepTeaBag();
        pourInCup();
        addLemon();
    }

    public void boilWater(){
        logger.info("물 끓이는 중");
    }

    public void stepTeaBag(){
        logger.info("티백에 우려내는중");
    }

    public void pourInCup(){
        logger.info("컵에 따르는중");
    }

    public void addLemon(){
        logger.info("레몬을 추가하는중");
    }
}
~~~

위의 두 코드를 보면 아주아주 비슷하다는것을 볼 수 있다.

그렇다면 공통적인 부분을 추상화해서 베이스 클래스를 만드는것이 좋지 않을까?

위의 코드에서 공통적인 부분은 boilWater,pourInCup 메소드 이다.

이 두개의 코드만 추상화 하고 나머지는 서브 클래스에서 구현하면 될것 같다.

하지만 위의 코드의 알고리즘은 똑같기 때문에 서로 다른 메소드들도 같이 추상화 시킬 수는 없을까?


---------------
1. 물을 끓인다.
2. 뜨거운 물을 이용하여 커피 또는 홍차를 우려낸다.  (추상화 되지 않았지만 알고리즘은 같다)
3. 만들어진 음료르 컵에 따른다.
4. 각 음료에 맞는 첨가물을 추가한다.  (추상화 되지 않았지만 알고리즘은 같다)
---------------

위의 리스트 중 2번과 4번의 메소드가 다른것을 공통적인 요소로 묶어 해결할 수 있다.

~~~java
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
~~~

위의 코드와 같이 grew , addCondiments 라고 메소드명을 바꾸되 각 서브클래스에서 재정의 하도록 하였다.

<br/>

~~~java
public class Coffee extends CoffeineBeverage {
    private static final Logger logger = LoggerFactory.getLogger(Coffee.class);

    @Override
    void grew() {
        logger.info("필터로 커피를 우려내는중");
    }

    @Override
    void addCondiments() {
        logger.info("설탕과 우류를 추가하는중");
    }
}
~~~

~~~java
public class Tea extends CoffeineBeverage{
    private static final Logger logger = LoggerFactory.getLogger(Tea.class);

    @Override
    void grew() {
        logger.info("차를 우려내는중");
    }

    @Override
    void addCondiments() {
        logger.info("레몬을 추가하는중");
    }
}
~~~
