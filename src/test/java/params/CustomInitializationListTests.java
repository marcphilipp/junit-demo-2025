package params;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.BeforeParameterizedClassInvocation;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ParameterizedClass(name = "{0}")
@ValueSource(classes = {ArrayList.class, LinkedList.class, Vector.class})
record CustomInitializationListTests(
        @ConvertWith(Instantiate.class) List<String> list) {

    @BeforeParameterizedClassInvocation
    static void before(List<String> list) {
        if (list instanceof ArrayList<?> arrayList) {
            arrayList.ensureCapacity(100);
        }
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

    @AfterEach
    void clearList() {
        list.clear();
    }
}
