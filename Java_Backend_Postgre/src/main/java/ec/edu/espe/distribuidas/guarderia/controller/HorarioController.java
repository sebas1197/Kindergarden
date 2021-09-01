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

import ec.edu.espe.distribuidas.guarderia.model.Horario;
import ec.edu.espe.distribuidas.guarderia.service.HorarioService;
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
@RequestMapping("/v1/horario")
@Slf4j
public class HorarioController {

    private final HorarioService service;

    public HorarioController(HorarioService service) {

        this.service = service;

    }

    @GetMapping
    @ApiOperation(value = "Listar Horarios", notes = "Retorna todos "
            + "los horarios disponibles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todos los horarios"
                + " disponibles")
    })
    public ResponseEntity listarTodos() {

        List<Horario> horario = this.service.listAll();
        return ResponseEntity.ok(horario);

    }

    @GetMapping(value = "{codigo}")
    @ApiOperation(value = "Obtiene un horario", notes = "Retorna un Horario"
            + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Horario encontrado"),
        @ApiResponse(code = 404, message = "No existe horario para "
                + "el codigo envidado")})
    public ResponseEntity obtenerPorCodigo(
            @PathVariable("codigo") Integer codigo) {

        try {

            Horario horario = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(horario);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }
}
