package org.gello.model.HL7RIM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import junit.framework.TestCase;

import org.gello.model.HL7RIM.generated.CE;
import org.gello.model.HL7RIM.generated.Observation;
import org.gello.model.HL7RIM.generated.Patient;
import org.gello.runtime.CompileMessage;

import org.gello.runtime.GelloModelException;
import org.gello.runtime.IGelloModel;

public class TestHL7Model extends TestCase {
    
    /**
     * Handles model creation
     */
    public void setUp() {
        model = new HL7Model();
    }
    
    /**
     * Test the constructor
     *
     */
    public void testConstructor() {
        assertNotNull(model);
    }
    
    public void testGetProperty() {
        try {
            FileInputStream s = new FileInputStream(TEST_PATIENT_DEFAULT_PATH);
            model.loadData(s);
            Patient patient = (Patient)model.getContextRoot();
            Observation observation = (Observation)patient.getObservation().get(0);
            CE code = observation.getInterpretationCode().get(0);
            String codeSystemName = code.getCodeSystemName();
            assertNotNull(codeSystemName);
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }  catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    }
    
    public void testGetContext() {
        try {
            FileInputStream s = new FileInputStream(TEST_PATIENT_DEFAULT_PATH);
            model.loadData(s);
            Patient patient = (Patient)model.getContextRoot();
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }  catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    }
    
    /**
     * Test saving the model to a file
     *
     */
    public void testSaveData() {
        try {
            FileInputStream si = new FileInputStream(TEST_PATIENT_DEFAULT_PATH);
            model.loadData(si);
            File file = new File(TEST_PATIENT_DEFAULT_PATH);
            file.delete();
            assertFalse(file.exists());
            file = null;
            
            FileOutputStream so = new FileOutputStream(TEST_PATIENT_DEFAULT_PATH);
            model.saveData(so);
            file = new File(TEST_PATIENT_DEFAULT_PATH);
            assertTrue(file.exists());
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    };
    
    /**
     * Test parsing data
     */
    public void testParseData() {
        try {
            FileInputStream stream = new FileInputStream(TEST_PATIENT_DEFAULT_PATH);
            CompileMessage[] exceptions = model.parseData(stream);
            assertEquals(exceptions.length, 0);
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    };
    
    /**
     * Test parsing bad data
     **/
    public void testParseBadData() {
        testParseBadData(TEST_PATIENT_FAKE_TESTING_PATH, 1);
        testParseBadData(TEST_PATIENT_NO_MOOD_CODE_PATH, 2);
        testParseBadData(TEST_PATIENT_WRONG_OBSERVATION_LOCATION_PATH, 1);
    }
    
    /**
     * Test parsing bad data
     **/
    private void testParseBadData(String path, int errorExpectation) {
        try {
            FileInputStream stream = new FileInputStream(path);
            CompileMessage[] exceptions = model.parseData(stream);
            assertEquals(exceptions.length, errorExpectation);
            for (CompileMessage m : exceptions) {
                String line = "Line: " + m.getLine();
                String col = " Column: " + m.getColumn();
                String sev = " Severity: " + m.getSeverity();
                String key = " Key: " + m.getKey();
                String message = " Parameters: " + m.getParameters(0).toString();
                System.out.println(line + col + sev + key + message);
            }
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    }
    
    /**
     * Test loading the model
     *
     */
    public void testLoadData() {
        try {
            FileInputStream stream = new FileInputStream(TEST_PATIENT_DEFAULT_PATH);
            model.loadData(stream);
            Patient patient = (Patient)model.getContextRoot();
            List obs = patient.getObservation();
            assertEquals(obs.size(), 1);
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    }
    
    /**
     * Test loading the model from the default data
     *
     */
    public void testLoadDataDefault() {
        try {
            model.loadDataDefault();
            Patient patient = (Patient)model.getContextRoot();
            List obs = patient.getObservation();
            assertEquals(obs.size(), 2);
        } catch (GelloModelException e) {
            System.out.println(e.getMessage());
            assertFalse(true);
        }
    }
    
    private IGelloModel model;
    
    private final String TEST_PATIENT_DEFAULT_PATH = "test/org/gello/model/HL7RIM/PatientDefault.xml";
    private final String TEST_PATIENT_FAKE_TESTING_PATH = "test/org/gello/model/HL7RIM/Patient_FakeTesting.xml";
    private final String TEST_PATIENT_NO_MOOD_CODE_PATH = "test/org/gello/model/HL7RIM/Patient_NoMoodCode.xml";
    private final String TEST_PATIENT_WRONG_OBSERVATION_LOCATION_PATH = "test/org/gello/model/HL7RIM/Patient_WrongObservationLocation.xml";
}
