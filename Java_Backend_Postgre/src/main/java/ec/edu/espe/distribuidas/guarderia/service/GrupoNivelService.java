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

import ec.edu.espe.distribuidas.guarderia.dao.GrupoNivelRepository;
import ec.edu.espe.distribuidas.guarderia.model.GrupoNivel;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class GrupoNivelService {

    private final GrupoNivelRepository grupoNivelRepo;

    public GrupoNivelService(GrupoNivelRepository grupoNivelRepo) {

        this.grupoNivelRepo = grupoNivelRepo;

    }

    public List<GrupoNivel> listAll() {
        return this.grupoNivelRepo.findAll();
    }

    public Optional<GrupoNivel> obtainByCodigo(Integer codigo) {

        Optional<GrupoNivel> nivelCodigo = this.grupoNivelRepo.findById(codigo);

        if (nivelCodigo.isPresent()) {

            return nivelCodigo;

        } else {

            throw new RuntimeException("Not found");

        }
    }

    @Transactional
    public void createNivel(GrupoNivel nivel) {
        grupoNivelRepo.save(nivel);
    }

    @Transactional
    public void modifyNivel(GrupoNivel nivel) {

        if (this.grupoNivelRepo.existsById(nivel.getCodigoGrupoNivel())) {

            this.grupoNivelRepo.save(nivel);

        } else {

            throw new IllegalArgumentException("Nivel not found.");

        }
    }

    @Transactional
    public void deleteNivel(Integer codigo) {

        if (this.grupoNivelRepo.existsById(codigo)) {

            this.grupoNivelRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("Nivel not found.");

        }
    }
}
