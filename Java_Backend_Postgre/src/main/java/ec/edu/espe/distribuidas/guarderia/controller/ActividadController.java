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

import ec.edu.espe.distribuidas.guarderia.model.Actividad;
import ec.edu.espe.distribuidas.guarderia.service.ActividadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
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
@RequestMapping("/v1/actividad")
@Slf4j
public class ActividadController {

    private final ActividadService service;

    public ActividadController(ActividadService service) {

        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Listar actividades", notes = "Retorna todas "
            + "las actividades disponibles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todos las actividades"
                + " disponibles")
    })
    public ResponseEntity listarTodos() {

        List<Actividad> profesor = this.service.listAll();
        return ResponseEntity.ok(profesor);

    }

    @GetMapping(value = "{nombre}")
    @ApiOperation(value = "Obtiene una actividad", notes = "Retorna una "
            + "actividad de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actividad encontrado"),
        @ApiResponse(code = 404, message = "No existe actividad para "
                + "el codigo envidado")})
    public ResponseEntity obtenerActividad(
            @PathVariable("nombre") String nombre) {

        try {

            List<Actividad> actividad = this.service.findByName(nombre);
            return ResponseEntity.ok(actividad);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Crear Actididad", notes = "Registrar una nueva "
            + "actividad en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actividad registrada"),
        @ApiResponse(code = 400, message = "Actividad no pudo ser registrada")

    })
    public ResponseEntity crear(@RequestBody Actividad actividad) {
        try {

            log.info("Va a crear la actividad con la siguiente informacion:"
                    + " {}", actividad);
            this.service.createActividad(actividad);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear la actividad. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar actividad", notes = "La Actividad debe "
            + "estar registrada en la base de datos caso"
            + " contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actividad modificada exitosamente"),
        @ApiResponse(code = 400, message = "Actividad no pudo ser modificada")

    })
    public ResponseEntity modificar(@RequestBody Actividad actividad) {
        try {

            this.service.modifyActividad(actividad);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar la actividad. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar una Actividad", notes = "La eliminación"
            + "de la activdad se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actividad eliminada"),
        @ApiResponse(code = 400, message = "No existe actividad con el"
                + " codigo ingresado")

    })
    public ResponseEntity eliminar(@PathVariable("codigo") Integer codigo) {
        try {

            this.service.deleteActividad(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar la actividad. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
