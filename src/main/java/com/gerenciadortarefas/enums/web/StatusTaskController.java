package com.gerenciadortarefas.enums.web;

import com.gerenciadortarefas.configuration.LabelValueImpl;
import com.gerenciadortarefas.enums.StatusTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enums/status-task")
public class StatusTaskController {
    @GetMapping
    public ResponseEntity<List<LabelValueImpl>> estadoCivil() {
        List<LabelValueImpl> list = StatusTask.listLabelValue();
        return ResponseEntity.ok(list);
    }
}
