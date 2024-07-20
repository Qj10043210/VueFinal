package com.finals.globalprj.orgin.board_folder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.finals.globalprj.orgin.article_folder.services.*;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;
import com.finals.globalprj.orgin.board_folder.model.BoardOption;
import com.finals.globalprj.orgin.board_folder.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardReposit;

    @Autowired
    private ArticleServices articleServices;

    @Transactional
    public BoardModel addNewBoard(BoardModel newBoard){
        return boardReposit.save(newBoard);
    }
    @Transactional
    public Optional<BoardModel> findByBoardNo(int boardNo){        
        return boardReposit.findByBoardNo(boardNo);
    }
    // @Transactional
    // public void deleteByBoardNo(BoardModel currentBoard){
    //     currentBoard.setBoardTerminated(1);
    //     boardReposit.save(currentBoard);
    // }

    @Transactional
    public List<BoardModel> readAllBoard(List<BoardModel> list, BoardOption datas){        
        if(datas.getOrders().equals("name")){
            if(datas.getDirect()==0){
                boardReposit.findAll(Sort.by(Sort.Direction.ASC,"boardNo"), datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }else{
                boardReposit.findAll(Sort.by(Sort.Direction.DESC,"boardNo"), datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }
        }
        else if(datas.getOrders().equals("date")){
            if(datas.getDirect()==0){
                boardReposit.findAll(Sort.by(Sort.Direction.DESC,"boardCreated"), datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }else{
                boardReposit.findAll(Sort.by(Sort.Direction.ASC,"boardCreated"), datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }
        }
        else if(datas.getOrders().equals("amount")){
            if(datas.getDirect()==0){
                boardReposit.findAllSortedByReferencesD(datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }else{
                boardReposit.findAllSortedByReferencesA(datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
            }
        }else{
            boardReposit.findAll(datas.getPower()).forEach(board->{
                    board.setArticleCount(articleServices.countByBoardNo(board.getBoardNo()));
                    list.add(board);
                });
        }
        
        
        return list;
    }

}

