package com.acceso.wfcore.utils;

import java.io.*;
import java.lang.module.Configuration;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

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
//                        System.out.println("->" + cell.getNumericCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getNumericCellValue());
                    } else if (cellType.getCode() == CellType.BOOLEAN.getCode()) {
//                        System.out.println("->" + cell.getBooleanCellValue() + " ");
                        cellsx.put("" + (cr.getCol() + 1), cell.getBooleanCellValue());
                    } else if (cellType.getCode() == CellType.FORMULA.getCode()) {
//                        System.out.println("->" + cell.getCachedFormulaResultType() + " ");
                        if (cell.getCachedFormulaResultType().getCode() == CellType.NUMERIC.getCode()) {
//                            System.out.println("->" + cell.getNumericCellValue() + " ");
                            cellsx.put("" + (cr.getCol() + 1), cell.getNumericCellValue());
                        } else if (cell.getCachedFormulaResultType().getCode() == CellType.STRING.getCode()) {
//                            System.out.println("1->" + cell.getRichStringCellValue() + " ");
                            cellsx.put("" + (cr.getCol() + 1), cell.getRichStringCellValue());
                        }
//                        System.out.println("->" + cell.getBooleanCellValue() + " ");
//                        cellsx.put("" + (cr.getCol() + 1), cell.getBooleanCellValue());
                    } else {
                        try {
//                            System.out.println("1->" + cell.getStringCellValue() + " ");
                            cellsx.put("" + (cr.getCol() + 1), cell.getStringCellValue());
                        } catch (Exception ep) {
//                            System.out.println("ep = " + ep);
                            ep.printStackTrace();
                        }
                    }
                }

                rowSheetJson.setCells(cellsx);
                rowsx.add(rowSheetJson);
            }

            sheetJsonx.setRows(rowsx);
            sheets.add(sheetJsonx);
        }

        excelJson.setSheets(sheets);
//        System.out.println("excelJson = " + excelJson);
        return excelJson;
    }

    public File OBJECT_TO_XLS(Object data) {
        System.out.println("data = " + data.getClass());
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

                        Cell cell;
                        CellType ct = null;
                        try {
                            Double d = Double.parseDouble("" + d3.getValue());
                            cell = _row.createCell(_cell, CellType.NUMERIC);
                            cell.setCellValue(d);
                        } catch (Exception ep) {
                            cell = _row.createCell(_cell, CellType.STRING);
                            cell.setCellValue("" + d3.getValue());
                        }

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
            workbook.close();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return file;
    }

    public File OBJECT_TO_XLS(Object data, boolean havetitle, Map<Integer, Object> configuration) {
        System.out.println("data = " + data.getClass());
        System.out.println("havetitle = " + havetitle);
        System.out.println("configuration = " + configuration);
        if (data == null || !(data instanceof ArrayList | data instanceof ValpagJson)) {
            return null;
        }

        System.out.println("LIsto!XLS");
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("Sheet1");
        if (data instanceof com.acceso.wfcore.utils.ValpagJson) {
            //!"
            System.out.println("Crendo XLS JSON!");
            List<CellJson> regions = new ArrayList<>();
            List<CellJson> styles = new ArrayList<>();
            Map<String, CellStyle> mstyles = new HashMap<>();
            List<CellJson> widths = new ArrayList<>();
            com.acceso.wfcore.utils.ValpagJson valpagJson = (com.acceso.wfcore.utils.ValpagJson) data;
            for (RowJson rowJson : valpagJson.getRows()) {
                for (StandarRegisterJson registerJson : rowJson.getRegs()) {
//                    System.out.println("r!egisterJson => " + registerJson);
                    CellJson cellJson = (CellJson) registerJson;
                    CellReference cr = new CellReference(cellJson.getNo_addres());
//                    System.out.println("cr = " + cr + ", cellJson=" + cellJson);
                    SXSSFRow row = null;
                    try {
                        row = sheet.getRow(cr.getRow());
                        if (row == null) {
                            row = sheet.createRow(cr.getRow());
                        }
                    } catch (Exception ep) {
                        //ON error
                        row = sheet.createRow(cr.getRow());
                    }
                    //CELL
                    SXSSFCell cell = null;
                    try {
                        cell = row.getCell(cr.getCol());
                        if (cell == null) {
                            cell = row.createCell(cr.getCol());
                        }
                    } catch (Exception ep) {
                        //ON error
                        cell = row.createCell(cr.getCol());
                    }

                    cell.setCellValue(cellJson.va_pagreg);
                    if (cellJson.getNu_colspan() != null || cellJson.getNu_rowspan() != null) {
                        regions.add(cellJson);
                    }

                    if (cellJson.getColumnConfigJson() != null) {
                        styles.add(cellJson);
                    }

                }
            }
            //una vez agregada la data ahora toca join region!
            for (CellJson cellJson : regions) {
                CellReference cr = new CellReference(cellJson.getNo_addres());
                int firstCol = (cellJson.getNu_colspan() != null & cellJson.getNu_colspan() < 0) ? 1 : cellJson.getNu_colspan();
                int firstRow = (cellJson.getNu_rowspan() != null & cellJson.getNu_rowspan() < 0) ? 1 : cellJson.getNu_rowspan();
                firstCol = firstCol + cr.getCol() - 1;
                firstRow = firstRow + cr.getRow() - 1;
                if (!(cr.getRow() == firstRow & cr.getCol() == firstCol)) {
                    sheet.addMergedRegion(new CellRangeAddress(cr.getRow(), firstRow, cr.getCol(), firstCol));
                }
            }

            for (CellJson cellJson : styles) {
                CellReference cr = new CellReference(cellJson.getNo_addres());
                ColumnConfigJson configJson = cellJson.getColumnConfigJson();
                CellStyle customStyle = mstyles.get(configJson.getUXCode());

                if (customStyle == null) {
                    Font customFont = workbook.createFont();
                    customStyle = workbook.createCellStyle();
                    System.out.println("(*)customStyle = " + customStyle);
                    if (configJson.getAlign() != null) {
                        switch (configJson.getAlign()) {
                            case "CENTER": {
                                customStyle.setAlignment(HorizontalAlignment.CENTER);
                                break;
                            }
                            case "LEFT": {
                                customStyle.setAlignment(HorizontalAlignment.LEFT);
                                break;
                            }
                            case "RIGHT": {
                                customStyle.setAlignment(HorizontalAlignment.RIGHT);
                                break;
                            }
                            case "JUSTIFY": {
                                customStyle.setAlignment(HorizontalAlignment.JUSTIFY);
                                break;
                            }
                        }
                    }

                    if (configJson.getValign() != null) {
                        switch (configJson.getValign()) {
                            case "CENTER": {
                                customStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                                break;
                            }
                            case "TOP": {
                                customStyle.setVerticalAlignment(VerticalAlignment.TOP);
                                break;
                            }
                            case "BOTTOM": {
                                customStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
                                break;
                            }
                        }
                    }

                    if (configJson.getColor() != null) {
                        customStyle.setFillForegroundColor(Util.getColor(configJson.getColor()));
                        customFont.setColor(Util.getColor(configJson.getColor()));
                    }

                    if (configJson.getBgcolor() != null) {
                        customStyle.setFillBackgroundColor(Util.getColor(configJson.getBgcolor()));
                    }

                    if (configJson.isVwrap()) {
                        customStyle.setWrapText(true);
                    }

                    if (configJson.getBorderTop() != null) {
                        customStyle = getBorderStyle(customStyle, configJson.getBorderTop());
                    }
                    if (configJson.getBorderRight() != null) {
                        customStyle = getBorderStyle(customStyle, configJson.getBorderRight());
                    }
                    if (configJson.getBorderBottom() != null) {
                        customStyle = getBorderStyle(customStyle, configJson.getBorderBottom());
                    }
                    if (configJson.getBorderLeft() != null) {
                        customStyle = getBorderStyle(customStyle, configJson.getBorderLeft());
                    }

                    if (configJson.isBold()) {
                        customFont.setBold(true);
                    }

                    if (configJson.isItalic()) {
                        customFont.setItalic(true);
                    }

                    if (configJson.isUnderline()) {
                        customFont.setUnderline(HSSFFont.U_SINGLE);
                    }

                    if (configJson.isUnderline()) {
                        customFont.setUnderline(HSSFFont.U_SINGLE);
                    }

                    if (configJson.getSize() != null) {
                        customFont.setFontHeightInPoints(configJson.getSize().shortValue());
                    }

                    if (configJson.getFont() != null) {
                        customFont.setFontName(configJson.getFont());
                    }

                    customStyle.setFont(customFont);
                    mstyles.put(configJson.getUXCode(), customStyle);
                }

//                System.out.println("cr.getRow() = " + cr.getRow() + ", cr.getCol() = " + cr.getCol());
//                System.out.println("sheet.getRow(cr.getRow()) = " + sheet.getRow(cr.getRow()));
//                System.out.println("sheet.getRow(cr.getRow()).getCell(cr.getCol()) = " + sheet.getRow(cr.getRow()).getCell(cr.getCol()));
                try {
                    sheet.getRow(cr.getRow()).getCell(cr.getCol()).setCellStyle(customStyle);
                } catch (Exception ep) {
                    System.out.print("[E:" + ep.getMessage() + "]");
//                    ep.printStackTrace();
                }

                if (configJson.getWidth() > -1) {
                    widths.add(cellJson);

                }
            }

            for (CellJson cellJson : styles) {
                CellReference cr = new CellReference(cellJson.getNo_addres());
                ColumnConfigJson configJson = cellJson.getColumnConfigJson();
//                System.out.println("configJson.getWidth() = " + configJson.getWidth());
//                    if (configJson.getWidth() > -1) {
                sheet.setColumnWidth(cr.getCol(), configJson.getWidth() * 100);
//                    }
            }

        } else {
            List<HashMap<Object, Object>> datalist = (ArrayList) data;
            int currentRow = 0;
            int currentCol = 0;

            /*DEFAULT FONT1*/
            Font defaultFont = workbook.createFont();
            defaultFont.setBold(true);

            /*DEFAULT FONT2*/
            Font defaultFont2 = workbook.createFont();
            defaultFont2.setBold(false);

            /*DEFAULT TITLE STYLE*/
            CellStyle styleTitle = workbook.createCellStyle();
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            styleTitle.setFont(defaultFont);

            /*DEFAULT TITLE STYLE*/
            CellStyle styledefault = workbook.createCellStyle();
            styledefault.setAlignment(HorizontalAlignment.LEFT);
            styledefault.setFont(defaultFont2);

            DataFormat format = workbook.createDataFormat();
            //TYPE's
            Map<Integer, Integer> types = new HashMap<>();
            Map<Integer, String> formatDate = new HashMap<>();
            List<Integer> autosizecolumns = new ArrayList<>();

            for (int i = 0; i < datalist.get(0).size(); i++) {
                ColumnConfigJson configJson = (ColumnConfigJson) configuration.get(i + 1);
                int cellType = 0;

                if (configJson != null) {
                    Font customFont = workbook.createFont();
                    CellStyle customStyle = workbook.createCellStyle();
//                System.out.println("configJson.getAlign() = " + configJson.getAlign());
                    switch (configJson.getAlign()) {
                        case "CENTER": {
                            customStyle.setAlignment(HorizontalAlignment.CENTER);
                            break;
                        }
                        case "LEFT": {
                            customStyle.setAlignment(HorizontalAlignment.LEFT);
                            break;
                        }
                        case "RIGHT": {
                            customStyle.setAlignment(HorizontalAlignment.RIGHT);
                            break;
                        }
                        case "JUSTIFY": {
                            customStyle.setAlignment(HorizontalAlignment.JUSTIFY);
                            break;
                        }
                    }


                    if (configJson.getColor() != null) {
                        IndexedColors.LIGHT_GREEN.getIndex();
                        customStyle.setFillForegroundColor(Util.getColor(configJson.getColor()));
                        customFont.setColor(Util.getColor(configJson.getColor()));
                    }
                    if (configJson.getBgcolor() != null) {
                        customStyle.setFillBackgroundColor(Util.getColor(configJson.getBgcolor()));
                    }

//                System.out.println("configJson.isWrap() = " + configJson.isVwrap());
                    if (configJson.isVwrap()) {
//                    System.out.println("configJson.isWrap() ==> " + configJson.isVwrap());
                        customStyle.setWrapText(true);
                    }
//                    System.out.println("configJson.getFormat()  => " + configJson.getFormat());
                    if (configJson.getFormat() != null & configJson.getFormat().length() > 0) {
//                        System.out.println("setDataFormat ==> " + configJson.getFormat() + ", ==>" + format.getFormat(configJson.getFormat()));
                        customStyle.setDataFormat(format.getFormat(configJson.getFormat()));
                        formatDate.put(i + 1, configJson.getFormat());
                    }

                    if (configJson.isHwrap()) {
//                        System.out.println("configJson.isWrap() ==> " + configJson.isHwrap());
                        autosizecolumns.add((i + 1));
                    }

                    if (configJson.isBold()) {
                        customFont.setBold(true);
                    }
//                    System.out.println("configJson.getWidth() = " + configJson.getWidth());
                    if (configJson.getWidth() > -1) {
                        sheet.setColumnWidth(i, configJson.getWidth() * 100);
                    }

                    customStyle.setFont(customFont);
                    configuration.put((i + 1), customStyle);

                    //DEl tipo de celda
                    if (configJson.getType() != null) {
                        switch (configJson.getType()) {
                            case "STRING": {
                                cellType = CellType.STRING.getCode();
                                break;
                            }
                            case "NUMERIC": {
                                cellType = CellType.NUMERIC.getCode();
                                break;
                            }
                            case "DATE": {
                                cellType = 6;
                                break;
                            }
                            default: {
                                cellType = CellType.STRING.getCode();
                                break;
                            }
                        }
                    } else {
                        cellType = CellType.STRING.getCode();
                    }
                } else {
                    configuration.put((i + 1), styledefault);

                    //DEl tipo de celda
                    cellType = CellType.STRING.getCode();
                }
                types.put(i + 1, cellType);
            }

            if (havetitle) {
                Row _row = sheet.createRow(currentRow);
                for (HashMap.Entry<Object, Object> col : datalist.get(0).entrySet()) {
                    Cell cell = _row.createCell(currentCol, CellType.STRING);
                    cell.setCellStyle(styleTitle);
                    cell.setCellValue("" + col.getKey());
//                    System.out.println("cell = >>>[id:" + currentCol + "][" + col.getKey() + ":" + col.getValue() + "]");
                    currentCol++;
                }
                currentCol = 0;
                currentRow++;
            }
            datalist.stream().forEach(pararelRow -> {
                Row _row = sheet.createRow(sheet.getPhysicalNumberOfRows());
                int _currentCol = 0;

                for (HashMap.Entry<Object, Object> col : pararelRow.entrySet()) {
                    Cell cell;
                    try {

                        if (types.get(_currentCol + 1) == CellType.NUMERIC.getCode()) {
                            Double d = Double.parseDouble("" + col.getValue());
                            cell = _row.createCell(_currentCol, CellType.NUMERIC);
                            cell.setCellValue(d);
                        } else if (types.get(_currentCol + 1) == 6) {
                            cell = _row.createCell(_currentCol);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            //get date format
                            cell.setCellValue(sdf.parse(col.getValue().toString()));
                        } else if (types.get(_currentCol + 1) == CellType.STRING.getCode()) {
//                        Double d = Double.parseDouble("" + col.getValue());
                            cell = _row.createCell(_currentCol, CellType.STRING);
//                        cell.setCellValue(d);
                            if (col.getValue() == null) {
                                cell.setCellValue("");
                            } else {
                                cell.setCellValue(col.getValue().toString());
                            }

                        } else {
                            Double d = Double.parseDouble("" + col.getValue());
                            cell = _row.createCell(_currentCol, CellType.STRING);
                            cell.setCellValue(d);
                        }


                    } catch (Exception ep) {
                        cell = _row.createCell(_currentCol, CellType.STRING);
                        try {
                            if (col.getValue() != null) {
                                cell.setCellValue("" + col.getValue());
                            } else {
                                cell.setCellValue("");
                            }
                        } catch (Exception ep2) {
                            cell.setCellValue("");
                        }

                    }
                    cell.setCellStyle((CellStyle) configuration.get(_currentCol + 1));

                    _currentCol++;
                }
                if (sheet.getPhysicalNumberOfRows() % 1000 == 0)
                    System.out.println("MIL+ = " + _currentCol + "===>OK");
            });
        }


        try {
            System.out.println("BUILDING XLS...");
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("BUILDING XLS...OK");
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

    public File OBJECT_TO_CSV(Object data) {
        try {
            Writer writer = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            if (data instanceof ArrayList) {
                int rowexp = 0;
                boolean header = true;
                for (Object d1 : (ArrayList) data) {
                    System.out.println("d1 = " + d1);

                    List<String> _row;
                    if (d1 instanceof HashMap) {
                        int _cell = 0;
                        if (header) {
                            _row = new ArrayList<>();

                            for (Object d2 : ((HashMap) d1).entrySet()) {
                                HashMap.Entry<Object, Object> d3 = (HashMap.Entry<Object, Object>) d2;

                                _row.add("" + d3.getKey());
                                _cell++;
                            }

                            header = false;
                            rowexp++;

                            String[] tmparr = new String[_row.size()];
                            tmparr = _row.toArray(tmparr);
                            csvWriter.writeNext(tmparr);
                        }

                        _row = new ArrayList<>();

                        _cell = 0;
                        for (Object d2 : ((HashMap) d1).entrySet()) {
                            HashMap.Entry<Object, Object> d3 = (HashMap.Entry<Object, Object>) d2;

                            _row.add("" + d3.getValue());
                            _cell++;
                        }

                        String[] tmparr = new String[_row.size()];
                        tmparr = _row.toArray(tmparr);
                        csvWriter.writeNext(tmparr);

                        rowexp++;
                    }
                }
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return file;
    }

    public File ARCHIVO(String co_archiv) {

        return null;
    }

    public File DOWNLOAD(Object ur_file) {
        try {
            FileUtils.copyURLToFile(
                    new URL(ur_file.toString()),
                    file);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        return file;
    }

    public CellStyle getBorderStyle(CellStyle customStyle, String borderType) {
        switch (borderType) {
            case "THIN": {
                customStyle.setBorderBottom(BorderStyle.THIN);
                break;
            }
            case "MEDIUM": {
                customStyle.setBorderBottom(BorderStyle.MEDIUM);
                break;
            }
            case "DOUBLE": {
                customStyle.setBorderBottom(BorderStyle.DOUBLE);
                break;
            }
            case "DOTTED": {
                customStyle.setBorderBottom(BorderStyle.DOTTED);
                break;
            }
            case "DASHED": {
                customStyle.setBorderBottom(BorderStyle.DASHED);
                break;
            }
            case "HAIR": {
                customStyle.setBorderBottom(BorderStyle.HAIR);
                break;
            }
            case "DASH_DOT": {
                customStyle.setBorderBottom(BorderStyle.DASH_DOT);
                break;
            }
            case "DASH_DOT_DOT": {
                customStyle.setBorderBottom(BorderStyle.DASH_DOT_DOT);
                break;
            }
            case "SLANTED_DASH_DOT": {
                customStyle.setBorderBottom(BorderStyle.SLANTED_DASH_DOT);
                break;
            }
            case "MEDIUM_DASHED": {
                customStyle.setBorderBottom(BorderStyle.MEDIUM_DASHED);
                break;
            }
            case "MEDIUM_DASH_DOT": {
                customStyle.setBorderBottom(BorderStyle.MEDIUM_DASH_DOT);
                break;
            }
            case "MEDIUM_DASH_DOT_DOT": {
                customStyle.setBorderBottom(BorderStyle.MEDIUM_DASH_DOT_DOT);
                break;
            }
        }
        return customStyle;
    }
}
