import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    private List<Bogie> getSampleBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));
        return bogies;
    }

    @Test
    void testReduce_TotalSeatCalculation() {
        int total = getSampleBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(156, total);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        int total = getSampleBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertTrue(total > 0);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> bogies = List.of(new Bogie("Sleeper", 72));

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Bogie> bogies = getSampleBogies();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(156, total);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<Bogie> bogies = getSampleBogies();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(72 + 60 + 24, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> bogies = getSampleBogies();

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(3, bogies.size()); // original list unchanged
    }
}