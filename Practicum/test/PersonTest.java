import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testConstructorAndGetters() {
        Person person = new Person("000001", "Luke", "Lindsey", "Lord", 2005);
        assertEquals("000001", person.getID());
        assertEquals("Luke", person.getFirstName());
        assertEquals("Lindsey", person.getLastName());
        assertEquals("Lord", person.getTitle());
        assertEquals(2005, person.getYOB());
    }

    @Test
    void testOverloadedConstructor() {
        Person person = new Person("000001", "Luke", "Lindsey");
        assertEquals("000001", person.getID());
        assertEquals("Luke", person.getFirstName());
        assertEquals("Lindsey", person.getLastName());
        assertEquals("", person.getTitle());
        assertEquals(0, person.getYOB());
    }

    @Test
    void testSetters() {
        Person person = new Person("000002", "Luke", "Lindsey", "Lord", 2006);
        person.setFirstName("Lucas");
        person.setLastName("Smith");
        person.setTitle("Sir");
        person.setYOB(1995);

        assertEquals("Lucas", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("Sir", person.getTitle());
        assertEquals(1995, person.getYOB());
    }

    @Test
    void testToString() {
        Person person = new Person("000002", "Luke", "Lindsey", "Lord", 2006);
        assertEquals("Lord Luke Lindsey", person.toString());
    }

    @Test
    void testEquals() {
        Person person1 = new Person("000001", "Luke", "Lindsey", "Lord", 2005);
        Person person2 = new Person("000001", "Luke", "Lindsey", "Lord", 2005);
        Person person3 = new Person("000002", "Lucas", "Smith", "Sir", 1995);

        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
    }

    @Test
    void testToCSV() {
        Person person = new Person("000002", "Luke", "Lindsey", "Lord", 2006);
        assertEquals("000002,Luke,Lindsey,Lord,2006", person.toCSV());
    }

    @Test
    void testToJSON() {
        Person person = new Person("000001", "Luke", "Lindsey", "Lord", 2005);
        String expectedJSON = "{\"ID\":\"000001\", \"firstName\":\"Luke\", \"lastName\":\"Lindsey\", \"title\":\"Lord\", \"YOB\":2005}";
        assertEquals(expectedJSON, person.toJSON());
    }

    @Test
    void testToXML() {
        Person person = new Person("000001", "Luke", "Lindsey", "Lord", 2005);
        String expectedXML = "<Person><ID>000001</ID><FirstName>Luke</FirstName><LastName>Lindsey</LastName><Title>Lord</Title><YOB>2005</YOB></Person>";
        assertEquals(expectedXML, person.toXML());
    }

    @Test
    void testGetAge() {
        Person person = new Person("000002", "Luke", "Lindsey", "Lord", 2006);
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        assertEquals(currentYear - 2006, person.getAge());
        assertEquals(24, person.getAge(2030));
    }
}
