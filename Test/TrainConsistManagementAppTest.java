import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    private List<Bogie> getSampleBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("First Class", 24));
        return bogies;
    }

    @Test
    void testGrouping_BogiesGroupedByType() {
        Map<String, List<Bogie>> result = getSampleBogies().stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        Map<String, List<Bogie>> result = getSampleBogies().stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        Map<String, List<Bogie>> result = getSampleBogies().stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertEquals(3, result.keySet().size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<Bogie> bogies = List.of(new Bogie("Sleeper", 72));

        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        Map<String, List<Bogie>> result = getSampleBogies().stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        Map<String, List<Bogie>> result = getSampleBogies().stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(1, result.get("AC Chair").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<Bogie> bogies = getSampleBogies();

        Map<String, List<Bogie>> result = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        assertEquals(4, bogies.size()); // original unchanged
    }
}