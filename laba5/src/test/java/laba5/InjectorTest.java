package laba5;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {

    @Test
    public void testInjection() throws IOException {
        // Перехватываем вывод
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App app = new Injector().inject(new App());
        app.run();

        String output = out.toString();
        assertTrue(output.contains("[DI] Successful"));
    }

    @Test
    public void testCorrectImplementationType() {
        App app = new Injector().inject(new App());
        assertNotNull(app.service);
        assertTrue(app.service instanceof ConsoleMessageService);
    }
}