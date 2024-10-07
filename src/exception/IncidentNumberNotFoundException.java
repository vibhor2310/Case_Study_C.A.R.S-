package exception;

public class IncidentNumberNotFoundException extends Throwable {
    public IncidentNumberNotFoundException() {
        super("Given Incident ID does not exist.");
    }

    @Override
    public String toString(){
        return "IncidentNumberNotFoundException: "+getMessage();

    }
}
