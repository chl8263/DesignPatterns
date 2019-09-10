package adapter;

import javafx.scene.media.MediaPlayer;

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
