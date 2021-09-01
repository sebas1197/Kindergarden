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
import javax.persistence.Table;

/**
 *
 * @author Windows Boo
 */
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", nullable = false)
    private Integer codigoActividad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    public Actividad() {
    }

    public Actividad(Integer codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public Integer getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(Integer codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (codigoActividad != null ? codigoActividad.hashCode() : 0);
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
        final Actividad other = (Actividad) obj;
        if (!Objects.equals(this.codigoActividad, other.codigoActividad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Actividad[ "
                + "idActividad=" + codigoActividad + " ]";
    }

    

}
