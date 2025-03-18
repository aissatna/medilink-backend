package com.aissatna.medilinkbackend.service.export.commun;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter<T> {
    private final Workbook workbook;
    private final XSSFSheet sheet;
    private final CellStyle headerStyle;
    private final CellStyle bodyStyle;

    public ExcelExporter(String sheetName) {
        this.workbook = new XSSFWorkbook();
        this.sheet = (XSSFSheet) workbook.createSheet(sheetName);
        this.headerStyle = initiateHeaderStyle();
        this.bodyStyle = initiateBodyStyle();
    }

    private CellStyle initiateHeaderStyle() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle initiateBodyStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    public void buildHeader(List<String> headers) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            createCell(row, i, headers.get(i), headerStyle);
        }
    }

    public void buildBody(List<T> dataList, List<ColumnExtractor<T>> extractors) {
        int rowNum = 1;
        for (T data : dataList) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < extractors.size(); i++) {
                createCell(row, i, extractors.get(i).extract(data), bodyStyle);
            }
        }
    }

    private void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    public byte[] toByteArray() throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
