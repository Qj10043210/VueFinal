package com.finals.globalprj.orgin.board_folder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finals.globalprj.orgin.board_folder.model.BoardModel;
import com.finals.globalprj.orgin.board_folder.model.BoardOption;
import com.finals.globalprj.orgin.board_folder.repository.BoardRepository;
import com.finals.globalprj.orgin.board_folder.services.BoardService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardReposit;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewBoard(@RequestBody BoardModel entity) {
        try {
            if(entity.getBoardName() != null){
                Optional<BoardModel> currentBoard = boardService.findByBoardNo(entity.getBoardNo());
                if(currentBoard.isPresent()){                    
                    BoardModel updateBoard = currentBoard.get();
                    updateBoard.setBoardName(entity.getBoardName());
                    updateBoard.setBoardExpression(entity.getBoardExpression());
                    updateBoard.setBoardExpressionTerm(entity.getBoardExpressionTerm());
                    updateBoard.setBoardStyle(entity.getBoardStyle());
                    BoardModel newBoard = boardService.addNewBoard(updateBoard);
                    return new ResponseEntity<>(newBoard, HttpStatus.CREATED);
                }else{
                    BoardModel newBoard = boardService.addNewBoard(entity);
                    return new ResponseEntity<>(newBoard, HttpStatus.CREATED);
                }                
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/readAll")
    public ResponseEntity<?> readAllBoard(@RequestBody BoardOption datas){
        try{            
            System.out.println(datas.getPower());
            List<BoardModel> allBoard = new ArrayList<BoardModel>();
            allBoard = boardService.readAllBoard(allBoard, datas);            
            if(allBoard.isEmpty()){
                System.out.println("성공2");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);            
            }
            System.out.println("성공3");
            return new ResponseEntity<>(allBoard, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    
    @Transactional
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody BoardOption entity) {
        try {
            int boardNo = entity.getBoardNo();
            if(boardNo != 0){
                Optional<BoardModel> currentBoard = boardService.findByBoardNo(boardNo);
                if(currentBoard.isPresent()){
                    BoardModel updateBoard = currentBoard.get();
                    updateBoard.setBoardTerminated(1);
                    boardReposit.save(updateBoard);                    
                }
            }            
            List<BoardModel> allBoard = new ArrayList<BoardModel>();
            allBoard = boardService.readAllBoard(allBoard, entity);
            return new ResponseEntity<>(allBoard, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @PostMapping("/live")
    public ResponseEntity<?> live(@RequestBody BoardOption entity) {
        try {
            int boardNo = entity.getBoardNo();
            if(boardNo != 0){
                Optional<BoardModel> currentBoard = boardService.findByBoardNo(boardNo);
                if(currentBoard.isPresent()){
                    BoardModel updateBoard = currentBoard.get();
                    updateBoard.setBoardTerminated(0);
                    boardReposit.save(updateBoard);                    
                }
            }            
            List<BoardModel> allBoard = new ArrayList<BoardModel>();
            allBoard = boardService.readAllBoard(allBoard, entity);
            return new ResponseEntity<>(allBoard, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
}
