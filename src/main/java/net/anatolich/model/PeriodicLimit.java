package net.anatolich.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "periodic_limits")
public class PeriodicLimit {

    @Id
    private String id;
    private String period;
    private String amount;

    public PeriodicLimit() {
    }

    public PeriodicLimit(String id, String amount, String period) {
        this.id = id;
        this.amount = amount;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        String sb = "PeriodicLimit{" + "id='" + id + '\'' +
                ", period='" + period + '\'' +
                ", amount='" + amount + '\'' +
                '}';
        return sb;
    }
}
