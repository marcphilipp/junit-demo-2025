package params;

import org.junit.jupiter.api.Test;
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
public record ParameterizedWithValueSourceAndConverterRecordListTests(
        @ConvertWith(Instantiate.class) List<String> list) {

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
