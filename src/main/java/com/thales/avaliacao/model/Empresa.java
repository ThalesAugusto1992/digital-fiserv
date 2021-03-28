package com.thales.avaliacao.model;

import com.thales.avaliacao.core.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Empresa extends AbstractEntity {

    public String nomeFantasia;

    public String razaoSocial;

    @Column(unique = true)
    public String cnpj;


    public Boolean isValidCNPJ(){
        return this.cnpj.length() == 14;
    }
}
