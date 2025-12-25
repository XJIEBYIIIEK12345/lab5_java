package laba5;

/**
 * Демонстрационный класс для проверки работы инжектора.
 */
public class App {
    @AutoInjectable
    MessageService service;

    public void run() {
        service.print("Successful");
    }

    public static void main(String[] args) {
        new Injector().inject(new App()).run();
    }
}