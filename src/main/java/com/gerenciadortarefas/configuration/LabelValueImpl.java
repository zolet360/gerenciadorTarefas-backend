package com.gerenciadortarefas.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelValueImpl implements LabelValue {

    private String label;
    private String value;

    public LabelValueImpl(String label, Number value) {
        super();
        this.label = label;
        this.value = String.valueOf(value);
    }

    public LabelValueImpl(Number label, Number value) {
        super();
        this.label = String.valueOf(label);
        this.value = String.valueOf(value);
    }
}