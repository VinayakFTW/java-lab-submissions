public class Main {
    public static void main(String[] args) {
        // This is a workaround to run JavaFX applications in IntelliJ without needing to specify VM options.
        // It bypasses the Java module system restrictions by launching from a class that doesn't extend javafx.application.Application.
        RestaurantJavaFXApp.main(args);
    }
}
