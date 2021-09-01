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
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Windows Boo
 */
@Entity
@Table(name = "menor")
public class Menor implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menor", nullable = false)
    private Integer codigoMenor;

    @Column(name = "tipo_identificacion", nullable = false, length = 3)
    private String tipoIdentificacion;

    @Column(name = "identificacion", nullable = false, length = 10)
    private String identificacion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "genero", nullable = false, length = 3)
    private String genero;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @JoinColumn(name = "id_alergias", referencedColumnName = "id_alergias")
    @ManyToOne
    private Alergias alergias;

    @JoinColumn(name = "id_grupo_nivel", referencedColumnName
            = "id_grupo_nivel")
    @ManyToOne
    private GrupoNivel grupoNivel;

    @JoinColumn(name = "id_horario", referencedColumnName
            = "id_horario")
    @ManyToOne
    private Horario horario;

    @JoinColumn(name = "id_representante", referencedColumnName
            = "id_representante")
    @ManyToOne
    private Representante representante;

    public Menor() {
    }

    public Menor(Integer codigoMenor) {
        this.codigoMenor = codigoMenor;
    }

    public Integer getCodigoMenor() {
        return codigoMenor;
    }

    public void setCodigoMenor(Integer codigoMenor) {
        this.codigoMenor = codigoMenor;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Alergias getAlergias() {
        return alergias;
    }

    public void setAlergias(Alergias alergias) {
        this.alergias = alergias;
    }

    public GrupoNivel getGrupoNivel() {
        return grupoNivel;
    }

    public void setGrupoNivel(GrupoNivel grupoNivel) {
        this.grupoNivel = grupoNivel;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMenor != null ? codigoMenor.hashCode() : 0);
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
        final Menor other = (Menor) obj;
        if (!Objects.equals(this.codigoMenor, other.codigoMenor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Menor[ "
                + "idMenor=" + codigoMenor + " ]";
    }

}
