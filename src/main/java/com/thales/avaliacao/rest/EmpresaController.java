package com.thales.avaliacao.rest;

import com.thales.avaliacao.exceptions.CNPJNotFoundException;
import com.thales.avaliacao.exceptions.EmpresaNotFoundException;
import com.thales.avaliacao.exceptions.EntityIdIsNotValidException;
import com.thales.avaliacao.exceptions.InvalidCNPJException;
import com.thales.avaliacao.model.EmpresaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = EmpresaController._PATH)
public class EmpresaController {

    public static final String _PATH = "empresas";

    public final EmpresaService empresaService;

    @Autowired
    EmpresaController(EmpresaServiceImpl empresaService) {
        this.empresaService = empresaService;
    }

    @ApiOperation(value = "Salva uma nova Empresa no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmpresaDTO.class),
            @ApiResponse(code = 400, message = InvalidCNPJException._DEFAULT_MESSAGE, response = String.class),
            @ApiResponse(code = 400, message = "Empresa já Cadastrada", response = String.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.empresaService.createEmpresa(empresaDTO));
        } catch (InvalidCNPJException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidCNPJException._DEFAULT_MESSAGE);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empresa já cadastrada");
        }
    }

    @ApiOperation(value = "Realiza a busca de uma empresa por seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmpresaDTO.class),
            @ApiResponse(code = 404, message = EmpresaNotFoundException._DEFAULT_MESSAGE, response = String.class)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEmpresaById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.getEmpresaById(id));
        } catch (EmpresaNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EmpresaNotFoundException._DEFAULT_MESSAGE);
        }
    }

    @ApiOperation(value = "Realiza a a busca de uma empresa por seu CNPJ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmpresaDTO.class),
            @ApiResponse(code = 404, message = EmpresaNotFoundException._DEFAULT_MESSAGE, response = String.class)
    })
    @GetMapping(value = "cnpj/{cnpj}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEmpresaByCNPJ(@PathVariable(value = "cnpj") String cnpj) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.getEmpresaByCNPJ(cnpj));
        } catch (CNPJNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CNPJNotFoundException._DEFAULT_MESSAGE);
        }
    }

    @ApiOperation(value = "Deleta uma empresa por seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = EmpresaNotFoundException._DEFAULT_MESSAGE, response = String.class),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteEmpresaById(@PathVariable(value = "id") Long id) {
        try {
            this.empresaService.deleteEmpresaById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EmpresaNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EmpresaNotFoundException._DEFAULT_MESSAGE);
        }
    }

    @ApiOperation(value = "Altera uma empresa já cadastrada no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmpresaDTO.class),
            @ApiResponse(code = 400, message = InvalidCNPJException._DEFAULT_MESSAGE, response = String.class),
            @ApiResponse(code = 400, message = "Empresa já Cadastrada", response = String.class),
            @ApiResponse(code = 404, message = EmpresaNotFoundException._DEFAULT_MESSAGE, response = String.class),
            @ApiResponse(code = 409, message = EntityIdIsNotValidException._DEFAULT_MESSAGE, response = String.class)
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable(value = "id") Long id, @RequestBody EmpresaDTO empresaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.updateEmpresa(id, empresaDTO));
        } catch (InvalidCNPJException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidCNPJException._DEFAULT_MESSAGE);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empresa já cadastrada");
        } catch (EmpresaNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EmpresaNotFoundException._DEFAULT_MESSAGE);
        } catch (EntityIdIsNotValidException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(EntityIdIsNotValidException._DEFAULT_MESSAGE);
        }
    }

    @ApiOperation(value = "Realiza a busca de Empresas com os parametros pre-estabelecidos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Page.class),
    })
    @GetMapping
    public ResponseEntity<Object> getEmpresaByParams(@RequestParam(required = false, defaultValue = "") String cnpj,
                                                     @RequestParam(required = false, defaultValue = "") String razaoSocial,
                                                     @RequestParam(required = false, defaultValue = "") String nomeFantasia,
                                                     @RequestParam Integer page,
                                                     @RequestParam Integer pageQtd) {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.getEmpresasByParams(cnpj, razaoSocial, nomeFantasia, PageRequest.of(page, pageQtd)));
    }

}
