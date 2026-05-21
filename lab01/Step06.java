package lab01;

public class Step06 {
    // Step 06: interfaces and dependency inversion

    interface Notifier {
        void send(String message);
    }

    static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Email: " + message);
        }
    }

    static class SmsNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("SMS: " + message);
        }
    }

    static class AlertService {
        private final Notifier notifier;

        AlertService(Notifier notifier) {
            this.notifier = notifier;
        }

        void alert(String message) {
            notifier.send(message);
        }
    }

    public static void main(String[] args) {
        AlertService emailAlerts = new AlertService(new EmailNotifier());
        AlertService smsAlerts = new AlertService(new SmsNotifier());

        emailAlerts.alert("Your order shipped.");
        smsAlerts.alert("Your code is 123456.");
    }
}
