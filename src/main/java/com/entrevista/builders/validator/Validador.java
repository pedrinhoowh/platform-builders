package com.entrevista.builders.validator;

import com.entrevista.builders.exception.unchecked.ObjetoInvalidoException;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Predicate;

public abstract class Validador {

    private Map<Predicate, String> validacaoMap = new HashMap<>();
    protected List<String> mensagens;

    public Validador() {
        this.mensagens = new ArrayList<>();
    }

    protected <E> Validador adiciona(Predicate<E> predicate, String reason) {
        validacaoMap.put(predicate, reason);
        return this;
    }

    protected Validador confirma(String fieldName, Object val) {

        this.validacaoMap.forEach((modifier, reason) -> {
            if (!modifier.test(val)) {
                String message = MessageFormat.format("{0} {1}", fieldName, reason);
                mensagens.add(message);
            }
        });
        this.validacaoMap.clear();
        return this;
    }


    public void finaliza() {
        Optional.ofNullable(mensagens).filter(CollectionUtils::isEmpty)
                .orElseThrow(() -> {
                    ObjetoInvalidoException businessException = new ObjetoInvalidoException(mensagens);
                    mensagens.clear();
                    return businessException;
                });
    }
}
