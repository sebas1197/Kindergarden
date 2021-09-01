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

import ec.edu.espe.distribuidas.guarderia.model.Planificacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Windows Boo
 */
public interface PlanificacionRepository extends
        JpaRepository<Planificacion, Integer> {

}
