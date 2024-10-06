package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.Cases;
import entity.Incidents;
import entity.Reports;
import java.util.*;

public class MainModule {

    public static void main(String[] args) {
        ICrimeAnalysisService service= new CrimeAnalysisServiceImpl();
        System.out.println("Welcome to Crime Analysis and Reporting System");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out .println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Create Case");
            System.out.println("7. Get Case Details");
            System.out.println("8. Update Case Details");
            System.out.println("9. Get All Cases");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createIncident(service, scanner);
                    break;
                case 2:
                    updateIncidentStatus(service, scanner);
                    break;
                case 3:
                    getIncidentsInDateRange(service, scanner);
                    break;
                case 4:
                    searchIncidents(service, scanner);
                    break;
                case 5:
                    generateIncidentReport(service, scanner);
                    break;
                case 6:
                    createCase(service, scanner);
                    break;
                case 7:
                    getCaseDetails(service, scanner);
                    break;
                case 8:
                    updateCaseDetails(service, scanner);
                    break;
                case 9:
                    getAllCases(service);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    private static void createIncident (ICrimeAnalysisService service, Scanner scanner) {
        Incidents incident = new Incidents();
        System.out.println("Enter incident ID");
        incident.setIncidentID(scanner.nextInt());
        System.out.print("Enter incident type: ");
        incident.setIncidentType(scanner.nextLine());
        System.out.print("Enter incident date (yyyy-MM-dd): ");
        String incidentDateStr = scanner.nextLine();
        try {
            Date incidentDate = new Date(incidentDateStr);
            incident.setIncidentDate(incidentDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        System.out.print("Enter location: ");
        incident.setLocation(scanner.nextLine());
        System.out.print("Enter description: ");
        incident.setDescription(scanner.nextLine());
        System.out.print("Enter status: ");
        incident.setStatus(scanner.nextLine());
        System.out.print("Enter victim ID: ");
        incident.setVictimID(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter suspect ID: ");
        incident.setSuspectID(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character

        if (service.createIncident(incident)) {
            System.out.println("Incident created successfully.");
        } else {
            System.out.println("Failed to create incident.");
        }
    }

    private static void updateIncidentStatus(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter incident ID: ");
        int incidentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        if (service.updateIncidentStatus(status, incidentId)) {
            System.out.println("Incident status updated successfully.");
        } else {
            System.out.println("Failed to update incident status.");
        }
    }

    private static void getIncidentsInDateRange(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        try {
            Date startDate = new Date(startDateStr);
            Date endDate = new Date(endDateStr);
            Collection<Incidents> incidents = service.getIncidentsInDateRange(startDate, endDate);

            if (incidents.isEmpty()) {
                System.out.println("No incidents found in the given date range.");
            } else {
                for (Incidents incident : incidents) {
                    System.out.println("Incident ID: " + incident.getIncidentID());
                    System.out.println("Incident Type: " + incident.getIncidentType());
                    System.out.println("Incident Date: " + incident.getIncidentDate());
                    System.out.println("Location: " + incident.getLocation());
                    System.out.println("Description: " + incident.getDescription());
                    System.out.println("Status: " + incident.getStatus());
                    System.out.println("Victim ID: " + incident.getVictimID());
                    System.out.println("Suspect ID: " + incident.getSuspectID());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void searchIncidents(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        Collection<Incidents> incidents = service.searchIncidents(keyword);

        if (incidents.isEmpty()) {
            System.out.println("No incidents found matching the search keyword.");
        } else {
            for (Incidents incident : incidents) {
                System.out.println("Incident ID: " + incident.getIncidentID());
                System.out.println("Incident Type: " + incident.getIncidentType());
                System.out.println("Incident Date: " + incident.getIncidentDate());
                System.out.println("Location: " + incident.getLocation());
                System.out.println("Description: " + incident.getDescription());
                System.out.println("Status: " + incident.getStatus());
                System.out.println("Victim ID: " + incident.getVictimID());
                System.out.println("Suspect ID: " + incident.getSuspectID());
                System.out.println();
            }
        }
    }

    private static void generateIncidentReport(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter incident ID: ");
        int incidentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Incidents incident = new Incidents();
        incident.setIncidentID(incidentId);

        Reports report = service.generateIncidentReport(incident);

        if (report != null) {
            System.out.println("Incident Report:");
            System.out.println("Report ID: " + report.getReportID());
            System.out.println("Incident ID: " + report.getIncidentID());
            System.out.println("Reporting Officer: " + report.getReportingOfficer());
            System.out.println("Report Date: " + report.getReportDate());
            System.out.println("Report Details: " + report.getReportDetails());
            System.out.println("Status: " + report.getStatus());
            System.out.println();
        } else {
            System.out.println("Failed to generate incident report.");
        }
    }

    private static void createCase(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter case description: ");
        String caseDescription = scanner.nextLine();

        System.out.print("Enter number of incidents: ");
        int numIncidents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Collection<Incidents> incidents = new ArrayList<>();

        for (int i = 0; i < numIncidents; i++) {
            System.out.print("Enter incident ID " + (i + 1) + ": ");
            int incidentId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Incidents incident = new Incidents();
            incident.setIncidentID(incidentId);

            incidents.add(incident);
        }

        Cases caseObj = service.createCase(caseDescription, incidents);

        if (caseObj != null) {
            System.out.println("Case created successfully.");
            System.out.println("Case ID: " + caseObj.getCaseID());
            System.out.println("Case Description: " + caseObj.getCaseDescription());
            System.out.println("Incident IDs: ");
            for (Incidents incident : incidents) {
                System.out.println(incident.getIncidentID());
            }
        } else {
            System.out.println("Failed to create case.");
        }
    }

    private static void getCaseDetails(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter case ID: ");
        int caseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Cases caseObj = service.getCaseDetails(caseId);

        if (caseObj != null) {
            System.out.println("Case Details:");
            System.out.println("Case ID: " + caseObj.getCaseID());
            System.out.println("Case Description: " + caseObj.getCaseDescription());
            System.out.println("Incident ID: " + caseObj.getIncidentsID());
        } else {
            System.out.println("Failed to retrieve case details.");
        }
    }

    private static void updateCaseDetails(ICrimeAnalysisService service, Scanner scanner) {
        System.out.print("Enter case ID: ");
        int caseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new case description: ");
        String caseDescription = scanner.nextLine();

        Cases caseObj = new Cases();
        caseObj.setCaseID(caseId);
        caseObj.setCaseDescription(caseDescription);

        if (service.updateCaseDetails(caseObj)) {
            System.out.println("Case details updated successfully.");
        } else {
            System.out.println("Failed to update case details.");
        }
    }

    private static void getAllCases(ICrimeAnalysisService service) {
        Collection<Cases> cases = service.getAllCases();

        if (cases.isEmpty()) {
            System.out.println("No cases found.");
        } else {
            for (Cases caseObj : cases) {
                System.out.println("Case ID: " + caseObj.getCaseID());
                System.out.println("Case Description: " + caseObj.getCaseDescription());
                System.out.println("Incident ID: " + caseObj.getIncidentsID());
                System.out.println();
            }
        }
    }

}
