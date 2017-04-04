import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HumanReaderSizeTest {

    @Test
    public void size() {
        long size = new HumanReaderSize("progr").size();
        assertEquals(153, size);// тестирование файла

        long sizeDirectory = new HumanReaderSize("progrDirectory").size();
        assertEquals(799, sizeDirectory);// тестирование папки
    }
}