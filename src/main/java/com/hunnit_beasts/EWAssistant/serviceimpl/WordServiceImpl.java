package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.*;
import com.hunnit_beasts.EWAssistant.entity.Word;
import com.hunnit_beasts.EWAssistant.enums.ErrorCode;
import com.hunnit_beasts.EWAssistant.repository.jpa.WordJpaRepository;
import com.hunnit_beasts.EWAssistant.repository.querydsl.WordQueryDslRepository;
import com.hunnit_beasts.EWAssistant.service.WordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class WordServiceImpl implements WordService {

    private final WordJpaRepository wordJpaRepository;
    private final WordQueryDslRepository wordQueryDslRepository;

    @Override
    public WordReadDTO create(WordCreateDTO dto) {
        Word entity = dto.entityToCreateDTO();
        Word savedEntity = wordJpaRepository.save(entity);
        return savedEntity.readDTOToEntity();
    }

    @Override
    public WordReadDTO read(Long id) {
        Word entity = wordJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
        return entity.readDTOToEntity();
    }

    @Override
    public WordReadDTO update(WordUpdateDTO dto) {
        Word entity = dto.entityToUpdateDTO();
        Word savedEntity = wordJpaRepository.save(entity);
        return savedEntity.readDTOToEntity();
    }

    @Override
    public Long delete(Long id) {
        wordJpaRepository.deleteById(id);
        return id;
    }

    @Override
    public List<WordDataDTO> readWordData(List<SelectedDTO> dtos) {
        return wordQueryDslRepository.findByWords(dtos);
    }

    @Override
    public List<WordDataDTO> readWords(List<WordSubmitDTO> dtos) {
        return wordQueryDslRepository.findAllByIds(dtos);
    }

}
