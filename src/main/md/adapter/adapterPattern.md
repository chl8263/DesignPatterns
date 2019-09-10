## 7. Adapter pattern

### 정의
> 한 클래스의 인터페이스를 클라이언트에서 사용하고자 하는 다른 인터페이스로 변환한다.
> 어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수 없는 클래스들을 연결해서 쓸 수 있다.

어댑터 패턴을 이용하면 호환되지 않는 인터페이스를 사용하는 클라이언트를 그대로 활용할 수 있게 된다.

이렇게 함으로써 클라이언트와 구현된 인터페이스를 분리시킬 수 있으며, 나중에 인터페이스가 바뀌더라도 그 변경 내역은 어뎁터에 캡슐화되기 때문에 클라이언트는 바뀔 필요가 없다.

예를들어 아래와 같은 시스템의 경우라고 생각하면 되겠다.

![base](/src/main/md/adapter/img/adapter2.PNG)

위와 같이 기존의 어떤 인터페이스를 클라이언트에서 요구하는 형태의 인터페이스에 적응 시켜주는 역할을 한다.

![base](/src/main/md/adapter/img/adapter1.PNG)

위의 그림은 Adapter 패턴의 클래스 다이어그램이다.

클라이언트를 특정 구현이 아닌 인터페이스에 연결 시켜 서로 다른 백엔드 클래스로 변환시키는 것이 가능하다.

이렇게 인터페이스를 기준으로 코딩을 했기때문에 타겟 인터페이스만 제대로 지킨다면 나중에 다른 구현을 추가하는 것도 가능하다.

### 예제 

- mp3 파일은 오디오를 재생시킨다.
- mp4 파일은 동영상을 재생시킨다.
- mkv 파일은 동영상을 재생시킨다.

그렇다면 mp3 플레이어에서 mp4, mkv 파일을 가지고 오디오만 재생시킬 수 있게 하는 간단한 예제를 구현해 보겠다.

~~~java
public interface Mediaplayer {
    void play();
}
~~~

먼저 interface를 구현 하겠다. mp3를 구동시킬 수 있는 Mediaplayer를 생성했다.

<br/>

~~~java
public interface Mediapakage {
    void playFile();
}
~~~

그다음 mp4, mkv 파일을 실행시키는 Mediapakage를 만들었다. 
<br/>

~~~java
public class Mp3 implements Mediaplayer {

    private static final Logger logger = LoggerFactory.getLogger(Mp3.class);

    @Override
    public void play() {
        logger.info("mp3 파일을 재생합니다.");
    }
}
~~~

mp3 파일은 Mediaplayer에서 재생된다. play메소드를 통해 재생되는 것을 알 수있다.
<br/>

~~~java
public class Mp4 implements Mediapakage {
    private static final Logger logger = LoggerFactory.getLogger(Mp4.class);

    @Override
    public void playFile() {
        logger.info("mp4 파일을 재생합니다.");
    }
}
~~~

~~~java
public class Mkv implements Mediapakage {
    private static final Logger logger = LoggerFactory.getLogger(Mkv.class);

    @Override
    public void playFile() {
        logger.info("Mkv 파일을 재생합니다.");
    }
}
~~~

mp4 파일과 Mkv 파일은 Mediapakage 에서 재생된다.
<br/>

자 이제 그렇다면 Mediaplayer 환경에서 어떻게 Mediapakage환경에서 돌아가는 mp4, mkv 파일을 재생시킬 수 있을까??

Mediaplayer를 구현하는 adapter를 이용하여 이 문제를 해결할 수 있다.

~~~java
public class MediaAdapter implements Mediaplayer{
    private static final Logger logger = LoggerFactory.getLogger(MediaAdapter.class);

    private Mediapakage mediapakage;    //mediapakage 객체를 인자로 받는다. 

    public MediaAdapter(Mediapakage mediapakage){
        this.mediapakage = mediapakage;
    }

    @Override
    public void play() {
        if(mediapakage != null){
            logger.info("Mediaplayer 환경에서 재생합니다. 오디오만 지원합니다.");
            mediapakage.playFile(); // mediapakage 의 playFile을 play에서 실행 시켜준다.
        }
    }
}
~~~

<br/>

위와같이 MediaPlayer를 구현하는 Adapter를 하나 생성하고 mediaPAkage를 맴버로 가진뒤 play() 메소드 안에 playFile() 메소드를 연결시켜주면 
간단히 구현이 끝난다.

즉, adapter의 구현으로 mp3,mp4,mkv Class의 소스를 수정없이 적용시킬 수 있다.

밑의 사용 코드를 보자.

~~~java
public class AdapterTest {
    public AdapterTest(){
        run();
    }

    private void run(){
        Mediaplayer mp3 = new Mp3();
        mp3.play();         // mp3 만 재생시킨경우

        Mediapakage mp4 = new Mp4();
        mp4.playFile();     // mp4 만 재생시킨경우

        Mediapakage mkv = new Mkv();
        mkv.playFile();     // mkv 만 재생시킨경우

        Mediaplayer mpAdapter1 = new MediaAdapter(new Mp4());
        mpAdapter1.play();  // Mediaplayer에 mp4를 재생시킨경우

        Mediaplayer mpAdapter2 = new MediaAdapter(new Mkv());
        mpAdapter2.play();  // Mediaplayer에 mkv를 재생시킨경우
    }
}
~~~

~~~java
23:58:39.986 [main] INFO adapter.Mp3 - mp3 파일을 재생합니다.
23:58:39.988 [main] INFO adapter.Mp4 - mp4 파일을 재생합니다.
23:58:39.989 [main] INFO adapter.Mkv - Mkv 파일을 재생합니다.
23:58:39.989 [main] INFO adapter.MediaAdapter - Mediaplayer 환경에서 재생합니다. 오디오만 지원합니다.
23:58:39.989 [main] INFO adapter.Mp4 - mp4 파일을 재생합니다.
23:58:39.989 [main] INFO adapter.MediaAdapter - Mediaplayer 환경에서 재생합니다. 오디오만 지원합니다.
23:58:39.989 [main] INFO adapter.Mkv - Mkv 파일을 재생합니다.

Process finished with exit code 0
~~~

다음과 같은 결과화면을 볼 수 있다.
