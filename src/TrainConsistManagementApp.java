import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    private final Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
    private final Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

    @Test
    void testRegex_ValidTrainID() {
        assertTrue(trainPattern.matcher("TRN-1234").matches());
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        assertFalse(trainPattern.matcher("TRAIN12").matches());
        assertFalse(trainPattern.matcher("TRN12A").matches());
        assertFalse(trainPattern.matcher("1234-TRN").matches());
    }

    @Test
    void testRegex_ValidCargoCode() {
        assertTrue(cargoPattern.matcher("PET-AB").matches());
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        assertFalse(cargoPattern.matcher("PET-ab").matches());
        assertFalse(cargoPattern.matcher("PET123").matches());
        assertFalse(cargoPattern.matcher("AB-PET").matches());
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(trainPattern.matcher("TRN-123").matches());
        assertFalse(trainPattern.matcher("TRN-12345").matches());
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(cargoPattern.matcher("PET-Ab").matches());
        assertFalse(cargoPattern.matcher("PET-aB").matches());
    }

    @Test
    void testRegex_EmptyInputHandling() {
        assertFalse(trainPattern.matcher("").matches());
        assertFalse(cargoPattern.matcher("").matches());
    }

    @Test
    void testRegex_ExactPatternMatch() {
        assertFalse(trainPattern.matcher("TRN-1234XYZ").matches());
        assertFalse(cargoPattern.matcher("PET-AB12").matches());
        // Create bogie list
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("First Class", 24));

        // Calculate total seating capacity using reduce
        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // Display result
        System.out.println("\nTotal Seating Capacity: " + totalSeats);
        // Group bogies by type (name)
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Display grouped bogies
        System.out.println("\nGrouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}