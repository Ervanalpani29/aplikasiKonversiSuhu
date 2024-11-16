/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiKonversiSuhu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author User
 */
public class aplikasiKonversiSuhu {
      private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> scaleComboBox;
    private JButton convertButton;

    // Main method to run the application
    public static void main(String[] args) {
                EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Mengatur tema modern
                aplikasiKonversiSuhu window = new aplikasiKonversiSuhu();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the GUI
    public aplikasiKonversiSuhu() {
        frame = new JFrame("Aplikasi Konversi Suhu");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(70, 70, 90)); // Warna latar belakang

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label input
        JLabel inputLabel = new JLabel("Masukkan nilai suhu:");
        inputLabel.setForeground(Color.WHITE); // Warna teks putih
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        frame.getContentPane().add(inputLabel, gbc);

        // JTextField untuk input suhu
        inputField = new JTextField();
        inputField.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.getContentPane().add(inputField, gbc);

        // Membatasi input hanya angka
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
                    e.consume();
                }
            }
        });

        // ComboBox untuk memilih skala suhu
        JLabel scaleLabel = new JLabel("Pilih skala suhu:");
        scaleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(scaleLabel, gbc);

        String[] scales = {"Celcius", "Fahrenheit", "Reamur", "Kelvin"};
        scaleComboBox = new JComboBox<>(scales);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.getContentPane().add(scaleComboBox, gbc);

        // Tombol Konversi
        convertButton = new JButton("Konversi");
        convertButton.setBackground(new Color(30, 144, 255)); // Warna biru
        convertButton.setForeground(Color.WHITE); // Warna teks putih
        convertButton.addActionListener(e -> convertTemperature());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(convertButton, gbc);

        // Label untuk hasil konversi
        resultLabel = new JLabel("Hasil: ");
        resultLabel.setForeground(Color.ORANGE); // Warna teks kuning
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(resultLabel, gbc);
    }

    // Fungsi untuk konversi suhu
    private void convertTemperature() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap masukkan nilai suhu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double inputTemperature = Double.parseDouble(input);
            String selectedScale = (String) scaleComboBox.getSelectedItem();
            String resultText = "";

            switch (selectedScale) {
                case "Celcius":
                    resultText = "Fahrenheit: " + celciusToFahrenheit(inputTemperature) + "\n"
                            + "Reamur: " + celciusToReamur(inputTemperature) + "\n"
                            + "Kelvin: " + celciusToKelvin(inputTemperature);
                    break;
                case "Fahrenheit":
                    resultText = "Celcius: " + fahrenheitToCelcius(inputTemperature) + "\n"
                            + "Reamur: " + fahrenheitToReamur(inputTemperature) + "\n"
                            + "Kelvin: " + fahrenheitToKelvin(inputTemperature);
                    break;
                case "Reamur":
                    resultText = "Celcius: " + reamurToCelcius(inputTemperature) + "\n"
                            + "Fahrenheit: " + reamurToFahrenheit(inputTemperature) + "\n"
                            + "Kelvin: " + reamurToKelvin(inputTemperature);
                    break;
                case "Kelvin":
                    resultText = "Celcius: " + kelvinToCelcius(inputTemperature) + "\n"
                            + "Fahrenheit: " + kelvinToFahrenheit(inputTemperature) + "\n"
                            + "Reamur: " + kelvinToReamur(inputTemperature);
                    break;
            }

            resultLabel.setText("<html>" + resultText.replace("\n", "<br>") + "</html>");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Input tidak valid! Masukkan angka yang benar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Rumus konversi suhu
    private double celciusToFahrenheit(double c) { return c * 9 / 5 + 32; }
    private double celciusToReamur(double c) { return c * 4 / 5; }
    private double celciusToKelvin(double c) { return c + 273.15; }
    private double fahrenheitToCelcius(double f) { return (f - 32) * 5 / 9; }
    private double fahrenheitToReamur(double f) { return (f - 32) * 4 / 9; }
    private double fahrenheitToKelvin(double f) { return (f - 32) * 5 / 9 + 273.15; }
    private double reamurToCelcius(double r) { return r * 5 / 4; }
    private double reamurToFahrenheit(double r) { return r * 9 / 4 + 32; }
    private double reamurToKelvin(double r) { return r * 5 / 4 + 273.15; }
    private double kelvinToCelcius(double k) { return k - 273.15; }
    private double kelvinToFahrenheit(double k) { return (k - 273.15) * 9 / 5 + 32; }
    private double kelvinToReamur(double k) { return (k - 273.15) * 4 / 5; }
}

