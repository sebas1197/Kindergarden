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

import ec.edu.espe.distribuidas.guarderia.dao.HorarioRepository;
import ec.edu.espe.distribuidas.guarderia.model.Horario;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class HorarioService {

    private final HorarioRepository horarioRepo;

    public HorarioService(HorarioRepository horarioRepo) {

        this.horarioRepo = horarioRepo;

    }

    public List<Horario> listAll() {

        return this.horarioRepo.findAll();

    }

    public Horario obtainByCodigo(Integer codigo) {

        Optional<Horario> horarioCodigo = this.horarioRepo.findById(codigo);

        if (horarioCodigo.isPresent()) {
            return horarioCodigo.get();
        } else {
            throw new RuntimeException("Not found");
        }

    }

    @Transactional
    public void createHorario(Horario horario) {

        if (horarioRepo.existsById(horario.getCodigoHorario())) {

            throw new IllegalArgumentException("Horario ya existe");

        } else {
            horarioRepo.save(horario);

        }
    }

    @Transactional
    public void modifyHorario(Horario horario) {

        if (this.horarioRepo.existsById(horario.getCodigoHorario())) {

            this.horarioRepo.save(horario);

        } else {

            throw new IllegalArgumentException("Horario not found.");
        }
    }

    @Transactional
    public void deleteHorario(Integer codigo) {

        if (this.horarioRepo.existsById(codigo)) {

            this.horarioRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("Horario not found.");

        }
    }

}
