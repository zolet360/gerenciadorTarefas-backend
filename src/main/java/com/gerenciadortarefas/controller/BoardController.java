package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.api.ReturnApiDTO;
import com.gerenciadortarefas.model.board.BoardDTO;
import com.gerenciadortarefas.model.user.UserDTO;
import com.gerenciadortarefas.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping()
    public ResponseEntity<ReturnApiDTO> boards() {
        try {
            List<BoardDTO> boards = boardService.boards();
            return ResponseEntity.status(HttpStatus.OK).body(new ReturnApiDTO(201, boards));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnApiDTO.exception(500, e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<ReturnApiDTO> createBoard(@RequestBody BoardDTO dto) {
        try {
            BoardDTO newBoard = boardService.createBoard(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReturnApiDTO(201, newBoard));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnApiDTO.exception(500, e.getMessage()));
        }
    }
}
