package com.tx.txcall.common.utils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Excel Pojo
 * Created by wyh in 2018/7/6 9:59
 **/
public class Excel {

    private int sheetCount; //sheet表个数

    private String [] sheetNames;//表名

    private String[] cellNames;//表头

    private LinkedHashMap<String, List> excelMap; //数据

    private String Suffix;


    public String[] getSheetNames() {
        return sheetNames;
    }

    public void setSheetNames(String[] sheetNames) {
        this.sheetNames = sheetNames;
    }
    public String getSuffix() {
        return Suffix;
    }

    public void setSuffix(String suffix) {
        Suffix = suffix;
    }

    public int getSheetCount() {
        return sheetCount;
    }

    public void setSheetCount(int sheetCount) {
        this.sheetCount = sheetCount;
    }

    public String[] getCellNames() {
        return cellNames;
    }

    public void setCellNames(String[] cellNames) {
        this.cellNames = cellNames;
    }

    public LinkedHashMap<String, List> getExcelMap() {
        return excelMap;
    }

    public void setExcelMap(LinkedHashMap<String, List> excelMap) {
        this.excelMap = excelMap;
    }
}
