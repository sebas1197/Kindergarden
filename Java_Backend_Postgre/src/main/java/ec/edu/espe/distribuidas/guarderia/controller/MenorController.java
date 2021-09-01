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

import ec.edu.espe.distribuidas.guarderia.model.Menor;
import ec.edu.espe.distribuidas.guarderia.service.MenorService;
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
@RequestMapping("/v1/menor")
@Slf4j
public class MenorController {

    private final MenorService service;

    public MenorController(MenorService service) {

        this.service = service;

    }

    @GetMapping
    @ApiOperation(value = "Listar Menores", notes = "Retorna todos "
            + "los menores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Muestra todas los"
                + " menores registrados en la base de datos")
    })
    public ResponseEntity listarTodos() {

        List<Menor> menor = this.service.listAll();
        return ResponseEntity.ok(menor);

    }

    @GetMapping(value = "{tipoIdentificacion}/{identificacion}")
    @ApiOperation(value = "Obtiene un menor", notes = "Obtiene un menor de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un menor deacuerdo al codigo"),
        @ApiResponse(code = 404, message = "No existe registros para el codigo envidado")
    })
    public ResponseEntity obtenerMenor(
            @PathVariable("tipoIdentificacion") String tipoIdentificacion,
            @PathVariable("identificacion") String identificacion) {

        try {

            Menor menor = this.service
                    .obtainByTipoIdentificacionAndIdentificacion(
                            tipoIdentificacion, identificacion);
            return ResponseEntity.ok(menor);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }
    
    @GetMapping(value = "identificacion/{identificacion}")
    @ApiOperation(value = "Obtiene un menor", notes = "Obtiene un menor de acuerdo "
            + "a su identificación")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cuando encuentra un menor deacuerdo a la identificación"),
        @ApiResponse(code = 404, message = "No existe registros para la identificación enviada")
    })
    public ResponseEntity obtenerMenorIdentificacion(
            @PathVariable("identificacion") String identificacion) {
        try {
            Menor menor = this.service.obtainByIdentificacion(identificacion);
            return ResponseEntity.ok(menor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "codigo/{codigo}")
    @ApiOperation(value = "Obtiene un menor", notes = "Retorna un "
            + "menor" + " de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Menor encontrado"),
        @ApiResponse(code = 404, message = "No existe menor para "
                + "el codigo enviado")})
    public ResponseEntity obtenerPorCodigo(
            @PathVariable("codigo") Integer codigo) {

        try {

            Optional<Menor> menor = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(menor);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    @ApiOperation(value = "Registrar una nuevo Menor", notes = "Agrega"
            + "un nuevo menor a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Menor registrado"),
        @ApiResponse(code = 400, message = "Menor no pudo ser "
                + "registrado")

    })
    public ResponseEntity crear(@RequestBody Menor menor) {
        try {

            log.info("Va a crear el menor con la siguiente informacion:"
                    + " {}", menor);
            this.service.createMenor(menor);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al crear el menor. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping
    @ApiOperation(value = "Modificar menor", notes = "El menor"
            + " a ser registrado debe estar registrado en la base de datos"
            + " caso contrario no podra ser modificado"
            + " en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Menor modificado "
                + "exitosamente"),
        @ApiResponse(code = 400, message = "Menor no pudo "
                + "ser modificado")

    })
    public ResponseEntity modificar(@RequestBody Menor menor) {

        try {

            this.service.modifyMenor(menor);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al modificar el menor. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping(value = "{codigo}")
    @ApiOperation(value = "Eliminar menor", notes = "La eliminación"
            + "del menor se la realiza de acuerdo a su código")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Menor eliminado"),
        @ApiResponse(code = 400, message = "No existe Menor con el"
                + " codigo ingresado")
    })
    public ResponseEntity eliminar(
            @PathVariable("codigo") Integer codigo) {

        try {

            this.service.deleteMenor(codigo);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            log.error("Ocurrio un error al eliminar el menor. {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
}
