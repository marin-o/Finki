package junit;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class VisaApplicationsTest {
    //commented asserts would be for functionality based testing

    private List<String> uk; //test fixture
    private List<String> usa; //test fixture

    @After
    public void setUpAfter() {
        uk = null;
        usa = null;
    }

    /**
     * Testing base case. Testing if both lists exist and are non-empty.
     */
    @Test
    public void testTTTT(){
        uk = new ArrayList<>();
        usa = new ArrayList<>();
        uk.add("123");
        uk.add("456");
        usa.add("456");
        usa.add("789");
        assertNotNull(uk);
        assertNotNull(usa);
        assertFalse(uk.isEmpty());
        assertFalse(usa.isEmpty());
        assertArrayEquals(new String[]{"123"}, VisaApplications.applicantsForUkVisaOnly(uk, usa).toArray());
    }

    /**
     * Testing if uk list is empty, and us list is non-empty and non-null
     */
    @Test
    public void testFTTT(){
        uk = new ArrayList<>();
        usa = new ArrayList<>();
        usa.add("456");
        usa.add("789");
        assertNotNull(uk);
        assertNotNull(usa);
        assertTrue(uk.isEmpty());
        assertNull(VisaApplications.applicantsForUkVisaOnly(uk, usa));
    }
    /**
     * Testing if uk list is null, and us list is non-empty and non-null
     */
    @Test
    public void testTFTT(){
        usa = new ArrayList<>();
        usa.add("456");
        usa.add("789");
        assertNull(uk);
        assertNotNull(usa);
        assertFalse(usa.isEmpty());
        assertThrows(NullPointerException.class, () -> VisaApplications.applicantsForUkVisaOnly(uk, usa));
    }
    /**
     * Testing if us list is empty, and uk list is non-empty and non-null
     */
    @Test
    public void testTTFT(){
        uk = new ArrayList<>();
        usa = new ArrayList<>();
        uk.add("456");
        uk.add("789");
        assertNotNull(uk);
        assertNotNull(usa);
        assertTrue(usa.isEmpty());
//        assertArrayEquals(new String[]{"456","789"},VisaApplications.applicantsForUkVisaOnly(uk, usa).toArray());
    }
    /**
     * Testing if us list is null, and uk list is non-empty and non-null
     */
    @Test
    public void testTTTF(){
        uk = new ArrayList<>();
        uk.add("456");
        uk.add("789");
        assertNotNull(uk);
        assertNull(usa);
        assertThrows(NullPointerException.class, () -> VisaApplications.applicantsForUkVisaOnly(uk, usa));
    }

}
