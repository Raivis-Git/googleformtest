package google;

import java.util.ArrayList;
import java.util.List;

public interface TestStepLogger {
    List<String> testSteps = new ArrayList<>();

    default void logStep(String step) {
        testSteps.add(step);
    }

    default List<String> getTestSteps() {
        return new ArrayList<>(testSteps);
    }

    default void clearTestSteps() {
        testSteps.clear();
    }
}
