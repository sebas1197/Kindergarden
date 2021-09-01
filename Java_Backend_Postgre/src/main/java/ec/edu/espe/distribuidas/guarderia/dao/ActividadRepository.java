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
package ec.edu.espe.distribuidas.guarderia.dao;

import ec.edu.espe.distribuidas.guarderia.model.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Windows Boo
 */
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    List<Actividad> findByNombreStartingWithOrderByNombre(String nombre);

    Boolean existsByNombre(String nombre);
}
