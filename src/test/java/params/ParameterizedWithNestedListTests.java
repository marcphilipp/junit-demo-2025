package params;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ParameterizedClass(name = "[{index}] {0}")
@ValueSource(classes = {ArrayList.class, LinkedList.class, Vector.class})
public class ParameterizedWithNestedListTests {

    @Parameter
    @ConvertWith(Instantiate.class)
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

    @AfterEach
    void clearList() {
        list.clear();
    }

    @Nested
    @ParameterizedClass(name = "[{index}] {0}")
    @ValueSource(classes = {ArrayList.class, LinkedList.class, Vector.class})
    class Interoperability {

        @Parameter
        @ConvertWith(Instantiate.class)
        List<String> secondList;

        @Test
        void twoListsWithSameItemsAreEqual() {
            list.add("foo");
            list.add("bar");

            secondList.addAll(list);

            assertEquals(secondList, list);
        }

        @ParameterizedTest
        @ValueSource(strings = {"baz", "qux"})
        void removeAllItemsInPassedList(String extraItem) {
            list.add("foo");
            list.add("bar");

            secondList.add("foo");
            secondList.add(extraItem);

            list.removeAll(secondList);

            assertEquals(1, list.size());
            assertEquals("bar", list.getFirst());
        }

        @AfterEach
        void clearList() {
            secondList.clear();
        }
    }

}
