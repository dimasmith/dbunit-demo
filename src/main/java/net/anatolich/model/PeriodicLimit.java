package net.anatolich.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "periodic_limits")
public class PeriodicLimit {

    @Id
    private String id;
    private String period;
    private String amount;
    @ManyToOne(targetEntity = Limit.class)
    private Limit limit;

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

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeriodicLimit{");
        sb.append("id='").append(id).append('\'');
        sb.append(", period='").append(period).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
