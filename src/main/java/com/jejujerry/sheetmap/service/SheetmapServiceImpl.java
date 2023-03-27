package com.jejujerry.sheetmap.service;

import com.jejujerry.sheetmap.dto.SheetmapDTO;
import com.jejujerry.sheetmap.dto.PageRequestDTO;
import com.jejujerry.sheetmap.dto.PageResultDTO;
import com.jejujerry.sheetmap.entity.Sheetmap;
import com.jejujerry.sheetmap.entity.QSheetmap;
import com.jejujerry.sheetmap.repository.SheetmapRepository;
import com.jejujerry.sheetmap.service.SheetmapService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class SheetmapServiceImpl implements SheetmapService {

    private final SheetmapRepository repository;

    @Override
    public Long register(SheetmapDTO dto) {

        log.info("DTO------------------------");
        log.info(dto);

        Sheetmap entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<SheetmapDTO, Sheetmap> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Sheetmap> result = repository.findAll(pageable);

        Function<Sheetmap, SheetmapDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn );
    }


    @Override
    public SheetmapDTO read(Long gno) {

        Optional<Sheetmap> result = repository.findById(gno);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void remove(Long gno) {

        repository.deleteById(gno);

    }

    @Override
    public void modify(SheetmapDTO dto) {

        //업데이트 하는 항목은 '제목', '내용'

        Optional<Sheetmap> result = repository.findById(dto.getGno());

        if(result.isPresent()){

            Sheetmap entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);

        }
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO){

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QSheetmap qSheetmap = QSheetmap.sheetmap;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qSheetmap.gno.gt(0L); // gno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }


        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qSheetmap.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qSheetmap.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qSheetmap.writer.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

}
