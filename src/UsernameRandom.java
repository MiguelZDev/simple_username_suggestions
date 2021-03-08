import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UsernameRandom {

    private static final String DOT = ".";

    public static void main (String [] args) {
        HashSet takenUsernames = new HashSet();
        List<String> suggestions = new ArrayList<>();

        initializeUsernames(takenUsernames); // O(1), propenso a colisión
        initializeSuggestions(suggestions);

        String username, suggestedUsername;
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Please Enter username: ");
            username = in.next();

            if (takenUsernames.contains(username)) {
                String randomWord1 = suggestions.get(draftRandomNumber(suggestions.size()));
                String randomWord2 = suggestions.get(draftRandomNumber(suggestions.size()));
                suggestedUsername = username + DOT + randomWord1 + DOT + randomWord2;
                System.out.println("Sorry, username already taken. We can suggest: " + suggestedUsername );
            } else {
                System.out.println("Your username '" + username + "' is available" );
            }
        } catch(Exception e) {
            System.out.println("Input Error");
            e.printStackTrace();
        }
    }

    public static void initializeUsernames(HashSet usernames) {
        try {
            ClassLoader classLoader = UsernameRandom.class.getClassLoader();
            File myObj = new File(classLoader.getResource("resource/taken_usernames.txt").getFile());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String un = myReader.nextLine();
                usernames.add(un);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void initializeSuggestions(List suggestions) {
        try {
            ClassLoader classLoader = UsernameRandom.class.getClassLoader();
            File myObj = new File(classLoader.getResource("resource/suggestions.txt").getFile());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String un = myReader.nextLine();
                suggestions.add(un);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int draftRandomNumber (int limit) {
        return new Random().nextInt(limit);
    }

}
