package com.thales.avaliacao.service;

import com.thales.avaliacao.exceptions.CNPJNotFoundException;
import com.thales.avaliacao.exceptions.EmpresaNotFoundException;
import com.thales.avaliacao.exceptions.EntityIdIsNotValidException;
import com.thales.avaliacao.exceptions.InvalidCNPJException;
import com.thales.avaliacao.model.Empresa;
import com.thales.avaliacao.model.EmpresaDTO;
import com.thales.avaliacao.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    public final EmpresaRepository empresaRepository;

    @Autowired
    EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO) throws InvalidCNPJException {
        Empresa empresa = empresaDTO.toEntity();

        if (!empresa.isValidCNPJ()) {
            throw new InvalidCNPJException();
        }

        return empresaDTO.fromEntity(this.empresaRepository.save(empresa));
    }

    @Override
    public EmpresaDTO getEmpresaById(Long id) throws EmpresaNotFoundException {
        return new EmpresaDTO(this.empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new));
    }

    @Override
    public EmpresaDTO getEmpresaByCNPJ(String cnpj) throws CNPJNotFoundException {
        return new EmpresaDTO(this.empresaRepository.findFirstByCnpj(cnpj).orElseThrow(CNPJNotFoundException::new));
    }

    @Override
    public void deleteEmpresaById(Long id) throws EmpresaNotFoundException {
        this.getEmpresaById(id);
        this.empresaRepository.deleteById(id);
    }

    @Override
    public EmpresaDTO updateEmpresa(Long id, EmpresaDTO empresaDTO) throws InvalidCNPJException, EmpresaNotFoundException, EntityIdIsNotValidException {
        EmpresaDTO empresa = this.getEmpresaById(id);

        if (empresaDTO.getId() != null && (!empresaDTO.getId().equals(empresa.getId()))) {
            throw new EntityIdIsNotValidException();
        }

        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        Empresa empresaEntity = empresa.toEntity();

        if (!empresaEntity.isValidCNPJ()) {
            throw new InvalidCNPJException();
        }

        return empresaDTO.fromEntity(this.empresaRepository.save(empresaEntity));
    }

    @Override
    public Page<Empresa> getEmpresasByParams(String cnpj, String razaoSocial, String nomeFantasia, Pageable pageRequest) {
        return this.empresaRepository.findAllByCnpjContainsAndRazaoSocialContainsIgnoreCaseAndNomeFantasiaContainsIgnoreCase(cnpj, razaoSocial, nomeFantasia, pageRequest);
    }


}
