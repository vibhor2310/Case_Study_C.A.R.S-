package Junit_testing;


import dao.CrimeAnalysisServiceImpl;
import entity.Incidents;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CreateIncidentTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private CrimeAnalysisServiceImpl service; // The class under test

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void testCreateIncident() throws SQLException {
        // Arrange
        Incidents incident = new Incidents(1, "Robbery", new Date(), "123 Main St", "Stolen cash", "Open", 1, 1);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate 1 row inserted

        // Act
        boolean result = service.createIncident(incident);

        // Assert
        assertTrue(result, "Incident creation failed!");

        // Verify interactions with the mock objects
        verify(mockPreparedStatement, times(1)).setInt(1, incident.getIncidentID());
        verify(mockPreparedStatement, times(1)).setString(2, incident.getIncidentType());
        verify(mockPreparedStatement, times(1)).setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
        verify(mockPreparedStatement, times(1)).setString(4, incident.getLocation());
        verify(mockPreparedStatement, times(1)).setString(5, incident.getDescription());
        verify(mockPreparedStatement, times(1)).setString(6, incident.getStatus());
        verify(mockPreparedStatement, times(1)).setInt(7, incident.getVictimID());
        verify(mockPreparedStatement, times(1)).setInt(8, incident.getSuspectID());

        verify(mockPreparedStatement, times(1)).executeUpdate();

    }
}
