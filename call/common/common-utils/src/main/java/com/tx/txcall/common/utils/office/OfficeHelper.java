package com.tx.txcall.common.utils.office;/**
 * Created by wyh in 2019/6/10 14:52
 **/

import com.tx.txcall.common.configuration.OfficePathConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import com.tx.txcall.common.utils.office.bean.dto.DocHtmlDto;
import com.tx.txcall.common.utils.office.bean.dto.ExcelHtmlResultDto;
import com.tx.txcall.common.utils.office.bean.dto.ExcelSheetDto;
import com.tx.txcall.common.utils.office.bean.dto.WordHtmlDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-06-10 14:52
 **/
public class OfficeHelper {

    public static String MultipartFile2Html(MultipartFile file) {
        StringBuilder uploadDir = new StringBuilder();
        //复制到服务器
        if (!file.isEmpty()) {
            try {

                InputStream inputStream = file.getInputStream();

                //后缀名
                String prefix = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf("."));

                uploadDir.append(OfficePathConfig.OFFICE_UPLOAD_DIR);
                File outFilePath = new File(uploadDir.toString());
                if (!outFilePath.exists()) {
                    outFilePath.mkdirs();
                }
                uploadDir.append(
                    System.currentTimeMillis() + prefix);
                File outFile =new File(uploadDir.toString());

                OutputStream outputStream = new FileOutputStream(outFile);

                int b = 0;
                byte[] bytes = new byte[1024];

                while ((b = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, b);
                }

                inputStream.close();
                outputStream.close();

                //转换
                DocHtmlDto docHtmlDto = Doc2HtmlFactory.coverToHtml(uploadDir.toString());
                if (prefix.contains("doc")){
                    //doc,docx

                    StringBuffer html = new StringBuffer();

                    WordHtmlDto wordHtmlDto = (WordHtmlDto) docHtmlDto;

                    BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(wordHtmlDto.getHtml()),
                                              "utf-8"));//这里可以控制编码
                    String line = "";
                    while((line = br.readLine()) != null) {
                        html.append(line);
                    }

                    return  html.toString();
                }else{
                    //xls,xlsx
                    ExcelHtmlResultDto excelHtmlResultDto = (ExcelHtmlResultDto) docHtmlDto;

                    StringBuilder html = new StringBuilder();
                    StringBuilder top = new StringBuilder();
                    top.append("<hr /> <p></p><center>  <h1>摘要</h1>  " );
                    List<ExcelSheetDto> sheetList = excelHtmlResultDto.getSheetList();
                    sheetList.forEach(excelSheetDto -> {
                        String sheetName = excelSheetDto.getSheetName();
                        top.append("<br/><p>");
                        top.append(sheetName);
                        top.append("</p><br/>");

                        html.append(excelSheetDto.getHtml());
                    });
                    top.append("</center><p></p><hr />");
                    top.append(html);
                    return top.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
