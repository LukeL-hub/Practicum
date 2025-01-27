public class ObjInputTest {
    public static void main(String[] args) {
        SafeInputObj input = new SafeInputObj();

        // Test getNonZeroLenString
        String nonEmptyString = input.getNonZeroLenString("Enter a non-empty string");
        System.out.println("You entered: " + nonEmptyString);

        // Test getRangedInt
        int rangedInt = input.getRangedInt("Enter a number between 1 and 10", 1, 10);
        System.out.println("You entered: " + rangedInt);

        // Test getYNConfirm
        boolean confirm = input.getYNConfirm("Do you agree?");
        System.out.println("You answered: " + (confirm ? "Yes" : "No"));

        // Add tests for any other methods you implemented
    }
}
