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
package ec.edu.espe.distribuidas.guarderia.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Windows Boo
 */
@Entity
@Table(name = "planificacion")
public class Planificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planificacion", nullable = false)
    private Integer codigoPlanificacion;

    @Column(name = "lugar", nullable = false, length = 100)
    private String lugar;

    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    @ManyToOne
    private Actividad actividad;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    @ManyToOne
    private Profesor profesor;

    public Planificacion() {
    }

    public Planificacion(Integer codigoPlanificacion) {
        this.codigoPlanificacion = codigoPlanificacion;
    }

    public Integer getCodigoPlanificacion() {
        return codigoPlanificacion;
    }

    public void setCodigoPlanificacion(Integer codigoPlanificacion) {
        this.codigoPlanificacion = codigoPlanificacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPlanificacion != null
                ? codigoPlanificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planificacion other = (Planificacion) obj;
        if (!Objects.equals(
                this.codigoPlanificacion, other.codigoPlanificacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Planificacion[ "
                + "idPlanificacion=" + codigoPlanificacion + " ]";
    }

}
