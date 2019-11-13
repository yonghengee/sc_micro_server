package com.tx.txcall.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * ExcelOutputUtils Excel工具类
 * Created by wyh in 2018/7/5 17:30
 **/
@Slf4j
public class ExcelImportUtil {


    /**
     * 解决思路：采用Apache的POI的API来操作Excel，读取内容后保存到List中，再将List转Json（推荐Linked，增删快，与Excel表顺序保持一致）
     * <p>
     * Sheet表1  ————>    List1<Map<列头，列值>>
     * Sheet表2  ————>    List2<Map<列头，列值>>
     * <p>
     * 步骤1：根据Excel版本类型创建对于的Workbook以及CellSytle
     * 步骤2：遍历每一个表中的每一行的每一列
     * 步骤3：一个sheet表就是一个Json，多表就多Json，对应一个 List
     * 一个sheet表的一行数据就是一个 Map
     * 一行中的一列，就把当前列头为key，列值为value存到该列的Map中
     *
     * @param inputStream SSM框架下用户上传的Excel文件
     * @param fileName
     * @return Map  一个线性HashMap，以Excel的sheet表顺序，并以sheet表明作为key，sheet表转换json后的字符串作为value
     * @throws IOException
     */
    public static Excel excel2json(
        InputStream inputStream, String fileName, LinkedHashMap<String, String> cellNameToKey,
        String businessType) throws IOException {
        log.info("excel2json方法执行....");


        Excel excel = new Excel();
        String[] sNames;
        // 返回的map
        LinkedHashMap<String, List> excelMap = new LinkedHashMap<>();

        // Excel列的样式，主要是为了解决Excel数字科学计数的问题
        CellStyle cellStyle;

        // Excel列的样式，主要是为了解决Excel数字科学计数的问题
        CellStyle cellStyle_D;
        // 根据Excel构成的对象
        Workbook wb;
        // 如果是2007及以上版本，则使用想要的Workbook以及CellStyle
        try {
            log.info(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (fileName.endsWith("xlsx")) {
            log.info("是2007及以上版本  xlsx");
            excel.setSuffix("xlsx");
            wb = new XSSFWorkbook(inputStream);
            XSSFDataFormat dataFormat = (XSSFDataFormat) wb.createDataFormat();
            cellStyle = wb.createCellStyle();
            cellStyle_D = wb.createCellStyle();
            // 设置Excel列的样式为文本
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            cellStyle_D.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
        } else if (fileName.endsWith("xls")) {
            log.info("是2007以下版本  xls");
            excel.setSuffix("xls");
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
            HSSFDataFormat dataFormat = (HSSFDataFormat) wb.createDataFormat();
            cellStyle = wb.createCellStyle();
            cellStyle_D = wb.createCellStyle();
            // 设置Excel列的样式为文本
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            cellStyle_D.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
        } else {
            return null;
        }

        // sheet表个数
        int sheetsCounts = wb.getNumberOfSheets();
        excel.setSheetCount(sheetsCounts);
        sNames = new String[sheetsCounts];
        // 遍历每一个sheet
        //        for (int i = 0; i < sheetsCounts; i++) {
        Sheet sheet = wb.getSheetAt(0);
        if (!wb.isSheetHidden(0)) { // 判断是否隐藏了，不读取已隐藏的sheet
            log.info("第" + 1 + "个sheet:" + sheet.toString());
            sNames[0] = sheet.getSheetName();
            // 一个sheet表对于一个List
            List list = new LinkedList();

            // 将第一行的列值作为正个json的key
            String[] cellNames;
            // 取第一行列的值作为key
            Row firstRow = sheet.getRow(0);
            // 如果第一行就为空，则是空sheet表，该表跳过
            //                if (null == firstRow) {
            //                    continue;
            //                }
            // 得到第一行有多少列
            int curCellNum = firstRow.getLastCellNum();
            log.info("第一行的列数：" + curCellNum);
            // 根据第一行的列数来生成列头数组
            cellNames = new String[curCellNum];


            // 单独处理第一行，取出第一行的每个列值放在数组中，就得到了整张表的JSON的key
            for (int m = 0; m < curCellNum; m++) {
                Cell cell = firstRow.getCell(m);
                // 设置该列的样式是字符串
                cell.setCellStyle(cellStyle);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                // 取得该列的字符串值
                cellNames[m] = cell.getStringCellValue().replace("*", "");
                //列名转key
                cellNames[m] = cellNameToKey(cellNames[m], cellNameToKey);

                excel.setCellNames(cellNames);
            }
            for (String s : cellNames) {
                log.info("得到第" + 1 + " 张sheet表的列头： " + s + ",");
            }

            // 从第二行起遍历每一行
            int rowNum = sheet.getLastRowNum();
            log.info("总共有 " + rowNum + " 行");
            for (int j = 1; j <= rowNum; j++) {
                // 一行数据对于一个Map
                LinkedHashMap rowMap = new LinkedHashMap();
                // 取得某一行
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                int cellNum = row.getLastCellNum();
                // 遍历每一列
                for (int k = 0; k < cellNum; k++) {
                    Cell cell = row.getCell(k);

                    if (cell == null) {
                        continue;
                    }
                    try {
                        // 保存该单元格的数据到该行中
                        Object cellValue = null;
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // 数字

                                //中文识别不了

                                cell.getCellStyle();
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                                    SimpleDateFormat sdf = null;
                                    if (cell.getCellStyle().getDataFormat() ==
                                        HSSFDataFormat.getBuiltinFormat("h:mm")) {
                                        sdf = new SimpleDateFormat("HH:mm");
                                    } else {// 日期
                                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    }
                                    Date date = cell.getDateCellValue();
                                    cellValue = sdf.format(date);
                                } else if (cell.getCellStyle().getDataFormat() == 58) {
                                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    double value = cell.getNumericCellValue();
                                    Date date = DateUtil.getJavaDate(value);
                                    cellValue = sdf.format(date);
                                } else {
                                    double value = cell.getNumericCellValue();
                                    CellStyle style = cell.getCellStyle();
                                    DecimalFormat format = new DecimalFormat();
                                    String temp = style.getDataFormatString();
                                    // 单元格设置成常规
                                    if (temp.equals("General")) {
                                        format.applyPattern("#");
                                    }
                                    cellValue = format.format(value);
                                }
                                break;
                            case HSSFCell.CELL_TYPE_STRING: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;

                            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;


                            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                                cellValue = cell.getCellFormula() + "";
                                break;


                            case HSSFCell.CELL_TYPE_BLANK: // 空值
                                cellValue = "";
                                break;


                            case HSSFCell.CELL_TYPE_ERROR: // 故障
                                cellValue = "非法字符";
                                break;


                            default:
                                cellValue = "未知类型";
                                break;
                        }

                        if ("".equals(cellValue)) {
                            cellValue = null;
                        }

                        //                        if (cellNames[k].equals("yewuleixing")) {
                        //                            if (!cell.getStringCellValue().trim().equals(businessType)) {
                        //                                throw new RuntimeException("第" + j + "行-" + "第" + (k + 1) + "请输入同一批业务类型,"+"值为"+cell.getStringCellValue());
                        //                            }
                        //                        }


                        //                        if (cellNames[k].equals()){
                        //
                        //                        }
                        //                        if (cellNames[k].equals("gender")){
                        //                            if (cell.getStringCellValue().contains("男")) {
                        //                                cellValue = 1;
                        //                            } else if (cell.getStringCellValue().contains("女")) {
                        //                                cellValue = 0;
                        //                            }
                        //                        }

                        rowMap.put(cellNames[k], cellValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("第" + j + "行-" + "第" + (k + 1) + "列出错,请检查");
                    }

                }

                // 保存该行的数据到该表的List中
                list.add(rowMap);
            }
            // 将该sheet表的表名为key，List转为json后的字符串为Value进行存储
            excelMap.put(sheet.getSheetName(), list);
            excel.setExcelMap(excelMap);
        }
        //        }

       log.info("excel2json方法结束....");

        excel.setSheetNames(sNames);
        return excel;
    }


    /**
     * 解决思路：采用Apache的POI的API来操作Excel，读取内容后保存到List中，再将List转Json（推荐Linked，增删快，与Excel表顺序保持一致）
     * <p>
     * Sheet表1  ————>    List1<Map<列头，列值>>
     * Sheet表2  ————>    List2<Map<列头，列值>>
     * <p>
     * 步骤1：根据Excel版本类型创建对于的Workbook以及CellSytle
     * 步骤2：遍历每一个表中的每一行的每一列
     * 步骤3：一个sheet表就是一个Json，多表就多Json，对应一个 List
     * 一个sheet表的一行数据就是一个 Map
     * 一行中的一列，就把当前列头为key，列值为value存到该列的Map中
     *
     * @param file SSM框架下用户上传的Excel文件
     * @return Map  一个线性HashMap，以Excel的sheet表顺序，并以sheet表明作为key，sheet表转换json后的字符串作为value
     * @throws IOException
     */
    public static Excel excel2json(File file, LinkedHashMap<String, String> cellNameToKey)
        throws IOException {
        log.info("excel2json方法执行....");


        Excel excel = new Excel();
        String[] sNames;
        // 返回的map
        LinkedHashMap<String, List> excelMap = new LinkedHashMap<>();

        // Excel列的样式，主要是为了解决Excel数字科学计数的问题
        CellStyle cellStyle;

        // Excel列的样式，主要是为了解决Excel数字科学计数的问题
        CellStyle cellStyle_D;
        // 根据Excel构成的对象
        Workbook wb;
        // 如果是2007及以上版本，则使用想要的Workbook以及CellStyle
        try {
            log.info(file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        InputStream inputStream = new FileInputStream(file);

        if (file.getName().endsWith("xlsx")) {
            log.info("是2007及以上版本  xlsx");
            excel.setSuffix("xlsx");
            wb = new XSSFWorkbook(inputStream);
            XSSFDataFormat dataFormat = (XSSFDataFormat) wb.createDataFormat();
            cellStyle = wb.createCellStyle();
            cellStyle_D = wb.createCellStyle();
            // 设置Excel列的样式为文本
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            cellStyle_D.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
        } else if (file.getName().endsWith("xls")) {
            log.info("是2007以下版本  xls");
            excel.setSuffix("xls");
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
            HSSFDataFormat dataFormat = (HSSFDataFormat) wb.createDataFormat();
            cellStyle = wb.createCellStyle();
            cellStyle_D = wb.createCellStyle();
            // 设置Excel列的样式为文本
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            cellStyle_D.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
        } else {
            return null;
        }

        // sheet表个数
        int sheetsCounts = wb.getNumberOfSheets();
        excel.setSheetCount(sheetsCounts);
        sNames = new String[sheetsCounts];
        // 遍历每一个sheet
        for (int i = 0; i < sheetsCounts; i++) {
            Sheet sheet = wb.getSheetAt(i);
            log.info("第" + i + "个sheet:" + sheet.toString());
            sNames[i] = sheet.getSheetName();
            // 一个sheet表对于一个List
            List list = new LinkedList();

            // 将第一行的列值作为正个json的key
            String[] cellNames;
            // 取第一行列的值作为key
            Row firstRow = sheet.getRow(0);
            // 如果第一行就为空，则是空sheet表，该表跳过
            if (null == firstRow) {
                continue;
            }
            // 得到第一行有多少列
            int curCellNum = firstRow.getLastCellNum();
            log.info("第一行的列数：" + curCellNum);
            // 根据第一行的列数来生成列头数组
            cellNames = new String[curCellNum];


            // 单独处理第一行，取出第一行的每个列值放在数组中，就得到了整张表的JSON的key
            for (int m = 0; m < curCellNum; m++) {
                Cell cell = firstRow.getCell(m);
                // 设置该列的样式是字符串
                cell.setCellStyle(cellStyle);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                // 取得该列的字符串值
                cellNames[m] = cell.getStringCellValue();
                //列名转key
                cellNames[m] = cellNameToKey(cellNames[m], cellNameToKey);

                excel.setCellNames(cellNames);
            }
            for (String s : cellNames) {
               log.info("得到第" + i + " 张sheet表的列头： " + s + ",");
            }

            // 从第二行起遍历每一行
            int rowNum = sheet.getLastRowNum();
            log.info("总共有 " + rowNum + " 行");
            for (int j = 1; j <= rowNum; j++) {
                // 一行数据对于一个Map
                LinkedHashMap rowMap = new LinkedHashMap();
                // 取得某一行
                Row row = sheet.getRow(j);
                int cellNum = row.getLastCellNum();
                // 遍历每一列
                for (int k = 0; k < cellNum; k++) {
                    Cell cell = row.getCell(k);

                    try {
                        // 保存该单元格的数据到该行中
                        Object cellValue = null;
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // 数字

                                //中文识别不了

                                cell.getCellStyle();
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                                    SimpleDateFormat sdf = null;
                                    if (cell.getCellStyle().getDataFormat() ==
                                        HSSFDataFormat.getBuiltinFormat("h:mm")) {
                                        sdf = new SimpleDateFormat("HH:mm");
                                    } else {// 日期
                                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    }
                                    Date date = cell.getDateCellValue();
                                    cellValue = sdf.format(date);
                                } else if (cell.getCellStyle().getDataFormat() == 58) {
                                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    double value = cell.getNumericCellValue();
                                    Date date = DateUtil.getJavaDate(value);
                                    cellValue = sdf.format(date);
                                } else {
                                    double value = cell.getNumericCellValue();
                                    CellStyle style = cell.getCellStyle();
                                    DecimalFormat format = new DecimalFormat();
                                    String temp = style.getDataFormatString();
                                    // 单元格设置成常规
                                    if (temp.equals("General")) {
                                        format.applyPattern("#");
                                    }
                                    cellValue = format.format(value);
                                }
                                break;
                            case HSSFCell.CELL_TYPE_STRING: // 字符串
                                if (cell.getStringCellValue().equals("男")) {
                                    cellValue = 1;
                                } else if (cell.getStringCellValue().equals("女")) {
                                    cellValue = 0;
                                } else if (cell.getStringCellValue().equals("企业客户")) {
                                    cellValue = "customer_type02";
                                } else if (cell.getStringCellValue().equals("个人客户")) {
                                    cellValue = "customer_type01";
                                } else {
                                    cellValue = cell.getStringCellValue();
                                }
                                break;


                            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;


                            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                                cellValue = cell.getCellFormula() + "";
                                break;


                            case HSSFCell.CELL_TYPE_BLANK: // 空值
                                cellValue = "";
                                break;


                            case HSSFCell.CELL_TYPE_ERROR: // 故障
                                cellValue = "非法字符";
                                break;


                            default:
                                cellValue = "未知类型";
                                break;
                        }


                        if ("".equals(cellValue)) {
                            cellValue = null;
                        }
                        rowMap.put(cellNames[k], cellValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("第" + j + "行-" + "第" + k + 1 + "列出错,请检查");
                    }

                }

                // 保存该行的数据到该表的List中
                list.add(rowMap);
            }
            // 将该sheet表的表名为key，List转为json后的字符串为Value进行存储
            excelMap.put(sheet.getSheetName(), list);
            excel.setExcelMap(excelMap);
        }

        log.info("excel2json方法结束....");

        excel.setSheetNames(sNames);
        return excel;
    }


    /**
     * 类型为double处理方法
     *
     * @param cellValue
     * @return
     */
    public static Object doubleSolution(Object cellValue) {
        if (!"".equals(cellValue)) {
            if (!isDouble(cellValue.toString())) {
                cellValue = cellValue.toString();
            } else {
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                cellValue = nf.format(Double.parseDouble(cellValue.toString()));
            }
        }
        return cellValue;
    }

    /**
     * 列名转key
     *
     * @param cellName 列名
     * @param nK       列名-key 映射列表
     * @return
     */
    public static String cellNameToKey(String cellName, LinkedHashMap<String, String> nK) {
        if (nK.containsKey(cellName)) {
            return nK.get(cellName);
        } else {
            return cellName;
        }
    }


    /**
     * 判断是否为double
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    /**
     * 判断cell类型是否为日期型
     *
     * @param Cell cell
     * @return true 是日期类型  false  否，不是日期类型
     * @throws Exception
     * @title:
     * @author xinyaoli
     * @description:
     * @date
     */
    private static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        boolean isDate = false;
        double d = cell.getNumericCellValue();
        if (DateUtil.isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            if (style == null) {
                return false;
            }
            int i = style.getDataFormat();
            String f = style.getDataFormatString();
            isDate = DateUtil.isADateFormat(i, f);
        }
        return isDate;
    }


    public static void main(String[] args) throws Exception {
        //        MultipartFile file = (MultipartFile) new File("C:/1.xlsx");

        //       System.out.println( excel2json(file));
        //        double d = 1.234567891E10;
        //        System.out.println(d);


    }


}
