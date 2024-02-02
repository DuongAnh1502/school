package j.s0052;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class JS0025 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/data/input.txt"))){
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            String normalizedText = text.toString().trim();
            normalizedText = normalizedText.replaceAll(" +", " ");
            normalizedText = normalizedText.replace(", ", ",").replace(". ", ".").replace(": ", ":");
            normalizedText = normalizedText.replace(" \"", "\"").replace("\" ", "\"");
            normalizedText = Character.toUpperCase(normalizedText.charAt(0)) + normalizedText.substring(1);
            normalizedText = normalizedText.replace(",", ", ").replace(".", ". ").replace(":", ": ");
            normalizedText = normalizedText.replaceAll("\n","");
            if (!normalizedText.endsWith(".")) {
                normalizedText += '.';
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/data/output.txt"));
            writer.write(normalizedText);
            writer.close();
            System.out.println("Normalization and writing to 'output.txt' successful.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
    }
}
