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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Windows Boo
 */
@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario", nullable = false)
    private Integer codigoHorario;

    @Column(name = "jornada", nullable = false, length = 3)
    private String jornada;

    @Column(name = "hora_entrada", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;

    @Column(name = "hora_salida", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaSalida;

    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    public Horario() {
    }

    public Horario(Integer codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public Integer getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(Integer codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoHorario != null ? codigoHorario.hashCode() : 0);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.codigoHorario, other.codigoHorario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Horario[ "
                + "idHorario=" + codigoHorario + " ]";
    }

}
