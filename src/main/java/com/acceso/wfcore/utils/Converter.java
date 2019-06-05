package com.acceso.wfcore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.compilers.Sj;

public class Converter {

    //        private ExcelToJsonConverterConfig config = null;
    File file;

    public Converter(File file) {
        this.file = file;
    }

    public ExcelJson XLS_TO_JSON()
            throws Exception {
        ExcelJson excelJson = new ExcelJson();
        excelJson.setFilename(file.getName());
        List<SheetJson> sheets = new ArrayList<>();

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            HSSFSheet sheet = wb.getSheetAt(i);
            HSSFRow row;
            HSSFCell cell;

            SheetJson sheetJsonx = new SheetJson();
            sheetJsonx.setName(sheet.getSheetName());

            List<RowSheetJson> rowsx = new ArrayList<>();

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                RowSheetJson rowSheetJson = new RowSheetJson();
                Map<String, Object> cellsx = new HashMap<>();

                while (cells.hasNext()) {
                    cell = (HSSFCell) cells.next();
                    CellType cellType = cell.getCellTypeEnum();
                    CellReference cr = new CellReference(cell);

                    if (cellType.getCode() == CellType.NUMERIC.getCode()) {
                        System.out.print("->" + cell.getNumericCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getNumericCellValue());
                    } else if (cellType.getCode() == CellType.BOOLEAN.getCode()) {
                        System.out.print("->" + cell.getBooleanCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getBooleanCellValue());
                    } else {
                        System.out.print("->" + cell.getStringCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getStringCellValue());
                    }
                }

                rowSheetJson.setCells(cellsx);
                rowsx.add(rowSheetJson);
            }

            sheetJsonx.setRows(rowsx);
            sheets.add(sheetJsonx);
        }

        excelJson.setSheets(sheets);
        return excelJson;
    }

    public ExcelJson XLSX_TO_JSON()
            throws Exception {

        ExcelJson excelJson = new ExcelJson();
        excelJson.setFilename(file.getName());
        List<SheetJson> sheets = new ArrayList<>();

        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            XSSFSheet sheet = wb.getSheetAt(i);
            XSSFRow row;
            XSSFCell cell;

            SheetJson sheetJsonx = new SheetJson();
            sheetJsonx.setName(sheet.getSheetName());

            List<RowSheetJson> rowsx = new ArrayList<>();

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                RowSheetJson rowSheetJson = new RowSheetJson();
                Map<String, Object> cellsx = new HashMap<>();

                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                    CellType cellType = cell.getCellTypeEnum();
                    CellReference cr = new CellReference(cell);

                    if (cellType.getCode() == CellType.NUMERIC.getCode()) {
                        System.out.print("->" + cell.getNumericCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getNumericCellValue());
                    } else if (cellType.getCode() == CellType.BOOLEAN.getCode()) {
                        System.out.print("->" + cell.getBooleanCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getBooleanCellValue());
                    } else {
                        System.out.print("->" + cell.getStringCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getStringCellValue());
                    }
                }

                rowSheetJson.setCells(cellsx);
                rowsx.add(rowSheetJson);
            }

            sheetJsonx.setRows(rowsx);
            sheets.add(sheetJsonx);
        }

        excelJson.setSheets(sheets);
        return excelJson;
    }

    public File OBJECT_TO_XLS(Object data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        if (data instanceof ArrayList) {
            int rowexp = 0;
            boolean header = true;
            for (Object d1 : (ArrayList) data) {
                Row _row;
                if (d1 instanceof HashMap) {
                    int _cell = 0;
                    if (header) {
                        _row = sheet.createRow(rowexp);
                        Font df = workbook.createFont();
                        df.setBold(true);
                        CellStyle style = workbook.createCellStyle();
                        style.setAlignment(HorizontalAlignment.CENTER);
                        style.setFont(df);

                        for (Object d2 : ((HashMap) d1).entrySet()) {
                            HashMap.Entry<Object, Object> d3 = (HashMap.Entry<Object, Object>) d2;

                            Cell cell = _row.createCell(_cell);
                            cell.setCellStyle(style);
                            cell.setCellValue("" + d3.getKey());
                            _cell++;
                        }
                        header = false;
                        rowexp++;
                    }

                    _row = sheet.createRow(rowexp);
                    _cell = 0;
                    for (Object d2 : ((HashMap) d1).entrySet()) {
                        HashMap.Entry<Object, Object> d3 = (HashMap.Entry<Object, Object>) d2;

                        CellType ct = null;
                        try {
                            Double d = Double.parseDouble("" + d3.getValue());
                            ct = CellType.NUMERIC;
                        } catch (Exception ep) {
                            ct = CellType.STRING;
                        }

                        Cell cell = _row.createCell(_cell);
                        cell.setCellValue("" + d3.getValue());
                        cell.setCellType(ct);

                        _cell++;
                    }
                    rowexp++;
                }
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return file;
    }

    public File OBJECT_TO_TXT(Object data) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            if (data instanceof ArrayList) {
                ArrayList l = (ArrayList) data;
                for (int i = 0; i < l.size(); i++) {
                    String file = "";
                    if (l.get(i) instanceof LinkedHashMap) {
                        LinkedHashMap hx = (LinkedHashMap) l.get(i);

                        Iterator it = hx.values().iterator();
                        while (it.hasNext()) {
                            Object ii = it.next();
                        }

                        file = StringUtils.join(hx.values(), "\t");

                        if (i + 1 == l.size()) {
                            writer.write(file);
                        } else {
                            writer.write(file);
                            writer.newLine();
                        }
                    }
                }
            }

            writer.close();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return file;
    }
}
