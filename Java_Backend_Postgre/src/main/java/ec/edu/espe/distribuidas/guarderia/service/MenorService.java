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

import ec.edu.espe.distribuidas.guarderia.dao.MenorRepository;
import ec.edu.espe.distribuidas.guarderia.model.Menor;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class MenorService {

    private final MenorRepository menorRepo;

    public MenorService(MenorRepository menorRepo) {

        this.menorRepo = menorRepo;

    }

    public List<Menor> listAll() {
        return this.menorRepo.findAll();
    }

    public Menor obtainByTipoIdentificacionAndIdentificacion(
            String tipoIdentificacion, String identificacion) {
        Menor menorFind = this.menorRepo
                .findByTipoIdentificacionAndIdentificacion(
                        tipoIdentificacion, identificacion);
        if (menorFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return menorFind;
    }

    public Menor obtainByIdentificacion(String identificacion) {
        Menor menorFind = this.menorRepo.findByIdentificacion(identificacion);
        if (menorFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return menorFind;
    }

    public Optional<Menor> obtainByCodigo(Integer codigo) {

        Optional<Menor> menorCodigo = this.menorRepo.findById(codigo);
        if (menorCodigo.isPresent()) {

            return menorCodigo;

        } else {

            throw new RuntimeException("Not found");
        }
    }

    @Transactional
    public void createMenor(Menor menor) {

        if (menorRepo.existsByTipoIdentificacionAndIdentificacion(
                menor.getTipoIdentificacion(), menor.getIdentificacion())) {

            throw new IllegalArgumentException("El menor ya existe");

        } else {

            menorRepo.save(menor);
        }
    }

    @Transactional
    public void modifyMenor(Menor menor) {

        if (this.menorRepo.existsById(menor.getCodigoMenor())) {

            this.menorRepo.save(menor);

        } else {

            throw new IllegalArgumentException("User not found.");

        }
    }

    @Transactional
    public void deleteMenor(Integer codigo) {

        if (this.menorRepo.existsById(codigo)) {

            this.menorRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("User not found.");
        }
    }
}
