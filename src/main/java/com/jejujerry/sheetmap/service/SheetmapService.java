package com.jejujerry.sheetmap.service;

import com.jejujerry.sheetmap.dto.SheetmapDTO;
import com.jejujerry.sheetmap.dto.PageRequestDTO;
import com.jejujerry.sheetmap.dto.PageResultDTO;
import com.jejujerry.sheetmap.entity.Sheetmap;

public interface SheetmapService {
    Long register(SheetmapDTO dto);

    PageResultDTO<SheetmapDTO, Sheetmap> getList(PageRequestDTO requestDTO);

    SheetmapDTO read(Long gno);

    //void modify(SheetmapDTO dto);

    //void remove(Long gno);

    default Sheetmap dtoToEntity(SheetmapDTO dto){
        Sheetmap entity = Sheetmap.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }


    default SheetmapDTO entityToDto(Sheetmap entity){

        SheetmapDTO dto  = SheetmapDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

}
