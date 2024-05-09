package com.hunnit_beasts.EWAssistant.excelReadTests;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordGradingDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;
import com.hunnit_beasts.EWAssistant.service.ExcelReadService;
import com.hunnit_beasts.EWAssistant.service.WordService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ExcelReadTest {

    private final WordService wordService;
    private final ExcelReadService excelReadService;
    private MultipartFile multipartFile;

    @Autowired
    public ExcelReadTest(WordService wordService, ExcelReadService excelReadService) {
        this.wordService = wordService;
        this.excelReadService = excelReadService;
    }

    @BeforeEach
    void 더미데이터_생성() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("TestSheet");

        for(int i = 0; i <= 1000; i++){
            Row row = sheet.createRow(i);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(i%20); // 첫 번째 열에 1 삽입
            Cell cell1 = row.createCell(1); // 두 번째 열에 삽입
            cell1.setCellValue("text" + (i+1)); // 텍스트 값에는 i에 1을 더한 값을 사용
            Cell cell2 = row.createCell(2); // 세 번째 열에 삽입
            cell2.setCellValue("테스트" + (i+1)); // 텍스트 값에는 i에 1을 더한 값을 사용
        }

        multipartFile = createMockMultipartFile("기모딱한 단어장" + ".xlsx", workbook);
    }

    // 가짜 MultipartFile 생성
    private MockMultipartFile createMockMultipartFile(String fileName, Workbook workbook) throws IOException {
        // Workbook을 InputStream으로 변환
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

        // MultipartFile 객체 생성
        return new MockMultipartFile("file", fileName, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream);
    }

    @Test
    void 엑셀_불러오기() throws Exception {

        String fileName = excelReadService.getFileName(multipartFile);
        excelReadService.saveBook(fileName);
        Workbook workbook = excelReadService.selectExcelType(multipartFile);
        excelReadService.saveCel(workbook,fileName);

        List<SelectedDTO> dtos = new ArrayList<>();
        dtos.add(new SelectedDTO("기모딱한 단어장",7L));
        dtos.add(new SelectedDTO("기모딱한 단어장",8L));
        dtos.add(new SelectedDTO("기모딱한 단어장",9L));
        dtos.add(new SelectedDTO("기모딱한 단어장",10L));

        List<WordDataDTO> result = wordService.readWordData(dtos);
        for (WordDataDTO wordDataDTO : result)
            System.out.println(wordDataDTO.toString());
    }

    @Test
    void 문제_나갑니다() throws Exception {

        String fileName = excelReadService.getFileName(multipartFile);
        excelReadService.saveBook(fileName);
        Workbook workbook = excelReadService.selectExcelType(multipartFile);
        excelReadService.saveCel(workbook,fileName);

        List<SelectedDTO> dtos = new ArrayList<>();
        dtos.add(new SelectedDTO("기모딱한 단어장",7L));
        dtos.add(new SelectedDTO("기모딱한 단어장",3L));
        dtos.add(new SelectedDTO("기모딱한 단어장",9L));
        dtos.add(new SelectedDTO("기모딱한 단어장",23L));

        List<WordQuestionDTO> result = wordService.createQuestion(dtos,50);
        for (WordQuestionDTO dto : result)
            System.out.println(dto.toString());
    }

    @Test
    void 정답을_순서대로_가져오나연() throws Exception {

        String fileName = excelReadService.getFileName(multipartFile);
        excelReadService.saveBook(fileName);
        Workbook workbook = excelReadService.selectExcelType(multipartFile);
        excelReadService.saveCel(workbook,fileName);

        List<SelectedDTO> dtos = new ArrayList<>();
        dtos.add(new SelectedDTO("기모딱한 단어장",7L));
        dtos.add(new SelectedDTO("기모딱한 단어장",3L));

        List<WordQuestionDTO> result = wordService.createQuestion(dtos,50);
        List<WordSubmitDTO> submits = new ArrayList<>();
        for (WordQuestionDTO dto : result)
            submits.add(new WordSubmitDTO(dto.getId(),dto.getWord()));
        List<WordDataDTO> wordDataDTOS = wordService.readWords(submits);

        for (int i = 0; i < submits.size(); i++) {
            System.out.println(result.get(i));
            System.out.println(wordDataDTOS.get(i));
        }
    }

    @Test
    void 야무진_채점을_하나연() throws Exception {

        String fileName = excelReadService.getFileName(multipartFile);
        excelReadService.saveBook(fileName);
        Workbook workbook = excelReadService.selectExcelType(multipartFile);
        excelReadService.saveCel(workbook,fileName);

        List<SelectedDTO> dtos = new ArrayList<>();
        dtos.add(new SelectedDTO("기모딱한 단어장",7L));
        dtos.add(new SelectedDTO("기모딱한 단어장",3L));
        dtos.add(new SelectedDTO("기모딱한 단어장",11L));
        dtos.add(new SelectedDTO("기모딱한 단어장",15L));

        List<WordQuestionDTO> result = wordService.createQuestion(dtos,5);
        List<WordSubmitDTO> submits = new ArrayList<>();
        for (int j = 0; j < result.size(); j++) {
            String meaning = result.get(j).getWord().replace("text","테스트");
            submits.add(new WordSubmitDTO(result.get(j).getId(),meaning));
        }
        List<WordGradingDTO> wordDataDTOS = wordService.grading(submits);

        for (int i = 0; i < submits.size(); i++) {
            System.out.println(result.get(i));
            System.out.println(wordDataDTOS.get(i));
        }
    }
}
