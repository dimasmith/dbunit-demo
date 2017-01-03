package net.anatolich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "limits")
public class Limit {

    @Id
    private String id;
    private String entityType;
    @Column(name = "entity_ref")
    private String entityReference;
    private String currency;

    public Limit() {
    }

    public Limit(String id, String entityType, String entityReference, String currency) {
        this.id = id;
        this.entityType = entityType;
        this.entityReference = entityReference;
        this.currency = currency;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Limit{");
        sb.append("id='").append(id).append('\'');
        sb.append(", entityType='").append(entityType).append('\'');
        sb.append(", entityReference='").append(entityReference).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
