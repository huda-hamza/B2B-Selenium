package com.fawry.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.SystemProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelReader {

    protected String getAbsolutePath(String pathFromContentRoot) {
        return SystemProperties.getProperty("user.dir")+pathFromContentRoot;
    }


    //read the whole Excel sheet

    //read data according to the number of rows and columns to skip
    public Object[][] readExcelData(String filePath, String sheetName, int numberOfRowsToSkip, int numOfColumnsToSkip) {
        filePath = getAbsolutePath(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return new Object[][]{};
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return new Object[][]{};
            }

            int lastRowNum = sheet.getPhysicalNumberOfRows();
            if (lastRowNum < 1 || lastRowNum <= numberOfRowsToSkip) {
                System.out.println("No data found in the sheet: " + sheetName);
                return new Object[][]{};
            }

            int lastCellNum = sheet.getRow(numberOfRowsToSkip).getLastCellNum();
            List<Object[]> data = new ArrayList<>();

            for (int i = numberOfRowsToSkip; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    List<Object> rowData = new ArrayList<>();
                    for (int j = numOfColumnsToSkip; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case STRING:
                                String stringValue = cell.getStringCellValue();
                                //to handle empty\null values in excel cell
                                if (stringValue != null && !stringValue.trim().isEmpty()) {
                                    rowData.add(stringValue);
                                }
                                break;
                            case NUMERIC:
                                rowData.add(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData.add(cell.getBooleanCellValue());
                                break;
                            default:
                                // Skip null values
                        }
                    }
                    if (!rowData.isEmpty()) {
                        data.add(rowData.toArray());
                    }
                }
            }
            return data.toArray(new Object[0][]);

        } catch (IOException e) {
            System.out.println("Error reading the Excel file: " + e.getMessage());
            return new Object[][]{};
        }
    }

    //read data from a specific column
    public List<String> readExcelData(String filePath, String sheetName, String columnName) {
        filePath = getAbsolutePath(filePath);
        List<String> columnData = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return columnData;
            }

            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum < 1) {
                System.out.println("No data found in the sheet: " + sheetName);
                return columnData;
            }

            int columnIdx = findColumnIndex(sheet, columnName);
            if (columnIdx == -1) {
                System.out.println("Column not found: " + columnName);
                return columnData;
            }

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i); // Skip header row
                if (row != null) {
                    Cell cell = row.getCell(columnIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()) {
                        case STRING:
                            String stringValue = cell.getStringCellValue();
                            if (stringValue != null && !stringValue.trim().isEmpty()) {
                                columnData.add(stringValue);
                            }
                            break;
                        case NUMERIC:
                            columnData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            columnData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            // Skip null values
                    }
                }
            }

            return columnData;

        } catch (IOException e) {
            System.out.println("Error reading the Excel file: " + e.getMessage());
            return columnData;
        }
    }

    public Object[][] readExcelData(String filePath, String sheetName) {
        filePath = getAbsolutePath(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                Log.error("Sheet not found");
                return new Object[][]{};
            }

            // Automatic skipping first header row
            int lastRowNum = sheet.getLastRowNum() + 1;
            if (lastRowNum < 1) {
                Log.error("No data found in sheet " + sheetName);
                return new Object[][]{};
            }

            int lastCellNum = sheet.getRow(0).getLastCellNum();
            List<Object[]> data = new ArrayList<>();

            for (int i = 0; i < lastRowNum; i++) {
                Row row = sheet.getRow(i + 1); // Skip header row
                if (row != null) {
                    List<Object> rowData = new ArrayList<>();
                    for (int j = 0; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case STRING:
                                String stringValue = cell.getStringCellValue();
                                if (stringValue != null && !stringValue.trim().isEmpty()) {
                                    rowData.add(stringValue);
                                } else {
                                    rowData.add("");
                                }
                                break;
                            case NUMERIC:
                                rowData.add(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData.add(cell.getBooleanCellValue());
                                break;
                            default:
                                rowData.add(" ");
                        }
                    }
                    if (!rowData.isEmpty()) {
                        data.add(rowData.toArray());
                    }
                }
            }

            return data.toArray(new Object[0][]);

        } catch (IOException e) {
            System.out.println("Error reading the Excel file: " + e.getMessage());
            return new Object[][]{};
        }
    }

    private int findColumnIndex(Sheet sheet, String columnName) {
        Row headerRow = sheet.getRow(0);
        int lastCellNum = headerRow.getLastCellNum();

        for (int j = 0; j < lastCellNum; j++) {
            Cell headerCell = headerRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (columnName.equals(headerCell.getStringCellValue())) {
                return j;
            }
        }

        return -1; // Column not found
    }


}
