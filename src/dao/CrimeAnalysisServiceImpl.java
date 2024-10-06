package dao;

import entity.Cases;
import entity.Incidents;
import entity.Reports;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService{

    @Override
    public boolean createIncident(Incidents incident) {
        return false;
    }

    @Override
    public boolean updateIncidentStatus(String status, int incidentId) {
        return false;
    }

    @Override
    public Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) {
        return List.of();
    }

    @Override
    public Collection<Incidents> searchIncidents(String criteria) {
        return List.of();
    }

    @Override
    public Reports generateIncidentReport(Incidents incident) {
        return null;
    }

    @Override
    public Cases createCase(String caseDescription, Collection<Incidents> incidents) {
        return null;
    }

    @Override
    public Cases getCaseDetails(int caseId) {
        return null;
    }

    @Override
    public boolean updateCaseDetails(Cases caseDetails) {
        return false;
    }

    @Override
    public Collection<Cases> getAllCases() {
        return List.of();
    }
}
