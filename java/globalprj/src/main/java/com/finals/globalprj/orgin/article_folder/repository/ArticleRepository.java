package com.finals.globalprj.orgin.article_folder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Integer>{
 
    int countByBoardBoardNo(int boardNo);

    void deleteByArticleNo(int articleNo);

    List<ArticleModel> findAllByBoardBoardNo(int boardNo);
    
    List<ArticleModel> findAllByArticleNo(int articleNo);

    ArticleModel findByArticleNo(int articleNo);
}
