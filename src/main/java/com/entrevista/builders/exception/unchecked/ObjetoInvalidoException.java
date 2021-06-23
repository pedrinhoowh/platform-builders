package com.entrevista.builders.exception.unchecked;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ObjetoInvalidoException extends RuntimeException {

    public ObjetoInvalidoException(List<String> mensagens) {
        super(StringUtils.join(mensagens, " ; "));
    }

}
