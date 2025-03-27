import java.io.*;
import java.nio.file.*;
import java.security.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Read JSON file
            String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));

            // Parse JSON
            JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
            JsonObject student = jsonObject.getAsJsonObject("student");

            // Extract values
            String firstName = student.get("first_name").getAsString().toLowerCase().replace(" ", "");
            String rollNumber = student.get("roll_number").getAsString().toLowerCase().replace(" ", "");

            // Concatenate and generate MD5 hash
            String concatenated = firstName + rollNumber;
            String md5Hash = generateMD5(concatenated);

            // Write to output.txt
            Files.write(Paths.get("output.txt"), md5Hash.getBytes());

            System.out.println("MD5 hash generated and stored in output.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate MD5 hash
    private static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
