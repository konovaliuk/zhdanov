package dao.sql;

public final class Query {
    public static final String CREATE_DIAGNOSIS = "INSERT INTO diagnosis (name, description, date, medical_record_id, doctor_id, is_final) VALUES (?,?,?,?,?,?)";
    public static final String FIND_DIAGNOSES_BY_MEDICAL_RECORD = "SELECT * FROM diagnosis WHERE medical_record_id = ? ORDER BY date DESC";
    public static final String CREATE_MEDICAL_RECORD = "INSERT INTO medical_record (opened, patient_id) VALUES (?,?)";
    public static final String UPDATE_MEDICAL_RECORD = "UPDATE medical_record SET closed = ? WHERE id = ?";
    public static final String FIND_MEDICAL_RECORD_BY_ID = "SELECT * FROM medical_record WHERE id = ?";
    public static final String FIND_ALL_MEDICAL_RECORDS = "SELECT * FROM patient";
    public static final String FIND_MEDICAL_RECORDS_BY_PATIENT_ID = "SELECT * FROM medical_record WHERE patient_id = ? ORDER BY opened DESC";
    public static final String FIND_OPEN_MEDICAL_RECORD_BY_PATIENT_ID = "SELECT * FROM medical_record WHERE patient_id = ? AND closed IS NULL";
    public static final String CREATE_PATIENT = "INSERT INTO patient (first_name, last_name, date_of_birth, phone_number) VALUES (?,?,?,?)";
    public static final String FIND_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
    public static final String FIND_PATIENTS_BY_LAST_NAME = "SELECT * FROM patient WHERE last_name = ?";
    public static final String UPDATE_PATIENT = "UPDATE patient SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ? WHERE id = ?";
    public static final String DELETE_PATIENT = "DELETE FROM patient WHERE id = ?";
    public static final String FIND_ALL_PATIENTS = "SELECT * FROM patient";
    public static final String FIND_ALL_PATIENTS_IN_HOSPITAL = "SELECT patient.id, first_name, last_name, date_of_birth, phone_number "
            + "FROM patient JOIN medical_record ON(patient.id = medical_record.patient_id) WHERE closed IS NULL  GROUP BY patient.id";
    public static final String CREATE_PRESCRIPTION = "INSERT INTO prescription (name, description, performance_date, type_id, medical_record_id, prescriber_id, is_done) "
            + "VALUES (?,?,?,?,?,?,?)";
    public static final String FIND_PRESCRIPTION_BY_ID = "SELECT * FROM prescription JOIN prescription_type ON prescription.type_id=prescription_type.id WHERE prescription.id = ?";
    public static final String UPDATE_PRESCRIPTION = "UPDATE prescription SET performer_id  = ?, is_done = ? WHERE id = ?";
    public static final String FIND_PRESCRIPTIONS_BY_MEDICAL_RECORD_ID = "SELECT * FROM prescription JOIN prescription_type ON prescription.type_id=prescription_type.id "
            + "WHERE medical_record_id = ? ORDER BY is_done, performance_date";
    public static final String FIND_PRESCRIPTION_TYPE_BY_TYPE = "SELECT * FROM prescription_type WHERE type =?";
    public static final String CREATE_USER = "INSERT INTO user (first_name, last_name, date_of_birth, phone_number, email, password, user_type_id) VALUES (?,?,?,?,?,?,?)";
    public static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    public static final String FIND_ALL_USERS = "SELECT * FROM user";
    public static final String UPDATE_USER = "UPDATE user SET first_name = ?, last_name = ?, phone_number = ?, email = ?, password = ? WHERE id = ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    public static final String FIND_DIAGNOSIS_BY_MEDICAL_RECORD = "SELECT * FROM diagnosis WHERE medical_record_id = ? AND is_final='true'";
}
