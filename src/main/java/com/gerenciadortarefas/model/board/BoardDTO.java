package com.gerenciadortarefas.model.board;

import com.gerenciadortarefas.configuration.LabelValueImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String description;
    private List<LabelValueImpl> boardColumns;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
    }
}
