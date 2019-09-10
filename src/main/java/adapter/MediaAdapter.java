package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
