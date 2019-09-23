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

