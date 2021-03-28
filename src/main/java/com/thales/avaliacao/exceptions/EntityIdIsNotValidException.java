package com.thales.avaliacao.exceptions;

public class EntityIdIsNotValidException extends Exception {

    public static final String _DEFAULT_MESSAGE = "ID da entidade não é compativel com o solicitado pelo recurso";

    public EntityIdIsNotValidException() {
        super(EntityIdIsNotValidException._DEFAULT_MESSAGE);
    }

    public EntityIdIsNotValidException(String msg) {
        super(msg);
    }
}
