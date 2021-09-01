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
@Table(name = "representante")
public class Representante implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_representante", nullable = false)
    private Integer codigoRepresentante;

    @Column(name = "tipo_identificacion", nullable = false, length = 3)
    private String tipoIdentificacion;

    @Column(name = "identificacion", nullable = false, length = 10)
    private String identificacion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "relacion", nullable = false, length = 10)
    private String relacion;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 150)
    private String correo;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @Column(name = "ocupacion", nullable = false, length = 150)
    private String ocupacion;

    public Representante() {
    }

    public Representante(Integer codigoRepresentante) {
        this.codigoRepresentante = codigoRepresentante;
    }

    public Integer getCodigoRepresentante() {
        return codigoRepresentante;
    }

    public void setCodigoRepresentante(Integer codigoRepresentante) {
        this.codigoRepresentante = codigoRepresentante;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRepresentante != null
                ? codigoRepresentante.hashCode() : 0);
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
        final Representante other = (Representante) obj;
        if (!Objects.equals(
                this.codigoRepresentante, other.codigoRepresentante)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Representante[ "
                + "idRepresentante=" + codigoRepresentante + " ]";
    }

}
