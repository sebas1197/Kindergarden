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

import ec.edu.espe.distribuidas.guarderia.dao.RepresentanteRepository;
import ec.edu.espe.distribuidas.guarderia.model.Representante;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class RepresentanteService {

    private final RepresentanteRepository representanteRepo;

    public RepresentanteService(RepresentanteRepository representanteRepo) {

        this.representanteRepo = representanteRepo;

    }

    public List<Representante> listAll() {

        return this.representanteRepo.findAll();

    }

    public Representante obtainByTipoIdentificacionAndIdentificacion(
            String tipoIdentificacion, String identificacion) {

        Representante representanteFind = this.representanteRepo
                .findByTipoIdentificacionAndIdentificacion(
                        tipoIdentificacion, identificacion);
        if (representanteFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return representanteFind;

    }

    public Representante obtainByIdentificacion(String identificacion) {
        Representante representanteFind = this.representanteRepo
                .findByIdentificacion(identificacion);
        if (representanteFind == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return representanteFind;
    }

    public Optional<Representante> obtainByCodigo(Integer codigo) {

        Optional<Representante> representanteCodigo = this.representanteRepo
                .findById(codigo);
        if (representanteCodigo.isPresent()) {
            return representanteCodigo;
        } else {
            throw new RuntimeException("Not found");
        }
    }

    @Transactional
    public void createRepresentante(Representante representante) {

        if (representanteRepo.existsByTipoIdentificacionAndIdentificacion(
                representante.getTipoIdentificacion(),
                representante.getIdentificacion())) {

            throw new IllegalArgumentException("El representante ya existe");

        } else {

            representanteRepo.save(representante);

        }
    }

    @Transactional
    public void modifyRepresentante(Representante representante) {
        if (this.representanteRepo.existsById(
                representante.getCodigoRepresentante())) {

            this.representanteRepo.save(representante);

        } else {

            throw new IllegalArgumentException("User not found.");

        }
    }

    @Transactional
    public void deleteRepresentante(Integer codigo) {
        if (this.representanteRepo.existsById(codigo)) {

            this.representanteRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("User not found.");
        }
    }

}
