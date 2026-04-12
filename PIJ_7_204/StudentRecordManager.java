import java.io.*;
import java.util.*;

public class StudentRecordManager {

    private static final String FILE_NAME = "Students.csv";
    private static final String HEADER = "studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage";

    public static void main(String[] args) {
        System.out.println("=== STUDENT RECORD MANAGER ===");

        try {
            // 1. Create file, add header, and add a couple of initial rows
            initializeFile();
            System.out.println("\n[READ] Initial File State:");
            displayFile();

            // 2. CREATE: Add 3 more rows with marks4 and marks5 as 0 initially
            addThreeStudents();
            System.out.println("\n[CREATE] Added 3 new students with 0s for marks4 & marks5:");
            displayFile();

            // 3. UPDATE: Update rows with correct marks (replacing 0s)
            updateMarks();
            System.out.println("\n[UPDATE] Corrected marks4 and marks5 for all rows:");
            displayFile();

            // 4. UPDATE: Calculate percentage of each student and update the file
            calculatePercentageAndUpdate();
            System.out.println("\n[UPDATE] Calculated and updated percentages:");
            displayFile();

            // 5. DELETE: Delete a row from the file (e.g., student S02)
            deleteStudent("S02");
            System.out.println("\n[DELETE] Deleted student S02 from the records:");
            displayFile();

            // 6. EXCEPTION HANDLING: Intentionally simulate an IOException
            System.out.println("\n[EXCEPTION] Simulating an IOException...");
            simulateIOException();

        } catch (IOException e) {
            System.err.println("A critical file error occurred: " + e.getMessage());
        }
    }

    /**
     * Creates the file, writes the header, and adds 2 initial rows.
     */
    private static void initializeFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(HEADER);
            bw.newLine();
            bw.write("S01,Alice,CS,80,85,90,88,92,0.0");
            bw.newLine();
            bw.write("S02,Bob,IT,75,70,80,78,82,0.0");
            bw.newLine();
        }
    }

    /**
     * Appends 3 new students with marks4 and marks5 set to 0.
     */
    private static void addThreeStudents() throws IOException {
        // 'true' flag enables append mode
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write("S03,Charlie,ECE,60,65,70,0,0,0.0");
            bw.newLine();
            bw.write("S04,Diana,MECH,85,80,75,0,0,0.0");
            bw.newLine();
            bw.write("S05,Eve,CS,90,95,85,0,0,0.0");
            bw.newLine();
        }
    }

    /**
     * Reads all rows, updates marks4 and marks5 if they are 0, and writes back.
     */
    private static void updateMarks() throws IOException {
        List<String> lines = readAllLines();
        List<String> updatedLines = new ArrayList<>();

        updatedLines.add(lines.get(0)); // Keep header intact

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            // If marks4 or marks5 are 0, update them to corrected values
            if (Double.parseDouble(parts[6]) == 0) parts[6] = "75";
            if (Double.parseDouble(parts[7]) == 0) parts[7] = "80";
            
            updatedLines.add(String.join(",", parts));
        }
        writeAllLines(updatedLines);
    }

    /**
     * Calculates the percentage based on marks1 to marks5 and updates the file.
     */
    private static void calculatePercentageAndUpdate() throws IOException {
        List<String> lines = readAllLines();
        List<String> updatedLines = new ArrayList<>();

        updatedLines.add(lines.get(0)); // Keep header intact

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            
            double m1 = Double.parseDouble(parts[3]);
            double m2 = Double.parseDouble(parts[4]);
            double m3 = Double.parseDouble(parts[5]);
            double m4 = Double.parseDouble(parts[6]);
            double m5 = Double.parseDouble(parts[7]);
            
            double percentage = (m1 + m2 + m3 + m4 + m5) / 5.0;
            
            // Format to 2 decimal places using US locale to enforce '.' decimal separator in CSV
            parts[8] = String.format(Locale.US, "%.2f", percentage);
            
            updatedLines.add(String.join(",", parts));
        }
        writeAllLines(updatedLines);
    }

    /**
     * Deletes a student row by ignoring it during the rewrite process.
     */
    private static void deleteStudent(String studentId) throws IOException {
        List<String> lines = readAllLines();
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            // Only keep lines that do NOT start with the targeted studentId
            if (!line.startsWith(studentId + ",")) {
                updatedLines.add(line);
            }
        }
        writeAllLines(updatedLines);
    }

    /**
     * Reads the file and prints its contents to the console.
     */
    private static void displayFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        }
    }

    /**
     * Helper method to read all lines from the CSV into a List.
     */
    private static List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Helper method to write a List of strings back to the CSV.
     */
    private static void writeAllLines(List<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Intentionally attempts to read a file that doesn't exist to demonstrate Exception Handling.
     */
    private static void simulateIOException() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("NonExistentDirectory/FakeFile.csv"));
            br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("  -> Exception details: " + e.toString());
        }
    }
}