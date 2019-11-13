package com.tx.txcall.common.configuration;
/**
 * Created by wyh in 2019/4/18 14:57
 **/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-04-18 14:57
 **/
@Component
public class ResourceValues {


    public static String RECORD_FILE;

    public static long EXPIRE_TIME;

    public static long getExpireTime() {
        return EXPIRE_TIME;
    }

    public static String uploadPath;

    public static String hostName;

    public static String recordFileRealPath;

    @Value("${hostName}")
    public void setHostName(String hostName) {
        ResourceValues.hostName = hostName;
    }
    @Value("${expire-time}")
    public void setExpireTime(long expireTime) {
        ResourceValues.EXPIRE_TIME = expireTime;
    }

    @Value("${file.upload-path}")
    public void setFilePath(String uploadPath) {
        ResourceValues.uploadPath = uploadPath;
    }

    @Value("${recordFile}")
    public void setRecordFile(String recordFile) {
        ResourceValues.RECORD_FILE = recordFile;
    }

    @Value("${callRecord.callRecordPath}")
    public void setRecordFileRealPath(String recordFileRealPath) {
        ResourceValues.recordFileRealPath = recordFileRealPath;
    }
}
