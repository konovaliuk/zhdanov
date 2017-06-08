package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import command.LoginCommand;
import command.LogoutCommand;
import command.MakeDiagnosisCommand;
import command.PerformPrescriptionCommand;
import command.PrescriptionInfoCommand;
import command.CreateMedicalRecordCommand;
import command.CreatePrescriptionCommand;
import command.GoToMainCommand;
import command.GoToMedicalRecordCommand;
import command.GoToNewDiagnosisCommand;
import command.GoToNewPrescriptionCommand;
import command.RegisterPatientCommand;
import command.ChangeLanguageCommand;
import command.Command;
import command.PatientInfoCommand;
import command.GoToPatientRegistrationCommad;

public class ControllerHelper {

    private static ControllerHelper controllerHelper = null;
    private Map<String, Command> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("Login", new LoginCommand());
        commands.put("Logout", new LogoutCommand());
        commands.put("GoToMainPage", new GoToMainCommand());
        commands.put("GoToPatientRegistration", new GoToPatientRegistrationCommad());
        commands.put("GoToPatientInfoPage", new PatientInfoCommand());
        commands.put("RegisterPatient", new RegisterPatientCommand());
        commands.put("GoToMedicalRecord", new GoToMedicalRecordCommand());
        commands.put("CreateMedicalRecord", new CreateMedicalRecordCommand());
        commands.put("PerformPrescription", new PerformPrescriptionCommand());
        commands.put("GoToNewPrescription", new GoToNewPrescriptionCommand());
        commands.put("CreatePrescription", new  CreatePrescriptionCommand());
        commands.put("GoToNewDiagnosis", new  GoToNewDiagnosisCommand());
        commands.put("MakeDiagnosis", new MakeDiagnosisCommand());
        commands.put("PrescriptionInfo", new PrescriptionInfoCommand());
        commands.put("ChangeLanguage", new ChangeLanguageCommand());
    }

    public static ControllerHelper getInstance() {
        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }
        return controllerHelper;
    }

    public Command getCommand(HttpServletRequest request) {
        if (request.getParameter("command") == null) {

            return commands.get("GoToMainPage");
        }
        return commands.get(request.getParameter("command"));
    }
}
