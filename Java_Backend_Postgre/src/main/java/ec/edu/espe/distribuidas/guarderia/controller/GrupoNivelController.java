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

import ec.edu.espe.distribuidas.guarderia.model.GrupoNivel;
import ec.edu.espe.distribuidas.guarderia.service.GrupoNivelService;
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
@RequestMapping("/v1/nivel")
@Slf4j
public class GrupoNivelController {

    private final GrupoNivelService service;

    public GrupoNivelController(GrupoNivelService service) {

        this.service = service;

    }

    @GetMapping
    @ApiOperation(value = "Listar niveles", notes = "Retorna todos "
            + "los niveles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todos los"
                + " niveles registrados en la base de datos")
    })

    public ResponseEntity listarTodos() {

        List<GrupoNivel> nivel = this.service.listAll();
        return ResponseEntity.ok(nivel);

    }

    @GetMapping(value = "{codigo}")
    @ApiOperation(value = "Obtiene un nivel", notes = "Retorna un nivel"
            + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Nivel encontrado"),
        @ApiResponse(code = 404, message = "No existe un nivel para "
                + "el codigo envidado")})
    public ResponseEntity obtenerPorCodigo(
            @PathVariable("codigo") Integer codigo) {

        try {

            Optional<GrupoNivel> nivel = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(nivel);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    @ApiOperation(value = "Registrar un nuevo nivel", notes = "Agrega una "
            + "nuevo nivel a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Nivel registrado"),
        @ApiResponse(code = 400, message = "Nivel no pudo ser registrado")

    })
    public ResponseEntity crear(@RequestBody GrupoNivel nivel) {
        try {

            log.info("Va a crear el nivel con la siguiente informacion: {}",
                    nivel);
            this.service.createNivel(nivel);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear el nivel. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar nivel", notes = "El nivel a ser "
            + "registrado debe estar registrada en la base de datos caso"
            + " contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Nivel modificado exitosamente"),
        @ApiResponse(code = 400, message = "Nivel no pudo ser modificado")

    })
    public ResponseEntity modificar(@RequestBody GrupoNivel nivel) {
        try {

            this.service.modifyNivel(nivel);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar el nivel. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar nivel", notes = "La eliminación"
            + "del nivel se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Nivel eliminado"),
        @ApiResponse(code = 400, message = "No existe nivel con el"
                + " codigo ingresado")

    })
    public ResponseEntity eliminar(@PathVariable("codigo") Integer codigo) {

        try {

            this.service.deleteNivel(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar el nivel. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
