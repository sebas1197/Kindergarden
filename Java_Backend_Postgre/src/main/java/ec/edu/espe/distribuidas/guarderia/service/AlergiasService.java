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

import ec.edu.espe.distribuidas.guarderia.dao.AlergiasRepository;
import ec.edu.espe.distribuidas.guarderia.model.Alergias;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class AlergiasService {

    private final AlergiasRepository alergiasRepo;

    public AlergiasService(AlergiasRepository alergiasRepo) {

        this.alergiasRepo = alergiasRepo;

    }

    public Alergias obtainByCodigo(Integer codigo) {

        Optional<Alergias> alergiaOpt = this.alergiasRepo.findById(codigo);
        if (alergiaOpt.isPresent()) {

            return alergiaOpt.get();

        } else {

            throw new RuntimeException("Not found");

        }
    }

    public List<Alergias> listByEstado(String nombre) {

        return this.alergiasRepo.findByNombreLikeOrderByNombre(nombre);

    }

    public List<Alergias> listAll() {

        return this.alergiasRepo.findAll();

    }

    public Alergias create(Alergias alergia) {

        if (alergiasRepo.existsByNombre(alergia.getNombre())) {

            throw new IllegalArgumentException("La alergia ya existe");

        } else {

            alergiasRepo.save(alergia);

        }

        return alergia;
    }

    @Transactional
    public void modify(Alergias alergia) {

        if (this.alergiasRepo.existsById(alergia.getCodigoAlergias())) {

            this.alergiasRepo.save(alergia);

        } else {

            throw new IllegalArgumentException("Alergia not found.");
        }
    }

    @Transactional
    public void deleteAlergia(Integer codigo) {

        if (this.alergiasRepo.existsById(codigo)) {

            this.alergiasRepo.deleteById(codigo);

        } else {

            throw new IllegalArgumentException("Alergia not found.");

        }
    }

}
