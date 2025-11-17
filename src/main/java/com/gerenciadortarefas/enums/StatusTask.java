package com.gerenciadortarefas.enums;

import com.gerenciadortarefas.configuration.LabelValue;
import com.gerenciadortarefas.configuration.LabelValueImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum StatusTask implements LabelValue {

    NOVA("Nova"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada");

    private String label;


    @Override
    public String getValue() {
        return this.toString();
    }

    public static List<LabelValueImpl> listLabelValue() {
        List<LabelValueImpl> list = new ArrayList<>();
        for (StatusTask options : StatusTask.values()) {
            list.add(new LabelValueImpl(options.getLabel(), options.getValue()));
        }
        return list;
    }

    public static StatusTask buscarPorValor(String value) {
        for (StatusTask option : StatusTask.values()) {
            if (option.getValue().equals(value)) {
                return option;
            }
        }
        return null;
    }
}
