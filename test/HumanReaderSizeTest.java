import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HumanReaderSizeTest {

    @Test
    public void size() {
        long size = new HumanReaderSize("progr").size();
        assertEquals(153, size);// тестирование файла

        long sizeDirectoryInDirectory = new HumanReaderSize("progrDirectory").size();
        assertEquals(1741, sizeDirectoryInDirectory);// тестирование директории, в которой есть вложенная директория + несколько файлов

        long sizeDirectory = new HumanReaderSize("exemple1").size();
        assertEquals(942, sizeDirectory);// тестирование директория
    }
}