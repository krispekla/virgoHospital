package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "Drugs")
public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(columnDefinition = "Money default '0'", nullable = true)
    private BigDecimal price;

    @OneToMany(mappedBy = "drug")
    private List<DrugPrescriptions> prescriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drug)) {
            return false;
        }
        Drug other = (Drug) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<DrugPrescriptions> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<DrugPrescriptions> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.Drug[ id=" + id + " ]";
    }

}
