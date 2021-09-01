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

import ec.edu.espe.distribuidas.guarderia.model.Alergias;
import ec.edu.espe.distribuidas.guarderia.service.AlergiasService;
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
@RequestMapping("/v1/alergia")
@Slf4j
public class AlergiasController {

    private final AlergiasService service;

    public AlergiasController(AlergiasService service) {

        this.service = service;
    }

    @GetMapping(value = "{codigo}")
    @ApiOperation(value = "Obtiene una alergia", notes = "Retorna un alergia"
            + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Alergia encontrado"),
        @ApiResponse(code = 404, message = "No existe una alergia para "
                + "el codigo envidado")})
    public ResponseEntity obtenerAlergia(
            @PathVariable("codigo") Integer codigo) {
        try {

            Alergias alergia = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(alergia);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping
    @ApiOperation(value = "Listar Alergias", notes = "Retorna todas "
            + "los alergias")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas las"
                + " alergias registradas en la base de datos")
    })
    public ResponseEntity listarTodos() {

        List<Alergias> impuestos = this.service.listAll();
        return ResponseEntity.ok(impuestos);

    }

    @PostMapping
    @ApiOperation(value = "Registrar una nueva alergia", notes = "Agrega una "
            + "nueva alergia a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Alergia registrado"),
        @ApiResponse(code = 400, message = "Alergia no pudo ser registrada")

    })
    public ResponseEntity crear(@RequestBody Alergias alergia) {
        try {

            log.info("Va a crear la alergia con la siguiente informacion: {}",
                    alergia);
            this.service.create(alergia);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear la Alergia. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar Alergia", notes = "La alergia a ser "
            + "registrado debe estar registrada en la base de datos caso"
            + " contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Alergia modificada exitosamente"),
        @ApiResponse(code = 400, message = "Alergia no pudo ser modificado")

    })
    public ResponseEntity modificar(@RequestBody Alergias alergia) {
        try {

            this.service.modify(alergia);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar la alergia. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar alergia", notes = "La eliminación"
            + "de la alergia se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Alergia eliminado"),
        @ApiResponse(code = 400, message = "No existe alergia con el"
                + " codigo ingresado")

    })
    public ResponseEntity eliminar(@PathVariable("codigo") Integer codigo) {
        try {

            this.service.deleteAlergia(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar la alergia. {} "
                    + "- retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
