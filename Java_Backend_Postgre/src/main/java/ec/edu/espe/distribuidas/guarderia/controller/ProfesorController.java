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

import ec.edu.espe.distribuidas.guarderia.model.Profesor;
import ec.edu.espe.distribuidas.guarderia.service.ProfesorService;
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
@RequestMapping("/v1/profesor")
@Slf4j
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {

        this.service = service;

    }

    @GetMapping
    @ApiOperation(value = "Lista profesores", notes = "Retorna todos "
            + "los profesores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas los"
                + " profesores registrados en la base de datos")
    })
    public ResponseEntity listarTodos() {

        List<Profesor> profesor = this.service.listAll();
        return ResponseEntity.ok(profesor);

    }

    @GetMapping(value = "{tipoIdentificacion}/{identificacion}")

    @ApiOperation(value = "Busca profesor", notes = "Obtiene "
            + "profesor por tipo de identificacion y número"
            + " de identificación"
            + "los profesores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un profesor"
                + " deacuerdo al codigo y el tipo de identificación"),
        @ApiResponse(code = 404, message = "No existe registros para el codigo"
                + " envidado")
    })

    public ResponseEntity obtenerProfesor(
            @PathVariable("tipoIdentificacion") String tipoIdentificacion,
            @PathVariable("identificacion") String identificacion) {

        try {

            Profesor profesor = this.service
                    .obtainByTipoIdentificacionAndIdentificacion(
                            tipoIdentificacion, identificacion);
            return ResponseEntity.ok(profesor);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "identificacion/{identificacion}")
    @ApiOperation(value = "Obtiene un profesor", notes = "Obtiene un "
            + "profesor de acuerdo a su identificación")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un profesor "
                + "deacuerdo a la identificación"),
        @ApiResponse(code = 404, message = "No existe un profesor para la "
                + "identificación enviada")
    })
    public ResponseEntity obtenerProfesorIdentificacion(
            @PathVariable("identificacion") String identificacion) {
        try {
            Profesor profesor = this.service.obtainByIdentificacion(identificacion);
            return ResponseEntity.ok(profesor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "codigo/{codigo}")
    @ApiOperation(value = "Obtiene un profesor", notes = "Obtiene un "
            + "profesor de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un profesor "
                + "deacuerdo al codigo"),
        @ApiResponse(code = 404, message = "No existe un profesor para el "
                + "codigo envidado")
    })
    public ResponseEntity obtenerRepresentanteCodigo(
            @PathVariable("codigo") Integer codigo) {

        try {

            Optional<Profesor> profesor = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(profesor);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    @ApiOperation(value = "Registrar una nuevo profesor", notes = "Agrega"
            + "un nuevo profesor a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Profesor registrado"),
        @ApiResponse(code = 400, message = "Profesor no pudo ser "
                + "registrado")

    })
    public ResponseEntity crear(@RequestBody Profesor profesor) {
        try {

            log.info("Va a crear el profesor con la siguiente informacion: {}",
                    profesor);
            this.service.createProfesor(profesor);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear el profesor.{} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar profesor", notes = "El profesor"
            + " a ser registrado debe estar registrado en la base de datos"
            + " caso contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Profesor modificado "
                + "exitosamente"),
        @ApiResponse(code = 400, message = "Profesor no pudo "
                + "ser modificado")

    })
    public ResponseEntity modificar(@RequestBody Profesor profesor) {

        try {

            this.service.modifyProfesor(profesor);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar el profesor. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar profesor", notes = "La eliminación"
            + "del profesor se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Profesor eliminado"),
        @ApiResponse(code = 400, message = "No existe Representante con el"
                + " codigo ingresado")
    })

    public ResponseEntity eliminar(@PathVariable("codigo") Integer codigo) {
        try {

            this.service.deleteProfesor(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar el profesor. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
