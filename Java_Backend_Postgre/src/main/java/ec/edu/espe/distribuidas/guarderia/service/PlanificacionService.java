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

import ec.edu.espe.distribuidas.guarderia.dao.PlanificacionRepository;
import ec.edu.espe.distribuidas.guarderia.model.Planificacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class PlanificacionService {

    private final PlanificacionRepository planificacionRepo;

    public PlanificacionService(PlanificacionRepository planificacionRepo) {

        this.planificacionRepo = planificacionRepo;

    }

    public List<Planificacion> listAll() {

        return this.planificacionRepo.findAll();
    }

    public Optional<Planificacion> obtainByCodigo(Integer codigo) {

        Optional<Planificacion> planificacionCodigo = this.planificacionRepo
                .findById(codigo);
        if (planificacionCodigo.isPresent()) {
            return planificacionCodigo;
        } else {
            throw new RuntimeException("Not found");
        }

    }

    @Transactional

    public void createPlanificacion(Planificacion planificacion) {
        planificacionRepo.save(planificacion);
    }

    @Transactional
    public void modifyPlanificacion(Planificacion planificacion) {

        if (this.planificacionRepo.existsById(planificacion
                .getCodigoPlanificacion())) {

            this.planificacionRepo.save(planificacion);

        } else {

            throw new IllegalArgumentException("Planificaion not found.");
        }
    }

    @Transactional
    public void deletePlanificacion(Integer codigo) {
        if (this.planificacionRepo.existsById(codigo)) {

            this.planificacionRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("Planificacion not found.");
        }
    }
}
