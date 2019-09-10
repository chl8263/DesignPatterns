package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mkv implements Mediapakage {
    private static final Logger logger = LoggerFactory.getLogger(Mkv.class);

    @Override
    public void playFile() {
        logger.info("Mkv 파일을 재생합니다.");
    }
}
