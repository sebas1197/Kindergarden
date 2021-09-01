/*
 * Copyright (c) 2021 Windows Boo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Windows Boo - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.guarderia.controller;

import ec.edu.espe.distribuidas.guarderia.model.Representante;
import ec.edu.espe.distribuidas.guarderia.service.RepresentanteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Windows Boo
 */
@RestController
@RequestMapping("/v1/representante")
@Slf4j
public class RepresentanteController {

    private final RepresentanteService service;

    public RepresentanteController(RepresentanteService service) {

        this.service = service;

    }

    @GetMapping
    @ApiOperation(value = "Listar Representantes", notes = "Retorna todos "
            + "los representantes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas los"
                + " representantes registrados en la base de datos")
    })
    public ResponseEntity listarTodos() {
        List<Representante> representante = this.service.listAll();
        return ResponseEntity.ok(representante);
    }

    @GetMapping(value = "{tipoIdentificacion}/{identificacion}")
    @ApiOperation(value = "Busca representante", notes = "Obtiene "
            + "representante por tipo de identificacion y número"
            + " de identificación"
            + "los representantes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un representante"
                + " deacuerdo al codigo y el tipo de identificación"),
        @ApiResponse(code = 404, message = "No existe registros para el codigo"
                + " envidado")
    })
    public ResponseEntity
            obtenerRepresentante(
                    @PathVariable(
                            "tipoIdentificacion") String tipoIdentificacion,
                    @PathVariable("identificacion") String identificacion) {
        try {

            Representante representante = this.service
                    .obtainByTipoIdentificacionAndIdentificacion(
                            tipoIdentificacion, identificacion);
            return ResponseEntity.ok(representante);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping(value = "identificacion/{identificacion}")
    @ApiOperation(value = "Obtiene un representante", notes = "Retorna un "
            + "representante" + " de acuerdo a su identificación")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Representante encontrado"),
        @ApiResponse(code = 404, message = "No existe representante para "
                + "la identificación enviada")})
    public ResponseEntity obtenerRepresentanteIdentificacion(
            @PathVariable("identificacion") String identificacion) {
        try {
            Representante representante = this.service
                    .obtainByIdentificacion(identificacion);
            return ResponseEntity.ok(representante);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "codigo/{codigo}")
    @ApiOperation(value = "Obtiene un representante", notes = "Retorna un "
            + "representante" + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Representante encontrado"),
        @ApiResponse(code = 404, message = "No existe representante para "
                + "el codigo envidado")})
    public ResponseEntity obtenerRepresentanteCodigo(
            @PathVariable("codigo") Integer codigo) {

        try {

            Optional<Representante> representante = this.service
                    .obtainByCodigo(codigo);
            return ResponseEntity.ok(representante);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Registrar una nuevo representante", notes = "Agrega"
            + "un nuevo representante a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Representante registrado"),
        @ApiResponse(code = 400, message = "Representante no pudo ser "
                + "registrado")

    })
    public ResponseEntity crearRepresentante(
            @RequestBody Representante representante) {

        try {

            log.info("Va a crear el representante con la siguiente informacion:"
                    + " {}", representante);
            this.service.createRepresentante(representante);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear el representante. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar representante", notes = "El representante"
            + " a ser registrado debe estar registrada en la base de datos"
            + " caso contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Representante modificado "
                + "exitosamente"),
        @ApiResponse(code = 400, message = "Representante no pudo "
                + "ser modificado")

    })
    public ResponseEntity modificarRepresentante(
            @RequestBody Representante representante) {
        try {

            this.service.modifyRepresentante(representante);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar el representante. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar representante", notes = "La eliminación"
            + "del representante se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Representante eliminado"),
        @ApiResponse(code = 400, message = "No existe Representante con el"
                + " codigo ingresado")
    })
    public ResponseEntity eliminarRepresentante(
            @PathVariable("codigo") Integer codigo) {

        try {

            this.service.deleteRepresentante(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar el representante. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
