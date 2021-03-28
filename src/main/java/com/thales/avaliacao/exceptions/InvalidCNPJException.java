package com.thales.avaliacao.exceptions;

public class InvalidCNPJException extends Exception {

    public static final String _DEFAULT_MESSAGE = "CNPJ invalido";

    public InvalidCNPJException() {
        super(InvalidCNPJException._DEFAULT_MESSAGE);
    }

    public InvalidCNPJException(String msg) {
        super(msg);
    }
}
