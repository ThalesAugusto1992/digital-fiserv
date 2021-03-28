package com.thales.avaliacao.exceptions;

public class EmpresaNotFoundException extends Exception {

    public static final String _DEFAULT_MESSAGE = "ID não encontrado";

    public EmpresaNotFoundException() {
        super(EmpresaNotFoundException._DEFAULT_MESSAGE);
    }

    public EmpresaNotFoundException(String msg) {
        super(msg);
    }
}
