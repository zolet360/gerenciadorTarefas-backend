package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.board.Board;
import com.gerenciadortarefas.model.board.BoardDTO;
import com.gerenciadortarefas.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO createBoard(BoardDTO dto) {
        Board board = new Board(dto);
        Board save = boardRepository.save(board);
        return new BoardDTO(save);
    }

    public List<BoardDTO> boards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());
    }
}
