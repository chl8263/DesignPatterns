## 2. Observer pattern

### 정의

>observer pattern 이란 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체들한테 연락이 가고 자동으로 내용이 갱신되는 방식으로
one to many 의 의존성을 가진다.

![base](/src/main/md/observer/img/ob1.PNG)

위의 그림과 같이 observer pattern 에는 크게 두가지로 나뉜다. 

>publisher  : 여러개의 subscriber 를 가질 수 있으며 데이터를 발행하는 주체, Subject 라고도 한다.
>subscriber : 한개의 publisher 를 가질 수 있으며 publisher 가 발행한 데이터를 수신받는 주체, Observer 라고도 한다 .

observer pattern 의 class 다이어그램과 설명은 아래 그림과 같다.

![base_archi](/src/main/md/observer/img/ob2.PNG)
