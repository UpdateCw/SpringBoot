package com.androidmov.adManagement.maker.scheduled;

import com.amt.tools.io.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Created by cw on 2017/10/27. good day.
*
* @Author: Chen Wu
* Blog: http://www.updatecg.xin
*/
@Component
public class FileScheduled {
    private static Logger log = Logger.getLogger(FileScheduled.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "00 00 2 ? * *")
    private void deleteFile() {
        try {
            String path = String.join("/", System.getProperty("user.dir"), "tmp");
            FileUtil.cleanDir(path);
            log.info("Remove file success . data = " + dateFormat.format(new Date()));
        } catch (Exception e) {
            log.info("Remove file fail . data = " + dateFormat.format(new Date()));
            e.printStackTrace();
        }
    }
}
