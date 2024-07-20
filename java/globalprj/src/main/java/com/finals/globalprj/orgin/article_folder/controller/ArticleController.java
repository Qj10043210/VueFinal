package com.finals.globalprj.orgin.article_folder.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;
import com.finals.globalprj.orgin.article_folder.services.ArticleServices;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/article")
public class ArticleController {
    
    @Autowired
    private ArticleServices articleServices;

    @PostMapping("/readAll")
    public ResponseEntity<?> readAll(@RequestBody BoardModel entity) {        
        // entity.getBoard().getBoardNo()
        try {            
            List<ArticleModel> allArticles = new ArrayList<ArticleModel>();
            allArticles = articleServices.readAllArticles(allArticles, entity);
            if(allArticles ==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
            }else{
                return new ResponseEntity<>(allArticles, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    @GetMapping("/read-{no}")
    public ResponseEntity<?> read(@PathVariable int no){
        try{
            ArticleModel article = new ArticleModel();
            article = articleServices.readSingleByArticleNo(no);
            if(article == null){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);      
            }else{
                return new ResponseEntity<>(article, HttpStatus.OK);
            }            
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    @PostMapping("/dump-{no}")
    public ResponseEntity<?> dump(@PathVariable int no, @RequestBody List<Integer> enlist){
        try {
            System.out.println(enlist);
            List<ArticleModel> allArticles = new ArrayList<ArticleModel>();
            enlist.forEach((item)->{
                allArticles.addAll(articleServices.readByArticleNo(item));                                
            });
            articleServices.saveAllDump(allArticles);
            enlist.forEach((item)->{
                articleServices.deleteByArticleNo(item);
            });


            BoardModel temp = new BoardModel();
            temp.setBoadrNo(no);
            List<ArticleModel> adjustArticles = new ArrayList<ArticleModel>();
            adjustArticles = articleServices.readAllArticles(adjustArticles, temp);
            if(adjustArticles ==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
            }else{
                return new ResponseEntity<>(adjustArticles, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody ArticleModel entity) 
    {
        try {
                ArticleModel newArticle = articleServices.save(entity);
                if(newArticle!=null){
                    return new ResponseEntity<>(newArticle, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
                }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    @PostMapping("/rewrite")
    public ResponseEntity<?> rewrite(@RequestBody ArticleModel entity) {
        System.out.println(entity.getArticleNo());
            try {
                ArticleModel article = articleServices.readSingleByArticleNo(entity.getArticleNo());
                System.out.println(article.getArticleTitle());
                if(article!=null){
                    LocalDateTime ldtObj = LocalDateTime.now();
                    ArticleModel updateArticle = article;
                    updateArticle.setArticleTitle(entity.getArticleTitle());
                    updateArticle.setArticleContent(entity.getArticleContent());
                    updateArticle.setArticleAdjusted(ldtObj);
                    ArticleModel newArticle = articleServices.save(updateArticle);
                    return new ResponseEntity<>(newArticle,HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
                }                
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
            }
    }
    
    
}
