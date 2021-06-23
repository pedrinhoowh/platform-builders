package com.entrevista.builders.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ValidadorGenerico extends Validador {

    private ValidadorGenerico() {
        super();
    }

    public static ValidadorGenerico of() {
        return new ValidadorGenerico();
    }

    public ValidadorGenerico naoNulo() {
        adiciona(Objects::nonNull, "Campo nulo não permitido!");
        return this;
    }

    public ValidadorGenerico naoVazio() {
        adiciona(StringUtils::isNotEmpty, "Campo vázio não permitido");
        return this;
    }

    public ValidadorGenerico tamanhoMinimo(int min) {
        adiciona(s -> s == null || s.toString().length() >= min, "Tamanho mínimo do campo é: " + min);
        return this;
    }

    public ValidadorGenerico tamanhoMaximo(int max) {
        adiciona(s -> s == null || s.toString().length() <= max, "Tamanho máximo do campo é: " + max);
        return this;
    }

    public ValidadorGenerico confirma(String fieldName, Object val) {
        return (ValidadorGenerico) super.confirma(fieldName, val);
    }

}
