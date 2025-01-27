import java.util.Scanner;

public class SafeInputObj {
    private Scanner pipe;

    // Default constructor
    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    // Constructor with custom Scanner
    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    /**
     * Get a String which contains at least one character.
     *
     * @param prompt Prompt for the user
     * @return A non-empty String
     */
    public String getNonZeroLenString(String prompt) {
        String retString;
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.isBlank());
        return retString;
    }

    /**
     * Get an int value within a specified numeric range.
     *
     * @param prompt Input prompt message
     * @param low    Low end of inclusive range
     * @param high   High end of inclusive range
     * @return An int value within the inclusive range
     */
    public int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // Clear buffer
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Number out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                System.out.println("Invalid input. Try again.");
                pipe.nextLine(); // Clear invalid input
            }
        } while (!done);

        return retVal;
    }

    // Repeat for other methods, e.g., getInt, getDouble, getRangedDouble, getYNConfirm, etc.

    /**
     * Get a Y/N confirmation from the user.
     *
     * @param prompt Input prompt message
     * @return true for Yes, false for No
     */
    public boolean getYNConfirm(String prompt) {
        boolean retVal = false;
        boolean validInput = false;
        String response;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                retVal = true;
                validInput = true;
            } else if (response.equalsIgnoreCase("N")) {
                retVal = false;
                validInput = true;
            } else {
                System.out.println("You must answer [Y/N]!");
            }
        } while (!validInput);

        return retVal;
    }
}
