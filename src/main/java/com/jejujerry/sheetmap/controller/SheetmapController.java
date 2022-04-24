package com.jejujerry.sheetmap.controller;

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

@Controller
@RequestMapping("/sheetmap")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class SheetmapController {

    private final SheetmapService service; //final로 선언


    @GetMapping("/")
    public String index() {

        return "redirect:/sheetmap/list";
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        //Model model 은 결과 데이터를 화면에 전달하기 위해서 사용된다.
        log.info("list............." + pageRequestDTO);
        log.info("step 1");
        model.addAttribute("result", service.getList(pageRequestDTO));
        log.info("step 2");
    }

//    @GetMapping("/register")
//    public void register(){
//        log.info("regiser get...");
//    }
//
//    @PostMapping("/register")
//    public String registerPost(SheetmapDTO dto, RedirectAttributes redirectAttributes){
//
//        log.info("dto..." + dto);
//
//        //새로 추가된 엔티티의 번호
//        Long gno = service.register(dto);
//
//        redirectAttributes.addFlashAttribute("msg", gno);
//
//        return "redirect:/sheetmap/list";
//    }
//
//    //@GetMapping("/read")
//
//    @GetMapping({"/read", "/modify"})
//    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){
//
//        log.info("gno: " + gno);
//
//        SheetmapDTO dto = service.read(gno);
//
//        model.addAttribute("dto", dto);
//
//    }
//
//    @PostMapping("/remove")
//    public String remove(long gno, RedirectAttributes redirectAttributes){
//
//
//        log.info("gno: " + gno);
//
//        service.remove(gno);
//
//        redirectAttributes.addFlashAttribute("msg", gno);
//
//        return "redirect:/sheetmap/list";
//
//    }
//
//    @PostMapping("/modify")
//    public String modify(SheetmapDTO dto,
//                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
//                         RedirectAttributes redirectAttributes){
//
//
//        log.info("post modify.........................................");
//        log.info("dto: " + dto);
//
//        service.modify(dto);
//
//        redirectAttributes.addAttribute("page",requestDTO.getPage());
//        redirectAttributes.addAttribute("type",requestDTO.getType());
//        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());
//
//        redirectAttributes.addAttribute("gno",dto.getGno());
//
//
//        return "redirect:/sheetmap/read";
//
//    }
}
