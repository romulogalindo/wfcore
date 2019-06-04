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
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.json.Json;

public class Converter {

    //        private ExcelToJsonConverterConfig config = null;
    File file;

    public Converter(File file) {
        this.file = file;
    }

    public JsonObject XLS_TO_JSON()
            throws Exception {
        JsonObject xlsjson = new JsonObject();
        xlsjson.addProperty("filename", file.getName());

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        JsonArray jsonSheets = new JsonArray();

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            HSSFSheet sheet = wb.getSheetAt(i);
            HSSFRow row;
            HSSFCell cell;

            JsonObject sheetjson = new JsonObject();
            sheetjson.addProperty("name", sheet.getSheetName());

            JsonArray jsonRows = new JsonArray();

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                JsonArray jsonCells = new JsonArray();
                JsonObject json_row = new JsonObject();

                while (cells.hasNext()) {
                    cell = (HSSFCell) cells.next();
                    CellType cellType = cell.getCellType();
                    CellReference cr = new CellReference(cell);

                    if (cellType.getCode() == CellType.NUMERIC.getCode()) {
                        System.out.print("->" + cell.getNumericCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getNumericCellValue());
                    } else if (cellType.getCode() == CellType.BOOLEAN.getCode()) {
                        System.out.print("->" + cell.getBooleanCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getBooleanCellValue());
                    } else {
                        System.out.print("->" + cell.getStringCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getStringCellValue());
                    }

                    jsonCells.add(json_row);
                }

                jsonRows.add(jsonCells);
            }

            //jsonSheets.add(jsonRows);

            sheetjson.add("rows", jsonRows);

            jsonSheets.add(sheetjson);
        }

        xlsjson.add("sheets", jsonSheets);

        return xlsjson;
    }

    public JsonObject XLSX_TO_JSON()
            throws Exception {

        JsonObject xlsjson = new JsonObject();
        xlsjson.addProperty("filename", file.getName());

        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        JsonArray jsonSheets = new JsonArray();

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            XSSFSheet sheet = wb.getSheetAt(i);
            XSSFRow row;
            XSSFCell cell;

            JsonObject sheetjson = new JsonObject();
            sheetjson.addProperty("name", sheet.getSheetName());

            JsonArray jsonRows = new JsonArray();

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                JsonArray jsonCells = new JsonArray();
                JsonObject json_row = new JsonObject();

                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                    CellType cellType = cell.getCellTypeEnum();
                    CellReference cr = new CellReference(cell);

                    if (cellType.getCode() == CellType.NUMERIC.getCode()) {
                        System.out.print("->" + cell.getNumericCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getNumericCellValue());
                    } else if (cellType.getCode() == CellType.BOOLEAN.getCode()) {
                        System.out.print("->" + cell.getBooleanCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getBooleanCellValue());
                    } else {
                        System.out.print("->" + cell.getStringCellValue() + " ");
                        json_row.addProperty(cr.formatAsString(), cell.getStringCellValue());
                    }

                    jsonCells.add(json_row);
                }

                jsonRows.add(jsonCells);
            }

            //jsonSheets.add(jsonRows);

            sheetjson.add("rows", jsonRows);

            jsonSheets.add(sheetjson);
        }

        xlsjson.add("sheets", jsonSheets);

        return xlsjson;
    }

}