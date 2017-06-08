package model;

import java.io.Serializable;
import java.util.Date;

public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private Date date;
    private Integer medicalRecordId;
    private Integer doctorId;
    private boolean isFinal;

    public Diagnosis() {
    }

    public Diagnosis(Integer id) {
        this.id = id;
    }

    public Diagnosis(String name, String description, Date date, Integer medicalRecordId, Integer doctorId,
            boolean isFinal) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.medicalRecordId = medicalRecordId;
        this.doctorId = doctorId;
        this.isFinal = isFinal;
    }

    public Diagnosis(Integer id, String name, String description, Date date, Integer doctor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.doctorId = doctor;
    }

    public Diagnosis(Integer id, String name, String description, Date date, Integer medicalRecordId, Integer doctorId,
            boolean isFinal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.medicalRecordId = medicalRecordId;
        this.doctorId = doctorId;
        this.isFinal = isFinal;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Integer medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((doctorId == null) ? 0 : doctorId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isFinal ? 1231 : 1237);
        result = prime * result + ((medicalRecordId == null) ? 0 : medicalRecordId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Diagnosis other = (Diagnosis) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (doctorId == null) {
            if (other.doctorId != null) {
                return false;
            }
        } else if (!doctorId.equals(other.doctorId)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isFinal != other.isFinal) {
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
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Diagnosis [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", date=");
        builder.append(date);
        builder.append(", medicalRecordId=");
        builder.append(medicalRecordId);
        builder.append(", doctorId=");
        builder.append(doctorId);
        builder.append(", isFinal=");
        builder.append(isFinal);
        builder.append("]");
        return builder.toString();
    }

}