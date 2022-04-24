package com.jejujerry.sheetmap.repository;

import com.jejujerry.sheetmap.entity.Sheetmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface SheetmapRepository extends JpaRepository<Sheetmap, Long>, QuerydslPredicateExecutor<Sheetmap> {
}

