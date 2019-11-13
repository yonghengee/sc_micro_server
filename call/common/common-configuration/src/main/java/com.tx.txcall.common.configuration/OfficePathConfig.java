package com.tx.txcall.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OfficePathConfig {

    public static String OFFICE_BASE_DIR;
    public static String OFFICE_IMAGE_DIR;
    public static String OFFICE_UPLOAD_DIR;

    @Value("${file.office-path}")
    public  void setOfficeBaseDir(String officeBaseDir) {
        OfficePathConfig.OFFICE_BASE_DIR = officeBaseDir;
    }

    @Value("${file.office-path}")
    public  void setOfficeImageDir(String officeImageDir) {
        OfficePathConfig.OFFICE_IMAGE_DIR = officeImageDir+"/images/";
    }

    @Value("${file.office-path}")
    public  void setOfficeUploadDir(String officeUploadDir) {
        OfficePathConfig.OFFICE_UPLOAD_DIR = officeUploadDir+"/upload/";
    }

    public static String getDir(String folder) {
        StringBuilder dir = new StringBuilder();
        dir.append(OfficePathConfig.OFFICE_BASE_DIR).append("/").append(
            FormartUtil.getDateFolder()).append("/").append(folder);
        return dir.toString();
    }

    public static String getRelativeDir(String folder) {
        StringBuilder dir = new StringBuilder();
        dir.append("http://").append(ResourceValues.hostName).append("/doc2html/").append(
            FormartUtil.getDateFolder()).append("/").append(folder);
        return dir.toString();
    }

    static class FormartUtil {
        private static final SimpleDateFormat dfm = new SimpleDateFormat("yyyy/MM-dd");

        protected static String getDateFolder() {
            return dfm.format(new Date());
        }
    }
}
