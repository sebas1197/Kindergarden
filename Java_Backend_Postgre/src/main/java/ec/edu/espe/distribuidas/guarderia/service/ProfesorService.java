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
package ec.edu.espe.distribuidas.guarderia.service;

import ec.edu.espe.distribuidas.guarderia.dao.ProfesorRepository;
import ec.edu.espe.distribuidas.guarderia.model.Profesor;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepo;

    public ProfesorService(ProfesorRepository profesorRepo) {

        this.profesorRepo = profesorRepo;

    }

    public List<Profesor> listAll() {

        return this.profesorRepo.findAll();

    }

    public Profesor obtainByTipoIdentificacionAndIdentificacion(
            String tipoIdentificacion, String identificacion) {

        Profesor profesorFind = this.profesorRepo
                .findByTipoIdentificacionAndIdentificacion(
                        tipoIdentificacion, identificacion);
        if (profesorFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return profesorFind;
    }

    public Profesor obtainByIdentificacion(String identificacion) {
        Profesor profesorFind = this.profesorRepo.findByIdentificacion(identificacion);
        if (profesorFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return profesorFind;
    }

    public Optional<Profesor> obtainByCodigo(Integer codigo) {

        Optional<Profesor> profesorCodigo = this.profesorRepo.findById(codigo);
        if (profesorCodigo.isPresent()) {
            return profesorCodigo;
        } else {
            throw new RuntimeException("Not found");
        }

    }

    @Transactional
    public void createProfesor(Profesor profesor) {

        if (profesorRepo.existsByTipoIdentificacionAndIdentificacion(
                profesor.getTipoIdentificacion(),
                profesor.getIdentificacion())) {

            throw new IllegalArgumentException("El profesor ya existe");

        } else {

            profesorRepo.save(profesor);

        }
    }

    @Transactional
    public void modifyProfesor(Profesor profesor) {

        if (this.profesorRepo.existsById(profesor.getCodigoProfesor())) {

            this.profesorRepo.save(profesor);

        } else {

            throw new IllegalArgumentException("User not found.");

        }
    }

    @Transactional
    public void deleteProfesor(Integer codigo) {

        if (this.profesorRepo.existsById(codigo)) {

            this.profesorRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("User not found.");

        }
    }
}
