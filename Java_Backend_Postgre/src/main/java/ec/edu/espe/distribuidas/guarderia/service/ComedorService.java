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

import ec.edu.espe.distribuidas.guarderia.dao.ComedorRepository;
import ec.edu.espe.distribuidas.guarderia.model.Comedor;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class ComedorService {

    private final ComedorRepository comedorRepo;

    public ComedorService(ComedorRepository comedorRepo) {
        this.comedorRepo = comedorRepo;
    }

    public List<Comedor> listAll() {
        return this.comedorRepo.findAll();
    }

    public Optional<Comedor> obtainByCodigo(Integer codigo) {
        Optional<Comedor> comedorCodigo = this.comedorRepo.findById(codigo);
        if (comedorCodigo.isPresent()) {

            return comedorCodigo;

        } else {
            throw new RuntimeException("Not found");
        }
    }

    @Transactional
    public void createComedor(Comedor comedor) {
        comedorRepo.save(comedor);
    }

    @Transactional
    public void modifyComedor(Comedor comedor) {
        if (this.comedorRepo.existsById(comedor.getCodigoComedor())) {
            this.comedorRepo.save(comedor);
        } else {
            throw new IllegalArgumentException("Comedor not found.");
        }
    }

    @Transactional
    public void deleteComedor(Integer codigo) {
        if (this.comedorRepo.existsById(codigo)) {
            this.comedorRepo.deleteById(codigo);
        } else {
            throw new IllegalArgumentException("Comedor not found.");
        }
    }
}
