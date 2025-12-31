import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UrunListesiPaneli extends JFrame {
    private String kullaniciAdi;
    private String marka;
    private String kategori;
    
    public UrunListesiPaneli(String kullaniciAdi, String marka, String kategori) {
        this.kullaniciAdi = kullaniciAdi;
        this.marka = marka;
        this.kategori = kategori;
        
        setTitle("ShoeStock - " + marka.toUpperCase() + " > " + kategori.toUpperCase());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 215, 215));
        
      
        JPanel ustPanel = new JPanel(new BorderLayout());
        ustPanel.setBackground(new Color(0, 215, 215));
        ustPanel.setPreferredSize(new Dimension(1000, 80));
        
        JButton btnGeri = new JButton("← Geri");
        btnGeri.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGeri.setBackground(new Color(41, 128, 185));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setFocusPainted(false);
        btnGeri.setBorderPainted(false);
        btnGeri.setPreferredSize(new Dimension(100, 40));
        btnGeri.addActionListener(e -> {
            dispose();
            new KatagoriPaneli(kullaniciAdi, marka);
        });
        
        JLabel lblBaslik = new JLabel(marka.toUpperCase() + " - " + kategori.toUpperCase());
        lblBaslik.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
        
        ustPanel.add(btnGeri, BorderLayout.WEST);
        ustPanel.add(lblBaslik, BorderLayout.CENTER);
        ustPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(ustPanel, BorderLayout.NORTH);
        
       
        JPanel ortaPanel = new JPanel();
        ortaPanel.setBackground(new Color(0, 215, 215));
        ortaPanel.setLayout(new GridLayout(1, 3, 20, 20));
        ortaPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        ArrayList<Product> urunler = ProductManager.kategoriUrunleriGetir(marka, kategori);
        
       
        for (int i = 0; i < Math.min(urunler.size(), 3); i++) {
            Product urun = urunler.get(i);
            JPanel urunKart = urunKartiOlustur(urun);
            ortaPanel.add(urunKart);
        }
        
        add(ortaPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    
    private JPanel urunKartiOlustur(Product urun) {
        JPanel kart = new JPanel();
        kart.setLayout(new BorderLayout());
        kart.setBackground(Color.WHITE);
        kart.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
       
        JPanel bilgiPanel = new JPanel();
        bilgiPanel.setLayout(new BoxLayout(bilgiPanel, BoxLayout.Y_AXIS));
        bilgiPanel.setBackground(Color.WHITE);
        bilgiPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        
        JLabel lblModel = new JLabel(urun.getModel());
        lblModel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblModel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
       
        JLabel lblMarka = new JLabel(urun.getMarka());
        lblMarka.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMarka.setForeground(Color.GRAY);
        lblMarka.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel lblKategori = new JLabel("Kategori: " + urun.getKategori());
        lblKategori.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblKategori.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel lblNumara = new JLabel("Numara: " + urun.getNumara());
        lblNumara.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNumara.setForeground(new Color(155, 89, 182));
        lblNumara.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel lblFiyat = new JLabel(String.format("%.2f TL", urun.getFiyat()));
        lblFiyat.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblFiyat.setForeground(new Color(46, 204, 113));
        lblFiyat.setAlignmentX(Component.CENTER_ALIGNMENT);
        
       
        JLabel lblStok = new JLabel();
        lblStok.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStok.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if (urun.getStok() == 0) {
            lblStok.setText(" STOKTA YOK!");
            lblStok.setForeground(Color.RED);
        } else if (urun.getStok() < 10) {
            lblStok.setText(" KRİTİK STOK: " + urun.getStok() + " adet!");
            lblStok.setForeground(new Color(255, 140, 0)); 
        } else {
            lblStok.setText(urun.getStok() + " adet");
            lblStok.setForeground(new Color(46, 204, 113));
        }
        
        
        bilgiPanel.add(lblModel);
        bilgiPanel.add(Box.createVerticalStrut(8));
        bilgiPanel.add(lblMarka);
        bilgiPanel.add(Box.createVerticalStrut(12));
        bilgiPanel.add(lblKategori);
        bilgiPanel.add(Box.createVerticalStrut(8));
        bilgiPanel.add(lblNumara);
        bilgiPanel.add(Box.createVerticalStrut(12));
        bilgiPanel.add(lblFiyat);
        bilgiPanel.add(Box.createVerticalStrut(8));
        bilgiPanel.add(lblStok);
        
        kart.add(bilgiPanel, BorderLayout.CENTER);
        
       
        JPanel butonPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        butonPanel.setBackground(Color.WHITE);
        butonPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
    
        JButton btnDetay = new JButton(" Detayları Gör");
        btnDetay.setBackground(new Color(41, 128, 185));
        btnDetay.setForeground(Color.WHITE);
        btnDetay.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDetay.setFocusPainted(false);
        btnDetay.setBorderPainted(false);
        btnDetay.addActionListener(e -> {
            new UrunDetayDialog(this, urun, kullaniciAdi).setVisible(true);
        });
        

        JButton btnSatinAl = new JButton(" Satın Al");
        btnSatinAl.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSatinAl.setFocusPainted(false);
        btnSatinAl.setBorderPainted(false);
        
        if (urun.getStok() == 0) {
            btnSatinAl.setText(" Stokta Yok");
            btnSatinAl.setEnabled(false);
            btnSatinAl.setBackground(Color.GRAY);
            btnSatinAl.setForeground(Color.WHITE);
        } else {
            btnSatinAl.setBackground(new Color(155, 89, 182));
            btnSatinAl.setForeground(Color.WHITE);
            btnSatinAl.addActionListener(e -> {
                satinAlIslem(urun);
            });
        }
        
        butonPanel.add(btnDetay);
        butonPanel.add(btnSatinAl);
        
        kart.add(butonPanel, BorderLayout.SOUTH);
        
        return kart;
    }
    
    
    private void satinAlIslem(Product urun) {
        if (urun.getStok() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Bu ürün stokta yok!", 
                "Uyarı", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
       
        String[] secenekler = new String[Math.min(urun.getStok(), 5)];
        for (int i = 0; i < secenekler.length; i++) {
            secenekler[i] = String.valueOf(i + 1);
        }
        
        String secim = (String) JOptionPane.showInputDialog(
            this,
            "Kaç adet almak istiyorsunuz?",
            "Adet Seçimi",
            JOptionPane.QUESTION_MESSAGE,
            null,
            secenekler,
            secenekler[0]
        );
        
        if (secim != null) {
            int adet = Integer.parseInt(secim);
            double toplam = urun.getFiyat() * adet;
            
           
            int onay = JOptionPane.showConfirmDialog(
                this,
                String.format("Ürün: %s\nAdet: %d\nToplam Tutar: %.2f TL\n\nOnaylıyor musunuz?", 
                    urun.getModel(), adet, toplam),
                "Satın Alma Onayı",
                JOptionPane.YES_NO_OPTION
            );
            
            if (onay == JOptionPane.YES_OPTION) {
                ProductManager.siparisKaydet(kullaniciAdi, urun, adet);
                JOptionPane.showMessageDialog(this, 
                    " Sipariş başarıyla oluşturuldu!", 
                    "Başarılı", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new UrunListesiPaneli(kullaniciAdi, marka, kategori); 
            }
        }
    }
}