import java.io.*;
import java.nio.file.*;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        try {
            
            String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));
            JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
            JsonObject student = jsonObject.getAsJsonObject("student"); 
            String firstName = student.get("first_name").getAsString().toLowerCase().replace(" ", "");
            String rollNumber = student.get("roll_number").getAsString().toLowerCase().replace(" ", "");
            String concatenated = firstName + rollNumber;
            String md5Hash = generateMD5(concatenated);

            
            Files.write(Paths.get("output.txt"), md5Hash.getBytes());

            System.out.println("MD5 hash generated and stored in output.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
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
