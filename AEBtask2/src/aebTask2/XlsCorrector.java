package aebTask2;
//Класс логики программы

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XlsCorrector {
        private Parser parser = new Parser();
        private AEBTask2App app;

        public XlsCorrector(AEBTask2App app) {
                this.app = app;
        }

        public void parseXls(String filePath, JFileChooser chooser) throws IOException {
                //Входная экселька
                HSSFWorkbook inputBook = new HSSFWorkbook(new FileInputStream(filePath));
                HSSFSheet inputBookSheet = inputBook.getSheetAt(0);

                //Создание выходной эксельки
                HSSFWorkbook outputBook = new HSSFWorkbook();
                HSSFSheet outputBookSheet = outputBook.createSheet("matched");

                //Копия первого ряда
                HSSFRow inputRow = inputBookSheet.getRow(0);
                HSSFRow outputRow = outputBookSheet.createRow(0);
                for (int i = 0; i < inputRow.getPhysicalNumberOfCells(); i++) {
                        outputRow.createCell(i).setCellValue(inputRow.getCell(i).toString());
                }

                //Парсинг и обработка данных
                for (int i = 1; i < inputBookSheet.getPhysicalNumberOfRows(); i++) {
                        //Подготовка "ФИО" и "Наменование счета"
                        HSSFRow row = inputBookSheet.getRow(i);
                        String cell2 = row.getCell(1).toString();
                        String cell3 = row.getCell(2).toString();

                        //Создание полученных/обработанных данные в выходной экседьке
                        HSSFRow outRow = outputBookSheet.createRow(i);
                        outRow.createCell(0).setCellValue(row.getCell(0).getRowIndex());
                        outRow.createCell(1).setCellValue(cell2);
                        outRow.createCell(2).setCellValue(parser.parseNSP(cell2, cell3)); // парсинг "Наименование счета"
                }
                //автонастройка размеров столбцов
                outputBookSheet.autoSizeColumn(0);
                outputBookSheet.autoSizeColumn(1);
                outputBookSheet.autoSizeColumn(2);

                FileOutputStream fileOut = new FileOutputStream(chooser.getCurrentDirectory() + "/corrected.xls");
                outputBook.write(fileOut);
                fileOut.close();
                app.finish();
        }
}
