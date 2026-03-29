package pemlanModul2Bab4;

public class akun {
    private double saldo;
    private String nomer;
    private String nama;
    private String pin;
    private int gagal = 0;
    private boolean diBlokir = false;
    
    public akun (String nama, String nomer, double saldo, String pin) {
        this.nama = nama;
        this.nomer = nomer;
        this.saldo = saldo;
        this.pin = pin;
    }

    public String getNomer() {
        return this.nomer;
    }
    
    public boolean login(String inputPin) {
        
        if (diBlokir) {
            System.out.println("Akun " + this.nama + " telah diblokir.");
            return false;
        }
        
        if (this.pin.equals(inputPin)) {
            gagal = 0; 
            return true;
        } else {
            gagal++;
            System.out.println("PIN salah! Kesalahan ke-" + gagal);
            if (gagal >= 3) {
                diBlokir = true;
                System.out.println("Sabar yah kena blokir");
            }
            return false;
        }  
    }
    
    public void topUp(double jumlah) {
        this.saldo += jumlah;
        System.out.println("Top up berhasil. Saldo sekarang: Rp" + String.format("%,.0f", this.saldo));
    }

    public void belanja(double nominal) {
        if (nominal > this.saldo) {
            System.out.println("Minimal punya saldo!!!");
            return;
        }
        String kodeTipe = nomer.substring(0, 2);
        double cashback = 0;
        
        if (nominal > 1000000) {
            if (kodeTipe.equals("38")) cashback = 0.05 * nominal;
            else if (kodeTipe.equals("56")) cashback = 0.07 * nominal;
            else if (kodeTipe.equals("74")) cashback = 0.10 * nominal;
        } else {
            if (kodeTipe.equals("56")) cashback = 0.02 * nominal;
            else if (kodeTipe.equals("74")) cashback = 0.05 * nominal;
        }

        double saldoSetelah = this.saldo - nominal + cashback;
        if (saldoSetelah < 10000) {
            System.out.println("Minimal Saldo Rp. 10.000 bos!");
        } else {
            this.saldo = saldoSetelah;
            System.out.println("Transaksi BERHASIL!");
            System.out.println("Cashback: Rp" + String.format("%,.0f", cashback));
            System.out.println("Sisa Saldo: Rp" + String.format("%,.0f", this.saldo));
        }
    }

    public boolean getdiBlokir() { 
        return diBlokir; 
    }
}
