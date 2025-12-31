import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GirisEkrani {
    private static JPasswordField textSifre;
    private static JTextField textKullanici;

    public static void main(String[] args) {
        JFrame jf = new JFrame("ShoeStock - Giriş");
        jf.setSize(500, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);
        jf.getContentPane().setBackground(new Color(0, 215, 215));
        

        JLabel lblLogo = new JLabel("");
        lblLogo.setBackground(new Color(255, 140, 85));
        lblLogo.setBounds(95, 30, 302, 172);
        
        try {
            ImageIcon icon = new ImageIcon(GirisEkrani.class.getResource("/logo.png"));
            Image img = icon.getImage().getScaledInstance(302, 172, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblLogo.setText("Logo Bulunamadı (logo.png)");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        jf.getContentPane().add(lblLogo);

  
        JLabel lblBaslik = new JLabel("ShoeStock Uygulaması");
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
        lblBaslik.setBounds(60, 213, 381, 56);
        jf.getContentPane().add(lblBaslik);

 
        JLabel lblKullanici = new JLabel("Kullanıcı Adı:");
        lblKullanici.setForeground(Color.WHITE);
        lblKullanici.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKullanici.setBounds(29, 298, 185, 56);
        jf.getContentPane().add(lblKullanici);

        textKullanici = new JTextField();
        textKullanici.setBackground(new Color(212, 212, 212));
        textKullanici.setBounds(270, 298, 185, 56);
        textKullanici.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jf.getContentPane().add(textKullanici);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setForeground(Color.WHITE);
        lblSifre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSifre.setBounds(29, 383, 185, 56);
        jf.getContentPane().add(lblSifre);

        textSifre = new JPasswordField();
        textSifre.setBackground(new Color(212, 212, 212));
        textSifre.setBounds(270, 383, 185, 56);
        jf.getContentPane().add(textSifre);

   
        JButton butonGiris = new JButton("Giriş Yap");
        butonGiris.setBounds(44, 479, 185, 56);
        butonGiris.setBackground(new Color(155, 89, 182)); 
        butonGiris.setForeground(Color.WHITE);
        butonGiris.setOpaque(true);
        butonGiris.setContentAreaFilled(true);
        butonGiris.setBorderPainted(false);
        butonGiris.setFont(new Font("Tahoma", Font.BOLD, 14));
        jf.getContentPane().add(butonGiris);

        JButton butonKaydol = new JButton("Kayıt Ol");
        butonKaydol.setBounds(270, 479, 185, 56);
        butonKaydol.setBackground(new Color(41, 128, 185)); 
        butonKaydol.setForeground(Color.WHITE);
        butonKaydol.setOpaque(true);
        butonKaydol.setContentAreaFilled(true);
        butonKaydol.setBorderPainted(false);
        butonKaydol.setFont(new Font("Tahoma", Font.BOLD, 14));
        jf.getContentPane().add(butonKaydol);

      
        butonGiris.addActionListener(e -> {
            String ad = textKullanici.getText().trim();
            String sifre = new String(textSifre.getPassword());
            String rol = girisKontrol(ad, sifre);

            if (rol != null) {
                JOptionPane.showMessageDialog(null, "Giriş Başarılı! Hoş geldiniz " + ad);
                jf.dispose();
                
               
                if (rol.equalsIgnoreCase("musteri") || rol.equalsIgnoreCase("müşteri")) {
                    new MarkaPaneli(ad);
                } else {
                
                    JOptionPane.showMessageDialog(null, "Admin paneli hazır değil!");
                    new MarkaPaneli(ad);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hatalı Giriş!");
            }
        });

        butonKaydol.addActionListener(e -> {
            try {
                new KayitEkrani().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "KayitEkrani sınıfı bulunamadı!");
            }
        });

        jf.setVisible(true);
    }

    public static String girisKontrol(String ad, String sifre) {
        File dosya = new File("user.txt");
        if (!dosya.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parca = satir.split(",");
                if (parca.length == 3 && parca[0].equals(ad) && parca[1].equals(sifre)) return parca[2];
            }
        } catch (IOException e) { }
        return null;
    }
}