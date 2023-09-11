package p.lodz.it.spjava.e13.ges.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @Version
    @Getter
    private long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, name = "creation_date")
    @Getter
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modification_date")
    @Getter
    private Date lastModificationDate;

    public AbstractEntity(UUID id) {
        this.id = id;
    }
    public AbstractEntity() {};

//    public UUID getId() {
//        return id;
//    }
//
//    public long getVersion() { return version; }

    @PrePersist
    public void onCreate() {
        creationDate = lastModificationDate = new Date();

    }

    @PreUpdate
    private void onUpdate() {
        lastModificationDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", version=" + version +
                ", creationDate=" + creationDate +
                ", lastModificationDate=" + lastModificationDate +
                '}';
    }
}