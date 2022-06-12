package com.jejujerry.sheetmap.controller;

import com.jejujerry.google.sheet.GSheetRealeLand;
import com.jejujerry.google.sheet.dto.RealeItem;
import com.jejujerry.sheetmap.dto.SheetmapDTO;
import com.jejujerry.sheetmap.dto.PageRequestDTO;
import com.jejujerry.sheetmap.service.SheetmapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
@RequestMapping("/sheetmap")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class SheetmapController {

    private final SheetmapService service; //final로 선언

    @GetMapping("/")
    public String index() {

        return "redirect:/sheetmap/map";
    }

    @GetMapping("/map")
    public void map(Model model) throws GeneralSecurityException, IOException {
        //Model model 은 결과 데이터를 화면에 전달하기 위해서 사용된다.
        log.info("map step 1");



        //////////////////////////////////////////////////////////
        // LIST 형태를 리턴 받아서 리스트형태를 전달하도록 만들자
        ////////////////////////////////////////////////////////////////
        //String temp = new GSheetRealeLand().getRealeLand();
        //List<RealeItem> realeItems = new GSheetRealeLand().getRealeLand();
        //model.addAttribute("realeItems", realeItems);
        //model.addAttribute("reale1", temp);
        //model.addAttribute("reale2", temp);
        log.info("map step 2");
    }


}
