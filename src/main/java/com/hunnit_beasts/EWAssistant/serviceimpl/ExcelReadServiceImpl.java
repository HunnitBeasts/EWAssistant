package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.book.BookCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.day.DayCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordCreateDTO;
import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.entity.Day;
import com.hunnit_beasts.EWAssistant.service.BookService;
import com.hunnit_beasts.EWAssistant.service.DayService;
import com.hunnit_beasts.EWAssistant.service.ExcelReadService;
import com.hunnit_beasts.EWAssistant.service.WordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ExcelReadServiceImpl implements ExcelReadService {
    private final BookService bookService;
    private final DayService dayService;
    private final WordService wordService;

    @Override
    public String getFileType(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }

    @Override
    public String getFileName(MultipartFile file) {
        return file.getOriginalFilename().split("\\.")[0];
    }

    @Override
    public void saveBook(String name) {
        BookCreateDTO dto = BookCreateDTO.builder()
                .name(name)
                .build();
        bookService.create(dto);
    }

    @Override
    public Workbook selectExcelType(MultipartFile file) throws IOException {
        if (getFileType(file).equals("xls"))
            return new HSSFWorkbook(file.getInputStream());
        else
            return new XSSFWorkbook(file.getInputStream());
    }

    @Override
    public void saveCel(Workbook workbook, String fileName) {
        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if(row == null) continue;
            Day day = setDay(row, fileName);
            registerWord(row,day);
        }
    }

    @Override
    public Day setDay(Row row, String fileName) {
        Long day = (long) row
                .getCell(0)
                .getNumericCellValue();
        Book book = bookService.getBook(fileName);
        if(!dayService.isDay(day, book))
            dayService.create(new DayCreateDTO(day, book));
        return dayService.findId(day,book);
    }

    @Override
    public void registerWord(Row row, Day day) {
        String word = row
                .getCell(1)
                .getStringCellValue();
        String meaning = row
                .getCell(2)
                .getStringCellValue();
        wordService.create(new WordCreateDTO(word,meaning,day));
    }
}
