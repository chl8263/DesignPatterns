## 10. Iterator pattern

### 정의
> 컬렉션 구현 방법을 노출시키지 않으면서도 그 집합체 안에 들어있는 모든 항목에 접근할 수 있게 해 주는 방법을 제공하는 패턴

### 예제

어느 식당에서 두사람이 일을 한다고 가정하자.

한사람은 점심 메뉴를 만들고 어떤 한 사람은 저녁 메뉴를 만들기로 하였다.

점심메뉴를 만드는 사람은 다음과 같이 코드를 구현 하였다.

~~~java
public class LunchMenu {

    private static final Logger logger = LoggerFactory.getLogger(LunchMenu.class);

    final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem menuItems [];

    public LunchMenu(){
        menuItems = new MenuItem[MAX_ITEMS];
        
        addItem("돈까스");
        addItem("생선까스");
        addItem("비프까스");
    }

    public void addItem(String name){
        if(numberOfItems >= MAX_ITEMS){
            logger.info("메뉴가 가늑찼습니다. 등록할 수 없습니다.");
        }else {
            menuItems[numberOfItems] = new MenuItem(name);
            numberOfItems++;
        }
    }

    public MenuItem[] getMenuItems(){
        return this.menuItems;
    }
}
~~~

위의 코드와 같이 점심 메뉴를 개발한 사람은 MenuItem 배열을 이용하여 생성자에 메뉴를 등록하는 방식을 사용했다.

~~~java
public class DinnerMenu {

    private static final Logger logger = LoggerFactory.getLogger(DinnerMenu.class);

    private ArrayList<MenuItem> menuItems;

    public DinnerMenu(){
        menuItems = new ArrayList<MenuItem>();
        
        this.addItem("양상추");
        this.addItem("콩자반");
        this.addItem("제육볶음");
    }

    public void addItem(String name){
        menuItems.add(new MenuItem(name));
    }

    public ArrayList<MenuItem> getMenuItems (){
        return this.menuItems;
    }
}
~~~

위와 같이 저녁을 만드는 사람은 ArrayList 를 이용하여 구현한것을 볼 수 있다.

### 문제점

메뉴 구현방식이 위와 같이 다르다보니, 점심과 저녁 메뉴 리스트를 출력할 때 다음과 같은 작업이 필요할 것 같다.

~~~java
public class PrintMenu {

    private static final Logger logger = LoggerFactory.getLogger(PrintMenu.class);

    private LunchMenu lunchMenu;

    private DinnerMenu dinnerMenu;

    public PrintMenu(){
        lunchMenu = new LunchMenu();
        dinnerMenu = new DinnerMenu();

        printMenu();
    }

    public void printMenu(){
        printLunch();
        printDinner();
    }

    private void printLunch(){
        MenuItem [] menuItems = lunchMenu.getMenuItems();
        for(MenuItem item : menuItems){
            try {
                logger.info(item.getName());
            }catch (NullPointerException e){ }
        }
    }

    private void printDinner(){
        ArrayList<MenuItem> menuItems = dinnerMenu.getMenuItems();
        for(MenuItem item : menuItems){
            logger.info(item.getName());
        }
    }
}
~~~

다음과 같이 점심 메뉴와 저녁 메뉴의 음식을 각각 출력 하여면 위와 같이 방식이 다른 순환문 2개를 돌려야만 한다.

### 바뀔 수 있는 부분을 캡슐화 하기

__인 패턴의 가장 중요한 원리 중 하나인 바뀌는 부분을 캡슐화를 해야한다.__

ArrayList 는 get() , 배열은 length 를 통하여 각각의 레퍼런스를 구할 수 있다.

이제, 객체의 컬렉션에 대한 반복작업을 처리하는 방법을 캡슐화한 Iterator라는 객체를 만들어 보면 어떨까??

~~~java
Iterator iterator = LunchMenu.createIterator(); // DinnerMenu에 대한 반복자를 요구한다.

while(iterator.hasNext()){  // 아직 항목이 남아있는 동안
    MenuItem menuItem = (MenuItem)iterator.next();  // 다음항목을 뽑아낸다.
}
~~~

위의 코드와 같이 Iterator를 만들어 공통화 시키면 리스트를 출력하는 작업을 단일화 시킬 수 있을것 같다.

위같은 코드가 바로 이터레이터 패턴인데 이것은 인터페이스에 의존한다.

Iterator 인터페이스만 있으면 배열,리스트,해시테이블은 물론, 그 어떤 종류의 객체 컬렉션에 대해서도 반복자를 구현할수 있다.

우선 Iterator 인터페이스를 구현해 보자.

~~~java
public interface Iterator {
    boolean hasNext();  // 그다음 반복작업을 수행할 수 있는지
    Object next();      // 다음항목을 리턴
}
~~~

~~~java
public class LunchMenuIterator implements Iterator {

    private MenuItem menuItems[];
    private int position = 0;

    public LunchMenuIterator(MenuItem items []){
        this.menuItems = items;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null)
            return false;
        else return true;
    }

    @Override
    public Object next() {
        return menuItems[position++].getName();
    }
}
~~~

다음은 위의 코드와 같이 DinnerMenu 에 대한 DinnerMenuIterator 클래스를 추가해준다

~~~java
public class LunchMenu {

    private static final Logger logger = LoggerFactory.getLogger(LunchMenu.class);

    final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem menuItems [];

    public LunchMenu(){
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("돈까스");
        addItem("생선까스");
        addItem("비프까스");
    }

    public void addItem(String name){
        if(numberOfItems >= MAX_ITEMS){
            logger.info("메뉴가 가늑찼습니다. 등록할 수 없습니다.");
        }else {
            menuItems[numberOfItems++] = new MenuItem(name);
        }
    }

    public Iterator createIterator(){
        return new LunchMenuIterator(menuItems);
    }
}
~~~

이제 LunchMenu 코드의 반복자는 createIterator() 의 호출에 의해서 수행될 수 있다.

~~~java
public class PrintMenu {

    private static final Logger logger = LoggerFactory.getLogger(PrintMenu.class);

    private LunchMenu lunchMenu;

    private DinnerMenu dinnerMenu;

    public PrintMenu(){
        lunchMenu = new LunchMenu();
        dinnerMenu = new DinnerMenu();

        printMenu();
    }

    public void printMenu(){
        printLunch();
    }

    private void printLunch(){
        Iterator iterator = lunchMenu.createIterator();
        while (iterator.hasNext()){
            logger.info(iterator.next().toString());
        }
    }
}
~~~

Lunch 메뉴에 대해서 앞으로 printLunch() 의 Iterator 만 사용하여 단일화로써 리스트를 출력할 수 있게 된다.


~~~java
02:22:21.099 [main] INFO iterator.PrintMenu - 돈까스
02:22:21.101 [main] INFO iterator.PrintMenu - 생선까스
02:22:21.101 [main] INFO iterator.PrintMenu - 비프까스

Process finished with exit code 0
~~~


![base](/src/main/md/iterator/img/iterator1.PNG)



__이터레이터 패턴을 사용하면 내부적인 구현방법을 외부로 노툴시키지 않으면서도 집합체에 있는 모든 항목에 일일이 접근할 수 있다.__

__또한 각 항목에 일일이 접근할 수 있게 해주는 기능을 집합체가아닌 반복자 객체에서 책임지게 된다는 것도 장점으로 작용한다.__
