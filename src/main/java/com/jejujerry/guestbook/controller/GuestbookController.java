package com.jejujerry.guestbook.controller;


import com.jejujerry.google.sheet.dto.RealeItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jejujerry.guestbook.dto.GuestbookDTO;
import com.jejujerry.guestbook.dto.PageRequestDTO;
import com.jejujerry.guestbook.service.GuestbookService;

import com.jejujerry.google.sheet.GSheetRealeLand;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class GuestbookController {

    private final GuestbookService service; //final로 선언

    //index jerry park
    @GetMapping("/")
    public String index() {

        return "redirect:/guestbook/list";
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        //Model model 은 결과 데이터를 화면에 전달하기 위해서 사용된다.
        log.info("list............." + pageRequestDTO);
        log.info("step 1");
        model.addAttribute("result", service.getList(pageRequestDTO));
        log.info("step 2");
    }

    @GetMapping("/map4construction")
    public void map4constuction(Model model) throws GeneralSecurityException, IOException {
        log.info("controller map4construction");
        List<RealeItem> realeItems = new GSheetRealeLand().getRealeLand("건축할토지");
        model.addAttribute("realeItems", realeItems);
    }

    @GetMapping("/map")
    public void map(Model model) throws GeneralSecurityException, IOException {
        //Model model 은 결과 데이터를 화면에 전달하기 위해서 사용된다.
        log.info("map step 1");

        //////////////////////////////////////////////////////////
        // LIST 형태를 리턴 받아서 리스트형태를 전달하도록 만들자
        ////////////////////////////////////////////////////////////////
        //String temp = new GSheetRealeLand().getRealeLand();
        List<RealeItem> realeItems = new GSheetRealeLand().getRealeLand("건축할토지");
        model.addAttribute("realeItems", realeItems);
        //model.addAttribute("reale2", temp);
        log.info("map step 2");
    }






    @GetMapping("/register")
    public void register(){
        log.info("regiser get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);

        //새로 추가된 엔티티의 번호
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    //@GetMapping("/read")

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){

        log.info("gno: " + gno);

        GuestbookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){


        log.info("gno: " + gno);

        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";

    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());

        redirectAttributes.addAttribute("gno",dto.getGno());


        return "redirect:/guestbook/read";

    }
}
