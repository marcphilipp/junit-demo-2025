package params;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.support.ReflectionSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@ParameterizedClass
@MethodSource("listImplementations")
public class ParameterizedListTests {

    static Stream<?> listImplementations() {
        return Stream.of(ArrayList.class, LinkedList.class, Vector.class)
                .map((Class<?> clazz) -> argumentSet(clazz.getSimpleName(), ReflectionSupport.newInstance(clazz)));
    }

    @Parameter
    List<String> list;

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
