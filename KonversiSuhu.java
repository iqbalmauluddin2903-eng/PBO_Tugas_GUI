import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 1. Top-level Container Class (JFrame)
public class KonversiSuhu extends JFrame implements ActionListener {

    // 3. Atomic Component Class
    private JTextField inputCelcius;
    private JLabel labelHasil;
    private JButton btnKonversi, btnReset;

    public KonversiSuhu() {
        // Pengaturan Top-level Container
        setTitle("Konversi Suhu Sederhana");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 4. Layout Class #1: BorderLayout pada JFrame
        setLayout(new BorderLayout(10, 10));

        // 2. Intermediate Container Class #1 (JPanel) + 4. Layout Class #2 (GridLayout)
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        panelForm.add(new JLabel("Suhu (Celcius):"));
        inputCelcius = new JTextField();
        panelForm.add(inputCelcius);

        panelForm.add(new JLabel("Hasil (Fahrenheit):"));
        labelHasil = new JLabel("-");
        labelHasil.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelForm.add(labelHasil);

        // 2. Intermediate Container Class #2 (JPanel) + 4. Layout Class #3 (FlowLayout)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnKonversi = new JButton("Konversi");
        btnReset = new JButton("Reset");

        panelTombol.add(btnKonversi);
        panelTombol.add(btnReset);

        // Memasukkan Intermediate Container ke Top-level Container
        add(panelForm, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        // 5. Event Listener (Mendaftarkan listener ke komponen)
        btnKonversi.addActionListener(this);
        btnReset.addActionListener(this);

        // Contoh tambahan menggunakan Event Adapter Class (KeyAdapter) untuk tombol Enter
        inputCelcius.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    hitungKonversi();
                }
            }
        });
    }

    // 5. Event Method Override dari Event Listener Interface (ActionListener)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnKonversi) {
            hitungKonversi();
        } else if (e.getSource() == btnReset) {
            inputCelcius.setText("");
            labelHasil.setText("-");
            inputCelcius.requestFocus();
        }
    }

    private void hitungKonversi() {
        try {
            double celcius = Double.parseDouble(inputCelcius.getText());
            double fahrenheit = (celcius * 9 / 5) + 32;
            labelHasil.setText(String.format("%.2f °F", fahrenheit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KonversiSuhu().setVisible(true);
        });
    }
}