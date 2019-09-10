package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mp4 implements Mediapakage {
    private static final Logger logger = LoggerFactory.getLogger(Mp4.class);

    @Override
    public void playFile() {
        logger.info("mp4 파일을 재생합니다.");
    }
}
