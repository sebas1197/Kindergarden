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

import ec.edu.espe.distribuidas.guarderia.model.Planificacion;
import ec.edu.espe.distribuidas.guarderia.service.PlanificacionService;
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
@RequestMapping("/v1/planificacion")
@Slf4j
public class PlanificacionController {

    private final PlanificacionService service;

    public PlanificacionController(PlanificacionService service) {

        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Listar planificaciones", notes = "Retorna todas "
            + "las planificaciones disponibles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas las planificaciones"
                + " disponibles")
    })
    public ResponseEntity listarTodos() {

        List<Planificacion> planificacion = this.service.listAll();
        return ResponseEntity.ok(planificacion);

    }

    @GetMapping(value = "{codigo}")
    @ApiOperation(value = "Obtiene una planificación", notes = "Retorna una "
            + "planificación de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Planificación encontrado"),
        @ApiResponse(code = 404, message = "No existe planificación para "
                + "el codigo envidado")})

    public ResponseEntity obtenerPorCodigo(
            @PathVariable("codigo") Integer codigo) {
        try {

            Optional<Planificacion> planificacion
                    = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(planificacion);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    @ApiOperation(value = "Crear planificación", notes = "Registrar una nueva"
            + " planificacion en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Planificación registrada"),
        @ApiResponse(code = 400, message = "Planificación no pudo ser registrado")

    })
    public ResponseEntity crear(@RequestBody Planificacion planificacion) {
        try {

            log.info("Va a crear la planificcion con la siguiente informacion: "
                    + "{}", planificacion);
            this.service.createPlanificacion(planificacion);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear la planificacion. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar planificación", notes = "La planificación"
            + " a ser registrado debe estar registrado en la base de datos caso"
            + " contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Planificación modificada exitosamente"),
        @ApiResponse(code = 400, message = "Planificación no pudo ser modificado")

    })
    public ResponseEntity modificar(@RequestBody Planificacion planificacion) {
        try {

            this.service.modifyPlanificacion(planificacion);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar la planificacion. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminacion de una planificación", notes = "La "
            + "eliminación de la planificación se la realiza de acuerdo "
            + "a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Planificación eliminado"),
        @ApiResponse(code = 400, message = "No existe planificaión con el"
                + " codigo ingresado")

    })
    public ResponseEntity eliminar(@PathVariable("codigo") Integer codigo) {
        try {

            this.service.deletePlanificacion(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar la planificacion. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
