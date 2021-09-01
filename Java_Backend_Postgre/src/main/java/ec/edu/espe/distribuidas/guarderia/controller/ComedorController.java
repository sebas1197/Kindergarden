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

import ec.edu.espe.distribuidas.guarderia.model.Comedor;
import ec.edu.espe.distribuidas.guarderia.service.ComedorService;
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
@RequestMapping("/v1/comedor")
@Slf4j
public class ComedorController {

    private final ComedorService service;

    public ComedorController(ComedorService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Listar Comedores", notes = "Retorna todos "
            + "los comedores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas los"
                + " comedores registrados en la base de datos")
    })
    public ResponseEntity listarTodos() {
        List<Comedor> comedor = this.service.listAll();
        return ResponseEntity.ok(comedor);
    }

    @GetMapping(value = "{codigo}")
    @ApiOperation(value = "Obtiene un comedor", notes = "Retorna un comedor"
            + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comedor encontrado"),
        @ApiResponse(code = 404, message = "No existe un comedor para "
                + "el codigo envidado")})
    public ResponseEntity obtenerRepresentanteCodigo(
            @PathVariable("codigo") Integer codigo) {
        try {
            Optional<Comedor> comedor = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(comedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Registrar una nuevo comedor", notes = "Agrega un "
            + "nuevo comedor a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comedor registrado"),
        @ApiResponse(code = 400, message = "Comedor no pudo ser registrada")

    })
    public ResponseEntity crearRepresentante(@RequestBody Comedor comedor) {
        try {
            log.info("Va a crear el comedor con la siguiente informacion:"
                    + " {}", comedor);
            this.service.createComedor(comedor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al crear el comedor. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar comedor", notes = "El comedor a ser "
            + "registrado debe estar registrado en la base de datos caso"
            + " contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comedor modificada exitosamente"),
        @ApiResponse(code = 400, message = "Comedor no pudo ser modificado")

    })
    public ResponseEntity modificarRepresentante(@RequestBody Comedor comedor) {
        try {
            this.service.modifyComedor(comedor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al modificar el comedor. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar comedor", notes = "La eliminación"
            + "del comedor se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comedor eliminado"),
        @ApiResponse(code = 400, message = "No existe comedor con el"
                + " codigo ingresado")

    })
    public ResponseEntity eliminarRepresentante(
            @PathVariable("codigo") Integer codigo) {
        try {
            this.service.deleteComedor(codigo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al eliminar el comedor. "
                    + "{} - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
