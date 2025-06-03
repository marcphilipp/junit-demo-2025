package params;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.platform.commons.support.ReflectionSupport;

class Instantiate extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        Class<?> clazz = (Class<?>) source;
        return ReflectionSupport.newInstance(clazz);
    }
}
