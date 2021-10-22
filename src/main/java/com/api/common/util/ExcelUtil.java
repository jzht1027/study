package com.api.common.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Iterator;

/**
 * @ClassName ExcelUtil
 * @Description
 * @Author
 * @Date 2021/6/24 10:57
 * @Version 1.0
 **/
public class ExcelUtil {
    /**
     * 获取准确的文件行数
     *
     * @param workbook
     * @return
     */
    public static Sheet getAccuracyContextNum(Workbook workbook) {
        // 取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        // 删除空行
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            // 删除空行
            if (isEmptyRow(row)) {
                int lastRowNum = sheet.getLastRowNum();
                if (i >= 0 && i < lastRowNum) {
                    sheet.shiftRows(i + 1, lastRowNum, -1);// 将行号为i+1一直到行号为lastRowNum的单元格全部上移一行，以便删除i行
                }
                if (i == lastRowNum) {
                    if (row != null) {
                        sheet.removeRow(row);
                    }
                }
                i--;
            }
        }
        return sheet;
    }

    /**
     * 判断是否为空行
     *
     * @param row
     * @return
     */
    private static Boolean isEmptyRow(Row row) {
        if (row == null || row.toString().isEmpty()) {
            return true;
        } else {
            Iterator<Cell> it = row.iterator();
            boolean emptyFlag = true;
            boolean ishas = it.hasNext();
            if (!ishas) {
                emptyFlag = true;
                return emptyFlag;
            }
            while (ishas) {
                Cell cell = it.next();
                if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    emptyFlag = true;
                } else {
                    emptyFlag = false;
                    break;
                }
                return emptyFlag;
            }
        }
        return false;
    }

}
