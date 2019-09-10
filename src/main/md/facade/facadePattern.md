## 8. Facade pattern

### 정의
> 퍼사드 패턴은 클래스 라이브러리 같은 어떤소프트웨어의 다른 커다란 코드 부분에 대한 간략화된 인페이스를 제공하는 객체이다.

즉 Facade패턴은 여러 시스탬을 모아 간략화된 하나의 인터페이스를 제공하는 패턴이다.

이패턴의 주체는 Client, Facade, System 이다.

본래 Client 는 아래의 그림과 같이 각각의 System을 일일이 알고 접근해야만 했었다.(Coupling)

![base](/src/main/md/facade/img/facade1.PNG)

하지만 Facade가 생기면서 Client는 각각의 System에 대해 몰라도 아래의 그림처럼 Facade를 통해 System의 기능을 사용할 수 있다.

![base](/src/main/md/facade/img/facade2.PNG)

### 예제

~~~java
class CPU {
 public void freeze() { ... }
 public void jump(long position) { ... }
 public void execute() { ... }
}

class Memory {
 public void load(long position, byte[] data) {
  ...
 }
}

class HardDrive {
 public byte[] read(long lba, int size) {
  ...
 }
}

/* Fa?ade */

class Computer {
 public void startComputer() {
                CPU cpu = new CPU();
                Memory memory = new Memory();
                HardDrive hardDrive = new HardDreive();
  cpu.freeze();
  memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
  cpu.jump(BOOT_ADDRESS);
  cpu.execute();
 }
}
~~~

~~~java
Computer facade = new Computer
facade.startComputer();
~~~

위의 예제 코드와 같이 간단하여 따로 주석과 설명을 생략하였다.

Facade 패턴을 사용하면 코드가 깔끔해지고 가독성도 좋아진다.

또한여러 System의 여러 기능들을 한꺼번에 처리하는 API를 추가확장하는것도 아주 쉽다.

![base](/src/main/md/facade/img/facade3.PNG)
