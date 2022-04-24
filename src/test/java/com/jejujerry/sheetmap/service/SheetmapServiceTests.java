package com.jejujerry.sheetmap.service;

import com.jejujerry.sheetmap.dto.SheetmapDTO;
import com.jejujerry.sheetmap.dto.PageRequestDTO;
import com.jejujerry.sheetmap.dto.PageResultDTO;
import com.jejujerry.sheetmap.entity.Sheetmap;
import com.jejujerry.sheetmap.service.SheetmapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SheetmapServiceTests {

    @Autowired
    private SheetmapService service;

    @Test
    public void testRegister() {

        SheetmapDTO guestbookDTO = SheetmapDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }


    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<SheetmapDTO, Sheetmap> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: "+resultDTO.isPrev());
        System.out.println("NEXT: "+resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("-------------------------------------");
        for (SheetmapDTO sheetmapDTO : resultDTO.getDtoList()) {
            System.out.println(sheetmapDTO);
        }

        System.out.println("========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

}
