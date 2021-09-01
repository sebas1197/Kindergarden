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
@Table(name = "grupo_nivel")
public class GrupoNivel implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_nivel", nullable = false)
    private Integer codigoGrupoNivel;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "rango_edad", nullable = false, length = 3)
    private String rangoEdad;

    @Column(name = "detalle", nullable = false, length = 200)
    private String detalle;

    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    @ManyToOne
    private Profesor profesor;

    public GrupoNivel() {
    }

    public GrupoNivel(Integer codigoGrupoNivel) {
        this.codigoGrupoNivel = codigoGrupoNivel;
    }

    public Integer getCodigoGrupoNivel() {
        return codigoGrupoNivel;
    }

    public void setCodigoGrupoNivel(Integer codigoGrupoNivel) {
        this.codigoGrupoNivel = codigoGrupoNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(String rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
        hash += (codigoGrupoNivel != null ? codigoGrupoNivel.hashCode() : 0);
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
        final GrupoNivel other = (GrupoNivel) obj;
        if (!Objects.equals(this.codigoGrupoNivel, other.codigoGrupoNivel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.GrupoNivel[ "
                + "idGrupoNivel=" + codigoGrupoNivel + " ]";
    }

}
