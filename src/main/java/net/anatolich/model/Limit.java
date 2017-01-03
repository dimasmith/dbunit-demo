package net.anatolich.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "limits")
public class Limit {

    @Id
    private String id;
    private String entityType;
    @Column(name = "entity_ref")
    private String entityReference;
    private String currency;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "limit_id")
    private Set<PeriodicLimit> periodicLimits = new HashSet<>();

    public Limit() {
    }

    public Limit(String id, String entityType, String entityReference, String currency) {
        this.id = id;
        this.entityType = entityType;
        this.entityReference = entityReference;
        this.currency = currency;
    }

    public void addPeriodicLimit(PeriodicLimit periodicLimit) {
        periodicLimits.add(periodicLimit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set<PeriodicLimit> getPeriodicLimits() {
        return periodicLimits;
    }

    public void setPeriodicLimits(Set<PeriodicLimit> periodicLimits) {
        this.periodicLimits = periodicLimits;
    }

    @Override
    public String toString() {
        String sb = "Limit{" + "id='" + id + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityReference='" + entityReference + '\'' +
                ", currency='" + currency + '\'' +
                '}';
        return sb;
    }
}
