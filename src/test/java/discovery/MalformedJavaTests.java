package discovery;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@SuppressWarnings("JUnitMalformedDeclaration")
class MalformedJavaTests {

    @Test
    int test() {
        return 42;
    }

    @TestFactory
    Object testFactory() {
        return List.of(dynamicTest("test", () -> {}));
    }

    @Nested
    static class InnerTests {

        @Test
        void test() {}
    }

}
