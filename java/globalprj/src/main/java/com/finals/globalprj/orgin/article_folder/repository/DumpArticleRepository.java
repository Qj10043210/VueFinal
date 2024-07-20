package com.finals.globalprj.orgin.article_folder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;
import com.finals.globalprj.orgin.article_folder.model.DumpArticleModel;

@Repository
public interface DumpArticleRepository extends JpaRepository<DumpArticleModel, Integer>{
    
}
