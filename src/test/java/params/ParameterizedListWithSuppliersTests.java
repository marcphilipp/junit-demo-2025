package params;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@ParameterizedClass
@MethodSource("listImplementations")
class ParameterizedListWithSuppliersTests {

    static Stream<?> listImplementations() {
        return Stream.of(
                argumentSet("ArrayList", (Supplier<?>) ArrayList::new),
                argumentSet("LinkedList", (Supplier<?>) LinkedList::new),
                argumentSet("Vector", (Supplier<?>) Vector::new)
        );
    }

    final List<String> list;

    ParameterizedListWithSuppliersTests(Supplier<List<String>> listSupplier) {
        this.list = listSupplier.get();
    }

    @Test
    void newListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void itemCanBeAdded() {
        var added = list.add("value");

        assertTrue(added);
        assertTrue(list.contains("value"));
        assertEquals("value", list.getFirst());
    }
}
