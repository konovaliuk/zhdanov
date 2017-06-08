package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicalRecord implements Serializable {

    private static final long serialVersionUID = -6730639956341662341L;

    private Integer id;
    private Date opened;
    private Date closed;
    private Integer patientId;
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Diagnosis> diagnoses = new ArrayList<>();

    public MedicalRecord(Integer id, Date opened, Date closed, Integer patientId) {
        this.id = id;
        this.opened = opened;
        this.closed = closed;
        this.patientId = patientId;
    }

    public MedicalRecord(Integer id, Date opened, Integer patientId) {
        this.id = id;
        this.opened = opened;
        this.patientId = patientId;
    }

    public MedicalRecord(Date opened, int patientId) {
        this.opened = opened;
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((closed == null) ? 0 : closed.hashCode());
        result = prime * result + ((diagnoses == null) ? 0 : diagnoses.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((opened == null) ? 0 : opened.hashCode());
        result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
        result = prime * result + ((prescriptions == null) ? 0 : prescriptions.hashCode());
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
        MedicalRecord other = (MedicalRecord) obj;
        if (closed == null) {
            if (other.closed != null) {
                return false;
            }
        } else if (!closed.equals(other.closed)) {
            return false;
        }
        if (diagnoses == null) {
            if (other.diagnoses != null) {
                return false;
            }
        } else if (!diagnoses.equals(other.diagnoses)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (opened == null) {
            if (other.opened != null) {
                return false;
            }
        } else if (!opened.equals(other.opened)) {
            return false;
        }
        if (patientId == null) {
            if (other.patientId != null) {
                return false;
            }
        } else if (!patientId.equals(other.patientId)) {
            return false;
        }
        if (prescriptions == null) {
            if (other.prescriptions != null) {
                return false;
            }
        } else if (!prescriptions.equals(other.prescriptions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MedicalRecord [id=");
        builder.append(id);
        builder.append(", opened=");
        builder.append(opened);
        builder.append(", closed=");
        builder.append(closed);
        builder.append(", patientId=");
        builder.append(patientId);
        builder.append(", prescriptions=");
        builder.append(prescriptions);
        builder.append(", diagnoses=");
        builder.append(diagnoses);
        builder.append("]");
        return builder.toString();
    }

}