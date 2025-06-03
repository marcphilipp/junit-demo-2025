package params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTests {

    private static abstract class AbstractListTests {

        List<String> list;

        @BeforeEach
        void initializeList() {
            list = createList();
        }

        protected abstract <T> List<T> createList();

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

    @Nested
    @DisplayName("ArrayList")
    class ArrayListTests extends AbstractListTests {
        @Override
        protected <T> List<T> createList() {
            return new ArrayList<>();
        }
    }

    @Nested
    @DisplayName("LinkedList")
    class LinkedListTests extends AbstractListTests {
        @Override
        protected <T> List<T> createList() {
            return new LinkedList<>();
        }
    }

    @Nested
    @DisplayName("Vector")
    class VectorTests extends AbstractListTests {
        @Override
        protected <T> List<T> createList() {
            return new Vector<>();
        }
    }

}
