package laba5;

/**
 * Реализация, выводящая сообщение в консоль.
 */
public class ConsoleMessageService implements MessageService {
    @Override
    public void print(String text) {
        System.out.println("[DI] " + text);
    }
}