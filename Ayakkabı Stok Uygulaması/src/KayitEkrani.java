import javax.swing.*;
import java.awt.*;
import java.io.*;

public class KayitEkrani extends JFrame {
    private JTextField txtYeniAd;
    private JPasswordField txtYeniSifre;

    public KayitEkrani() {
        setTitle("Yeni Kullanıcı Kaydı");
        setSize(400, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(0, 215, 215)); 

        JLabel lblTitle = new JLabel("KAYIT OL");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(150, 30, 100, 30);
        getContentPane().add(lblTitle);

        txtYeniAd = new JTextField();
        txtYeniAd.setBounds(150, 100, 180, 40);
        getContentPane().add(txtYeniAd);

        JLabel lblAd = new JLabel("Kullanıcı Adı:");
        lblAd.setForeground(Color.WHITE);
        lblAd.setBounds(50, 100, 100, 40);
        getContentPane().add(lblAd);

        txtYeniSifre = new JPasswordField();
        txtYeniSifre.setBounds(150, 160, 180, 40);
        getContentPane().add(txtYeniSifre);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setForeground(Color.WHITE);
        lblSifre.setBounds(50, 160, 100, 40);
        getContentPane().add(lblSifre);

        String[] roller = {"Müşteri", "Admin"};
        JComboBox<String> comboRol = new JComboBox<>(roller);
        comboRol.setBounds(150, 220, 180, 40);
        getContentPane().add(comboRol);

        JButton btnKaydet = new JButton("KAYDET");
        btnKaydet.setBounds(150, 300, 180, 50);
        btnKaydet.setBackground(new Color(41, 128, 185));
        btnKaydet.setForeground(Color.WHITE);
        getContentPane().add(btnKaydet);

        btnKaydet.addActionListener(e -> {
            String ad = txtYeniAd.getText().trim();
            String sifre = new String(txtYeniSifre.getPassword());
            String rol = (String) comboRol.getSelectedItem();

            if (rol.equals("admin")) {
                String kod = JOptionPane.showInputDialog("Admin Kodu:");
                if (!"1234".equals(kod)) {
                    JOptionPane.showMessageDialog(this, "Hatalı Kod! Müşteri yapıldınız.");
                    rol = "musteri";
                }
            }

            if (!ad.isEmpty() && !sifre.isEmpty()) {
                kaydet(ad, sifre, rol);
                JOptionPane.showMessageDialog(this, "Kayıt Başarılı!");
                dispose();
            }
        });
    }

    private void kaydet(String ad, String sifre, String rol) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true))) {
            bw.write(ad + "," + sifre + "," + rol);
            bw.newLine();
        } catch (IOException e) { }
    }
}