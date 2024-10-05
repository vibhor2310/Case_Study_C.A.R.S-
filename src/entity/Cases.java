package entity;

public class Cases {
    private int caseId;
    private String caseDescription;
    private int incidentsID;

    // Constructor
    public Cases() {

    }

    public Cases(int caseId, String caseDescription, int incidentsID) {
        this.caseId = caseId;
        this.caseDescription = caseDescription;
        this.incidentsID = incidentsID;
    }

    // getters and setters
    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public int getIncidentsID() {
        return incidentsID;
    }

    public void setIncidentsID(int incidentsID) {
        this.incidentsID = incidentsID;
    }

    @Override
    public String toString() {
        return "Cases [caseId=" + caseId + ", caseDescription=" + caseDescription +
                ",\nIncident ID =" + incidentsID + "]\n";
    }

}
