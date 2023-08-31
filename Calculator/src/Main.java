import javax.swing.*;
public class Main extends JFrame{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        //işletim sistemi arayüzü ile çalışır
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Arayuz form=new Arayuz();
                form.setVisible(true);
            }
        });
    }
}