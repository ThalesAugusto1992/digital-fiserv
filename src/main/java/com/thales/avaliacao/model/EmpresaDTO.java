package com.thales.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

    public Long id;

    public Date createdAt;

    public Date updatedAt;

    public String razaoSocial;

    public String nomeFantasia;

    public String cnpj;


    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.getId();
        this.createdAt = empresa.getCreatedAt();
        this.updatedAt = empresa.getUpdatedAt();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.cnpj = empresa.getCnpj();
    }

    public EmpresaDTO fromEntity(Empresa empresa) {
        this.id = empresa.getId();
        this.createdAt = empresa.getCreatedAt();
        this.updatedAt = empresa.getUpdatedAt();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.cnpj = empresa.getCnpj();

        return this;
    }

    public Empresa toEntity() {
        Empresa empresa = new Empresa();
        empresa.setId(this.getId());
        empresa.setCreatedAt(this.getCreatedAt());
        empresa.setUpdatedAt(this.getUpdatedAt());
        empresa.setRazaoSocial(this.getRazaoSocial());
        empresa.setNomeFantasia(this.getNomeFantasia());
        empresa.setCnpj(this.getCnpj().replaceAll("\\W", ""));

        return empresa;
    }

}
