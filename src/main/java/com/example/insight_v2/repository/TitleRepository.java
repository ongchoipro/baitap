package com.example.insight_v2.repository;

import com.example.insight_v2.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title,Long> {
    Title findByTitleName(String name);
    Title findByTitleId(String titleId);
}
