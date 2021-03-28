package com.thales.avaliacao.data;

import com.thales.avaliacao.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findFirstByCnpj(String cnpj);

    Page<Empresa> findAllByCnpjContainsAndRazaoSocialContainsIgnoreCaseAndNomeFantasiaContainsIgnoreCase(String cnpj, String razaoSocial, String nomeFantasia, Pageable pageRequest);
}
