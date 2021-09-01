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
@Table(name = "comedor")
public class Comedor implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comedor", nullable = false)
    private Integer codigoComedor;

    @Column(name = "nombre_comida", nullable = false, length = 150)
    private String nombreComida;

    @JoinColumn(name = "id_menor", referencedColumnName = "id_menor")
    @ManyToOne
    private Menor menor;

    public Comedor() {
    }

    public Comedor(Integer codigoComedor) {
        this.codigoComedor = codigoComedor;
    }

    public Integer getCodigoComedor() {
        return codigoComedor;
    }

    public void setCodigoComedor(Integer codigoComedor) {
        this.codigoComedor = codigoComedor;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public Menor getMenor() {
        return menor;
    }

    public void setMenor(Menor menor) {
        this.menor = menor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoComedor != null ? codigoComedor.hashCode() : 0);
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
        final Comedor other = (Comedor) obj;
        if (!Objects.equals(this.codigoComedor, other.codigoComedor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.distribuidas.guarderia.modelTemp.Comedor["
                + " idComedor=" + codigoComedor + " ]";
    }

}
