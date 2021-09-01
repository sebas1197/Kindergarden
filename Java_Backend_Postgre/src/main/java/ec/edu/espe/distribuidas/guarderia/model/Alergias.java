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
@Table(name = "alergias")
public class Alergias implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alergias", nullable = false)
    private Integer codigoAlergias;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "detalle", nullable = false, length = 200)
    private String detalle;

    public Alergias() {
    }

    public Alergias(Integer codigoAlergias) {
        this.codigoAlergias = codigoAlergias;
    }

    public Integer getCodigoAlergias() {
        return codigoAlergias;
    }

    public void setCodigoAlergias(Integer codigoAlergias) {
        this.codigoAlergias = codigoAlergias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAlergias != null ? codigoAlergias.hashCode() : 0);
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
        final Alergias other = (Alergias) obj;
        if (!Objects.equals(this.codigoAlergias, other.codigoAlergias)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Alergias[ "
                + "idAlergias=" + codigoAlergias + " ]";
    }

}
