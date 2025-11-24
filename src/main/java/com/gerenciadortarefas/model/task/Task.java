package com.gerenciadortarefas.model.task;

import com.gerenciadortarefas.enums.StatusTask;
import com.gerenciadortarefas.model.column.BoardColumn;
import com.gerenciadortarefas.model.user.User;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status-task")
    private StatusTask status;

    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User Assignee;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "board_column_id")
    private BoardColumn boardColumn;
}
