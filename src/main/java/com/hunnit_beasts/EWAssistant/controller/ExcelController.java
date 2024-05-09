package com.hunnit_beasts.EWAssistant.controller;

import com.hunnit_beasts.EWAssistant.service.ExcelReadService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelReadService excelReadService;

    @PostMapping
    public ResponseEntity<String> insertExcelData(@RequestBody MultipartFile file) throws IOException {
        String fileName = excelReadService.getFileName(file);
        excelReadService.saveBook(fileName);
        Workbook workbook = excelReadService.selectExcelType(file);
        excelReadService.saveCel(workbook,fileName);
        return new ResponseEntity<>("SUCK_SEX", HttpStatus.OK);
    }
}
