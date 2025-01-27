import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        ArrayList<Person> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        Person person1 = new Person("000001", "luke", "lindsey", "Mr.", 1990);
        Person person2 = new Person("000002", "Jane", "lindsey", "Ms.", 1985);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]:");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name:");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter the last name:");
            String title = SafeInput.getNonZeroLenString(in, "Enter the title:");
            int YOB = SafeInput.getRangedInt(in, "Enter the year of birth:", 1940, 2010); // Adjusted range per guidelines

            Person person = new Person(ID, firstName, lastName, title, YOB);
            folks.add(person);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while (!done);

        for (Person p : folks) {
            System.out.println(p.toCSV());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            for (Person person : folks) {
                writer.write(person.toCSV()); // Using Person's toCSV method
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Person {
    private String ID;
    private String firstName;
    private String lastName;
    private String title;
    private int YOB;

    // Constructor with all fields
    public Person(String ID, String firstName, String lastName, String title, int YOB) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    // Overloaded constructor
    public Person(String ID, String firstName, String lastName) {
        this(ID, firstName, lastName, "", 0); // Default values for optional fields
    }

    // Getters
    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYOB() {
        return YOB;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYOB(int YOB) {
        if (YOB >= 1940 && YOB <= 2010) {
            this.YOB = YOB;
        } else {
            throw new IllegalArgumentException("Year of Birth must be between 1940 and 2010.");
        }
    }

    // Methods
    public String toCSV() {
        return ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }

    public String toJSON() {
        return String.format("{\"ID\":\"%s\", \"firstName\":\"%s\", \"lastName\":\"%s\", \"title\":\"%s\", \"YOB\":%d}",
                ID, firstName, lastName, title, YOB);
    }

    public String toXML() {
        return String.format("<Person><ID>%s</ID><FirstName>%s</FirstName><LastName>%s</LastName><Title>%s</Title><YOB>%d</YOB></Person>",
                ID, firstName, lastName, title, YOB);
    }

    @Override
    public String toString() {
        return title + " " + firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB && Objects.equals(ID, person.ID) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(title, person.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, title, YOB);
    }

    public String formalName() {
        return title + " " + firstName + " " + lastName;
    }

    public int getAge() {
        return java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) - YOB;
    }

    public int getAge(int year) {
        return year - YOB;
    }
}
