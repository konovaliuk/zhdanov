package service;

import java.util.Calendar;

import org.apache.log4j.Logger;

import dao.DAOFactory;
import model.Prescription;
import model.PrescriptionType;

public class PrescriptionService {

    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static final Logger logger = Logger.getLogger(PrescriptionService.class.getName());

    public static Prescription performPrescription(int prescriptionId, int performerId) {
        Prescription prescription = daoFactory.getPrescriptionDAO().findById(prescriptionId);
        if (prescription.getIsDone() == false) {
            prescription.setPerformerId(performerId);
            prescription.setIsDone(true);
            prescription = daoFactory.getPrescriptionDAO().update(prescription);
        }
        return prescription;
    }

    public static PrescriptionType getPrescriptionType(String type) {
        return daoFactory.getPrescriptionTypeDAO().findByType(type);
    }

    public static Prescription createPrescription(Prescription prescription) {
        return daoFactory.getPrescriptionDAO().create(prescription);
    }

    public static void createPrescriptions(Prescription prescription, int days, int timesPerDay) {
        for (int i = days; i > 0; i--) {
            for (int j = timesPerDay; j > 0; j--) {
                daoFactory.getPrescriptionDAO().create(prescription);
                logger.info("Prescription was created: " + prescription);
            }
            Calendar performanceDate = Calendar.getInstance();
            performanceDate.setTime(prescription.getPerformanceDate());
            performanceDate.add(Calendar.DAY_OF_MONTH, 1);
            prescription.setPerformanceDate(performanceDate.getTime());
        }
    }

    public static Prescription getPrescription(int prescriptionId) {
        return daoFactory.getPrescriptionDAO().findById(prescriptionId);

    }

}
