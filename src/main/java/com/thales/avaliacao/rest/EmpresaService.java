package com.thales.avaliacao.rest;

import com.thales.avaliacao.exceptions.CNPJNotFoundException;
import com.thales.avaliacao.exceptions.EmpresaNotFoundException;
import com.thales.avaliacao.exceptions.EntityIdIsNotValidException;
import com.thales.avaliacao.exceptions.InvalidCNPJException;
import com.thales.avaliacao.model.Empresa;
import com.thales.avaliacao.model.EmpresaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpresaService {

    EmpresaDTO createEmpresa(EmpresaDTO empresaDTO) throws InvalidCNPJException;

    EmpresaDTO getEmpresaById(Long id) throws EmpresaNotFoundException;

    EmpresaDTO getEmpresaByCNPJ(String cnpj) throws CNPJNotFoundException;

    void deleteEmpresaById(Long id) throws EmpresaNotFoundException;

    EmpresaDTO updateEmpresa(Long id, EmpresaDTO empresaDTO) throws InvalidCNPJException, EmpresaNotFoundException, EntityIdIsNotValidException;

    Page<Empresa> getEmpresasByParams(String cnpj, String razaoSocial, String nomeFantasia, Pageable pageRequest);
}
