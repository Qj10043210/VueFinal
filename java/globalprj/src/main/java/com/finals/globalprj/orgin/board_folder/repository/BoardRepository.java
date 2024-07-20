package com.finals.globalprj.orgin.board_folder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;

@Repository
public interface BoardRepository extends JpaRepository<BoardModel, Integer>{
    
    @Query("SELECT b FROM BoardModel b WHERE b.boardTerminated <= :boardTerminated")
    List<BoardModel> findAll(Sort sort, @Param("boardTerminated") int boardTerminated);

    @Query("SELECT b FROM BoardModel b WHERE b.boardTerminated <= :boardTerminated")
    List<BoardModel> findAll(@Param("boardTerminated") int boardTerminated);

    Optional<BoardModel> findByBoardNo(int boardNo);

    @Query("SELECT b FROM BoardModel b LEFT JOIN b.articles ar WHERE b.boardTerminated <= :boardTerminated GROUP BY b.boardNo ORDER BY COUNT(ar.articleNo) DESC, b.boardNo ASC")
    List<BoardModel> findAllSortedByReferencesD(@Param("boardTerminated") int boardTerminated);

    @Query("SELECT b FROM BoardModel b LEFT JOIN b.articles ar WHERE b.boardTerminated <= :boardTerminated GROUP BY b.boardNo ORDER BY COUNT(ar.articleNo) ASC, b.boardNo ASC")
    List<BoardModel> findAllSortedByReferencesA(@Param("boardTerminated") int boardTerminated);

    void deleteByBoardNo(int boardNo);
}
