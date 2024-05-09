package com.hunnit_beasts.EWAssistant.service;

import com.hunnit_beasts.EWAssistant.entity.Day;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelReadService {
    String getFileType(MultipartFile file);
    String getFileName(MultipartFile file);
    void saveBook(String name);
    Workbook selectExcelType(MultipartFile file) throws IOException;
    void saveCel(Workbook workbook, String fileName);
    Day setDay(Row row, String fileName);
    void registerWord(Row row, Day day);
}
