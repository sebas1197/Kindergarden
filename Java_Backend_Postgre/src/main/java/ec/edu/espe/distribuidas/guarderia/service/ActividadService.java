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

import ec.edu.espe.distribuidas.guarderia.dao.ActividadRepository;
import ec.edu.espe.distribuidas.guarderia.model.Actividad;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class ActividadService {

    private final ActividadRepository actividadRepo;

    public ActividadService(ActividadRepository actividadRepo) {

        this.actividadRepo = actividadRepo;

    }

    public List<Actividad> listAll() {

        return this.actividadRepo.findAll();

    }

    public List<Actividad> findByName(String nombre) {

        return actividadRepo.findByNombreStartingWithOrderByNombre(nombre);

    }

    @Transactional
    public void createActividad(Actividad actividad) {

        if (actividadRepo.existsByNombre(actividad.getNombre())) {

            throw new IllegalArgumentException("La actividad ya existe");

        } else {

            actividadRepo.save(actividad);

        }
    }

    @Transactional
    public void modifyActividad(Actividad actividad) {

        if (this.actividadRepo.existsById(actividad.getCodigoActividad())) {

            this.actividadRepo.save(actividad);

        } else {

            throw new IllegalArgumentException("Actividad not found.");

        }
    }

    @Transactional
    public void deleteActividad(Integer codigo) {

        if (this.actividadRepo.existsById(codigo)) {

            this.actividadRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("Actividad not found.");
        }
    }

}
