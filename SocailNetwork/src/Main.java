import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartPage mainPage = new StartPage();
                mainPage.setVisible(true);
            }
        });
    }
}