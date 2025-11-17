package com.gerenciadortarefas.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReturnApiDTO {

    private Integer statusCode;
    private String message;
    private Object body;

    public ReturnApiDTO(Integer statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public static ReturnApiDTO exception(Integer statusCode, String message) {
        ReturnApiDTO retornoApiDTO = new ReturnApiDTO();
        retornoApiDTO.setMessage(message);
        retornoApiDTO.setStatusCode(statusCode);
        return retornoApiDTO;
    }

    public static ReturnApiDTO delete() {
        ReturnApiDTO retornoApiDTO = new ReturnApiDTO();
        retornoApiDTO.setMessage("Item excluido com sucesso");
        retornoApiDTO.setStatusCode(204);
        return retornoApiDTO;
    }

}
