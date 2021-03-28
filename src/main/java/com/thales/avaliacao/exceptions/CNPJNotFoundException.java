package com.thales.avaliacao.exceptions;

public class CNPJNotFoundException extends Exception {

    public static final String _DEFAULT_MESSAGE = "CNPJ não encontrado";

    public CNPJNotFoundException() {
        super(CNPJNotFoundException._DEFAULT_MESSAGE);
    }

    public CNPJNotFoundException(String msg) {
        super(msg);
    }
}
