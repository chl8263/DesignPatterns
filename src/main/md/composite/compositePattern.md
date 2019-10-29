## 11. composite pattern

### 정의
> 객체들의 관계를 트리 구조로 구성하여 부분-전체 계층을 표현하는 패턴으로, 사용자가 단일객체와 복합 객체 모두 동일하게 다루도록 하는 패턴.

### 예제

composite pattern은 위의 정의와 같이 단일, 복합 객체 전부같은 방법으로 취급한다는 것이고 여기서 말하는 composite의 의미는 일부 또는 그룹을 표현하는 객체들을 트리 구조로 구성한다.

예를들어 파일 시스템을 간단하게 구현해 본다고 하자. 

파일은 디렉토리 안에 들어갈 수 있고 디렉토리 또한 다른어떤 디렉토리 안에 들어갈 수 있다.

![base](/src/main/md/composite/img/composite1.PNG)

그렇다면 디렉토리와 파일을 어떻게 같은 구조로 할 수 있을까?

여태까지 디자인패턴에서 다형을 강조하며 매번 사용한 interface 또는 abstract를 이용할 수 있다.

나는 abstract를 이용하여 상속 구조로 구현할 것이고 디렉토리와 파일 모두 이름을 가지기 때문에
공통 맴버로써 Name을 받는다.
~~~java
abstract class Component{
    private String name ;

    public Component(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
~~~

그리고 File 클래스를 만들었다.

~~~java
class File extends Component{

    public File(String name) {
        super(name);
    }
}
~~~

directory 클래스는 하위 component들을 가지고 있을 수 있도록 ArrayList에 저장하고
component들을 add , remove하는 기능을 가지고 있다.
~~~java
class directory extends Component{

    private ArrayList<Component> components = new ArrayList<>();

    public directory(String name) {
        super(name);
    }

    public boolean isComponent(){
        if(components.size() >0) return true;
        return false;
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public boolean removeComponent(Component component){
        return components.remove(component);
    }
}
~~~

자 이제 Directory 와 File 들을 생성하고 Directory 에 component 들을 넣어 보겠다.
~~~java
Directory root = new Directory("root");
Directory home = new Directory("home");
Directory music = new Directory("music");
Directory picture = new Directory("picture");

File track1 = new File("track1");
File track2 = new File("track2");
File track3 = new File("track3");
File doc1 = new File("doc1");
File doc2 = new File("doc2");
~~~





