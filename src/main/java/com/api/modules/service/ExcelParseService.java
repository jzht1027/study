package com.api.modules.service;

import com.api.common.util.ExcelUtil;
import com.api.modules.dto.FixingPriceBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.websocket.Session;

/**
 * @ClassName ExcelParseService
 * @Description
 * @Author
 * @Date 2021/6/24 14:28
 * @Version 1.0
 **/
public class ExcelParseService {

    public static class ImportPrice {
        public String error;
        public boolean hasError = false;
        public int flag;
        public String message;
    }

    public ImportPrice uploadPrice(InputStream in, String deskEntityId) {
        ImportPrice price =  new ImportPrice();
        try {
            ZipSecureFile.setMinInflateRatio(0.0001);//解决excel太大导致解析不成功的问题
            Workbook workbook = this.createWorkbook(in);
            String rstStr = handleSheet(workbook, deskEntityId);
            price.message = rstStr;
        }catch(Exception e){
            e.printStackTrace();
            price.hasError = true;
            price.error = e.getMessage();
        }
        return price;
    }

    private String handleSheet(Workbook workbook, String deskEntityId) {
        ArrayList<FixingPriceBean> priceList = new ArrayList<>();

        Sheet sheet = ExcelUtil.getAccuracyContextNum(workbook);
        Row indexRow = sheet.getRow(0);

        int lastRowNum = sheet.getLastRowNum();
        for (int r = 1; r <= lastRowNum; r++) {
            Row row = sheet.getRow(r);
            FixingPriceBean priceBean = setFixingPrice(row, indexRow, deskEntityId);
            priceList.add(priceBean);
        }

//        RequestPageBean<List<FixingPriceBean>> model = new RequestPageBean<>();
//        model.setBussinessData(priceList);
//        model.setOptType("uploadFixingPrice");
//        String queryStr = JSONObject.toJSONString(model);
//
//        Session session = SecurityUtils.getSubject().getSession();
//        CurrentUser user = (CurrentUser)session.getAttribute("currentUser");
//        String userId = String.valueOf(user.getNumber());
//
//        MsgDynamicFunction.DynamicFunctionResponse resp = protoBufClientService.sendDynamicAndWait("000131", queryStr, userId, "0",600000l);

        String respStr = "";
//        if(resp.getResponseFlag()==-1){
//            respStr = resp.getResponseMemo();
//        }else{
//            String resutStr = resp.getResponseData().toStringUtf8();
//            ResponePageBean<FixingPriceBean> result = JSON.parseObject(resutStr,
//                    new TypeReference<ResponePageBean<FixingPriceBean>>() {
//                    });
//            respStr = result.getMemo();
//        }
        return respStr;
    }

    private FixingPriceBean setFixingPrice(Row row, Row indexRow, String deskEntityId) {
        short lastCellNum = indexRow.getLastCellNum();
        FixingPriceBean model = new FixingPriceBean();
        for (int c = 0; c < lastCellNum; c++) {
            int cellType = 0;
            String propValue = "";
            Cell cell = row.getCell(c);
            if (cell == null){
                cellType = 3;
            }else {
                cellType = row.getCell(c).getCellType();
            }
            if (0 == cellType){ //数值型
                if(HSSFDateUtil.isCellInternalDateFormatted(cell)) {
                    Date celldate = cell.getDateCellValue();
                    propValue = DateFormatUtils.format(celldate, "yyyyMMddHHmmss");
                }else {
                    double numericCellValue = row.getCell(c).getNumericCellValue();
                    propValue = String.valueOf(numericCellValue);
                }

            }else if (1 == cellType){ //字符串型
                propValue = row.getCell(c).getStringCellValue();
            }else if (3 == cellType){ //空值

            }

            setFixingPriceProperty(c,propValue,indexRow,model,deskEntityId);
        }
        return model;
    }


    private FixingPriceBean setFixingPriceProperty(int c, String propValue, Row indexRow, FixingPriceBean model, String deskEntitiyId) {
        String cellValue = indexRow.getCell(c).getStringCellValue().toString().trim();
        FixingPriceBean fp = model;
        switch (cellValue){
            case "日期":
                fp.setEodDate(propValue);
                break;
            case "证券代码":
                fp.setStkId(propValue);
                break;
            case "交易所":
                fp.setExchId(propValue);
                break;
            case "价格":
                fp.setPrice(propValue);
                break;
            default:
                break;
        }
        fp.setDeskentityId(deskEntitiyId);
        return fp;
    }

    public Workbook createWorkbook(InputStream in) {
        byte[] buf = new byte[1024 * 64*10];
        byte[] bytes = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while (in.read(buf) >= 0) {
                byteArrayOutputStream.write(buf);
            }

            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("读取文件异常, 请尝试重新上传");
        }

        Workbook workbook = null;

        try {
            in = new ByteArrayInputStream(bytes);
            workbook = new XSSFWorkbook(in);
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            in = new ByteArrayInputStream(bytes);
            workbook = new HSSFWorkbook(in);
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("未知文件格式, 请尝试重新保存或另存该文件");
    }

}
