package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mp3 implements Mediaplayer {

    private static final Logger logger = LoggerFactory.getLogger(Mp3.class);

    @Override
    public void play() {
        logger.info("mp3 파일을 재생합니다.");
    }
}
