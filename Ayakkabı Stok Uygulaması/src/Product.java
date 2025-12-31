public class Product {
    private String kod;
    private String marka;
    private String kategori;
    private String model;
    private int numara;
    private int stok;
    private double fiyat;
    private String aciklama;

    public Product(String kod, String marka, String kategori, String model, 
                   int numara, int stok, double fiyat, String aciklama) {
        this.kod = kod;
        this.marka = marka;
        this.kategori = kategori;
        this.model = model;
        this.numara = numara;
        this.stok = stok;
        this.fiyat = fiyat;
        this.aciklama = aciklama;
    }

    
    public String getKod() { return kod; }
    public String getMarka() { return marka; }
    public String getKategori() { return kategori; }
    public String getModel() { return model; }
    public int getNumara() { return numara; }
    public int getStok() { return stok; }
    public double getFiyat() { return fiyat; }
    public String getAciklama() { return aciklama; }

    
    public void setStok(int stok) { this.stok = stok; }

    
    public String toString() {
        return kod + "," + marka + "," + kategori + "," + model + "," + 
               numara + "," + stok + "," + fiyat + "," + aciklama;
    }
}
