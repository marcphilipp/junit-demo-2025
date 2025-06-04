package params;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.support.ReflectionSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ParameterizedClass(name = "[{index}] {0}")
@ValueSource(classes = {ArrayList.class, LinkedList.class, Vector.class})
public class ParameterizedWithValueSourceListTests {

    List<String> list;

    ParameterizedWithValueSourceListTests(Class<? extends List<String>> list) {
        this.list = ReflectionSupport.newInstance(list);
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
