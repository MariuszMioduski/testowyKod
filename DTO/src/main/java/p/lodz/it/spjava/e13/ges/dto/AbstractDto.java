package p.lodz.it.spjava.e13.ges.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractDto implements Serializable {

    @Getter
    private UUID id;

    @Getter
    private long version;

    @Getter
    private Date creationDate;

    @Getter
    private Date lastModificationDate;


    public AbstractDto(UUID id, long version, Date creationDate, Date lastModificationDate) {
        this.id = id;
        this.version = version;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
    }

    public AbstractDto(UUID id, long version) {
        this.id = id;
        this.version = version;
    }

    public AbstractDto() {}


//    public UUID getId() {
//        return id;
//    }
//
//    public long getVersion() {
//        return version;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractDto that = (AbstractDto) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "AbstractDTO{" +
                "id=" + id +
                "version=" + version +
                "creationDate=" + creationDate +
                "lastModificationDate" + lastModificationDate+
                '}';
    }
}

