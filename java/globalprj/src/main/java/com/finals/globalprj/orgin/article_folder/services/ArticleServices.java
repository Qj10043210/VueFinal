package com.finals.globalprj.orgin.article_folder.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;
import com.finals.globalprj.orgin.article_folder.model.DumpArticleModel;
import com.finals.globalprj.orgin.article_folder.repository.ArticleRepository;
import com.finals.globalprj.orgin.article_folder.repository.DumpArticleRepository;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArticleServices {
    
    @Autowired ArticleRepository articleReposit;
    @Autowired DumpArticleRepository dumpArticleReposit;

    public List<ArticleModel> readByArticleNo(int articleNo){
        return articleReposit.findAllByArticleNo(articleNo);
    }
    public ArticleModel readSingleByArticleNo(int articleNo){
        return articleReposit.findByArticleNo(articleNo);
    }    
    
    public int countByBoardNo(int boardNo){        
        return articleReposit.countByBoardBoardNo(boardNo);
        // Board에 연결되어있는 boardNo니까!
    }

    public List<ArticleModel> readAllArticles(List<ArticleModel> allArticles, BoardModel entity) {
        articleReposit.findAllByBoardBoardNo(entity.getBoardNo()).forEach(allArticles::add);
        if(allArticles.isEmpty()){
            return null;
        }else{
            return allArticles;
        }
    
    }
    
    public void deleteByArticleNo(int articleNo){
        articleReposit.deleteByArticleNo(articleNo);
    }

    public void saveAllDump(List<ArticleModel> allArticles){
        List<DumpArticleModel> dumpArticles = new ArrayList<DumpArticleModel>();
        allArticles.forEach((item)->{
            DumpArticleModel dumpArticle = new DumpArticleModel();
            dumpArticle.setArticleNo(item.getArticleNo());
            dumpArticle.setBoardNo(item.getBoard().getBoardNo());
            dumpArticle.setUserNo(item.getUser().getUserNo());
            dumpArticle.setUserBan(item.getUser().getUserBan());
            dumpArticle.setArticleTitle(item.getArticleTitle());
            dumpArticle.setArticleContent(item.getArticleContent());
            dumpArticle.setArticleCreated(item.getArticleCreated());
            dumpArticles.add(dumpArticle);
        });
        dumpArticleReposit.saveAll(dumpArticles);
    }

    public ArticleModel save(ArticleModel entity) {
        return articleReposit.save(entity);
    }
}
