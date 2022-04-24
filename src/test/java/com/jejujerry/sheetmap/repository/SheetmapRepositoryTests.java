package com.jejujerry.sheetmap.repository;

import com.jejujerry.sheetmap.entity.Sheetmap;
import com.jejujerry.sheetmap.entity.QSheetmap;
import com.jejujerry.sheetmap.repository.SheetmapRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class SheetmapRepositoryTests {

    @Autowired
    private SheetmapRepository sheetmapRepository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            Sheetmap guestbook = Sheetmap.builder()
                    .title("Title...." + i)
                    .content("Content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(sheetmapRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){
        Optional<Sheetmap> result = sheetmapRepository.findById(300L);

        if(result.isPresent()){
            Sheetmap guestbook = result.get();
            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            sheetmapRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QSheetmap qGuestbook = QSheetmap.sheetmap; //1

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();  //2

        BooleanExpression expression = qGuestbook.title.contains(keyword); //3

        builder.and(expression); //4

        Page<Sheetmap> result = sheetmapRepository.findAll(builder, pageable); //5

        System.out.println("guestbook printing");
        result.stream().forEach(sheetmap -> {
            System.out.println(sheetmap);
        });

    }

//    @Test
//    public void testQuery2() {
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//
//        String keyword = "1";
//
//        BooleanBuilder builder = new BooleanBuilder();
//
//        BooleanExpression exTitle =  qGuestbook.title.contains(keyword);
//
//        BooleanExpression exContent =  qGuestbook.content.contains(keyword);
//
//        BooleanExpression exAll = exTitle.or(exContent); // 1----------------
//
//        builder.and(exAll); //2-----
//
//        builder.and(qGuestbook.gno.gt(0L)); // 3-----------
//
//        Page<Guestbook> result = sheetmapRepository.findAll(builder, pageable);
//
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//
//    }

}
