package com.tx.txcall.common.utils.office;


import com.tx.txcall.common.utils.office.bean.dto.DocHtmlDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * office Factory
 * Created by wyh in 2019/6/10 9:59
 **/
public class Doc2HtmlFactory {

	private static Doc2Html excel2Html = new Excel2Html();
	private static Doc2Html word2Html = new Word2Html();

	public static Map<String, Doc2Html> instanceMap = new HashMap<String, Doc2Html>();
	static {
		instanceMap.put("doc", word2Html);
		instanceMap.put("docx", word2Html);
		instanceMap.put("xls", excel2Html);
		instanceMap.put("xlsx", excel2Html);
//		instanceMap.put("ppt", ppt2Html);
//		instanceMap.put("pptx", ppt2Html);
//		instanceMap.put("pdf", pdf2Html);

	}

	public static Doc2Html getInstance(String filePath) {
		if (filePath.indexOf(".") >= 0) {
			String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
			Doc2Html doc2Html = instanceMap.get(fileType.toLowerCase());
			return doc2Html;
		}
		return null;
	}

	public static DocHtmlDto coverToHtml(String filePath) {
		Doc2Html doc2Html = getInstance(filePath);
		if (doc2Html != null) {
			try {
				return doc2Html.doc2Html(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


    public static void main(String[] args) {


	    String filePath = "D:\\test.docx";

        DocHtmlDto docHtmlDto = coverToHtml(filePath);
//        System.out.println(docHtmlDto.getHtml());


    }
}
