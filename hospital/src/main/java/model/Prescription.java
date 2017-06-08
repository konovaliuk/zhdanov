package model;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable {

    private static final long serialVersionUID = -8317576713031830314L;

    private Integer id;
    private String name;
    private String description;
    private Date performanceDate;
    private PrescriptionType type;
    private Integer medicalRecordId;
    private Integer prescriberId;
    private Integer performerId;
    private boolean isDone = false;

    public Prescription(Integer id, String name, String description, Date performanceDate, PrescriptionType type,
            Integer medicalRecordId, Integer prescriberId, Integer performerId, boolean isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.performanceDate = performanceDate;
        this.type = type;
        this.medicalRecordId = medicalRecordId;
        this.prescriberId = prescriberId;
        this.performerId = performerId;
        this.isDone = isDone;
    }

    public Prescription(String name, String description, Date performanceDate, PrescriptionType type,
            Integer medicalRecordId, Integer prescriberId) {
        this.name = name;
        this.description = description;
        this.performanceDate = performanceDate;
        this.type = type;
        this.medicalRecordId = medicalRecordId;
        this.prescriberId = prescriberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public PrescriptionType getType() {
        return type;
    }

    public void seType(PrescriptionType type) {
        this.type = type;
    }

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Integer medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Integer getPrescriberId() {
        return prescriberId;
    }

    public void setPrescriberId(Integer prescriberId) {
        this.prescriberId = prescriberId;
    }

    public Integer getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Integer performerId) {
        this.performerId = performerId;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isDone ? 1231 : 1237);
        result = prime * result + ((medicalRecordId == null) ? 0 : medicalRecordId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((performanceDate == null) ? 0 : performanceDate.hashCode());
        result = prime * result + ((performerId == null) ? 0 : performerId.hashCode());
        result = prime * result + ((prescriberId == null) ? 0 : prescriberId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
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
        Prescription other = (Prescription) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isDone != other.isDone) {
            return false;
        }
        if (medicalRecordId == null) {
            if (other.medicalRecordId != null) {
                return false;
            }
        } else if (!medicalRecordId.equals(other.medicalRecordId)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (performanceDate == null) {
            if (other.performanceDate != null) {
                return false;
            }
        } else if (!performanceDate.equals(other.performanceDate)) {
            return false;
        }
        if (performerId == null) {
            if (other.performerId != null) {
                return false;
            }
        } else if (!performerId.equals(other.performerId)) {
            return false;
        }
        if (prescriberId == null) {
            if (other.prescriberId != null) {
                return false;
            }
        } else if (!prescriberId.equals(other.prescriberId)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Prescription [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", performanceDate=");
        builder.append(performanceDate);
        builder.append(", type=");
        builder.append(type);
        builder.append(", medicalRecordId=");
        builder.append(medicalRecordId);
        builder.append(", prescriberId=");
        builder.append(prescriberId);
        builder.append(", performerId=");
        builder.append(performerId);
        builder.append(", isDone=");
        builder.append(isDone);
        builder.append("]");
        return builder.toString();
    }

}
