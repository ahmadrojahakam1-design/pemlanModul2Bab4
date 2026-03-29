package pemlanModul2Bab4;

import java.util.ArrayList;
import java.util.Scanner;

public class mainSwalayan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<akun> daftarAkun = new ArrayList<>();

        daftarAkun.add(new akun("Hakam", "3812345610", 2000000, "1111"));
        daftarAkun.add(new akun("Satria", "5678901220", 5000000, "2222"));
        daftarAkun.add(new akun("Ayes", "7455667760", 1500000, "3333"));

        while (true) {
            System.out.print("Masukkan Nomor Akun: ");
            String nomorInput = in.nextLine();

            akun userAktif = null;
            for (akun aktif : daftarAkun) {
                if (aktif.getNomer().equals(nomorInput)) {
                    userAktif = aktif;
                    break;
                }
            }

            if (userAktif == null) {
                System.out.println("Akun tidak ditemukan!");
                continue;
            }

            if (userAktif.getdiBlokir()) {
                userAktif.login(""); 
                continue;
            }

            boolean loginBerhasil = false;
            while (!loginBerhasil && !userAktif.getdiBlokir()) {
                System.out.print("Masukkan PIN: ");
                String pinInput = in.nextLine();
                loginBerhasil = userAktif.login(pinInput);

                if (userAktif.getdiBlokir()) 
                break;
            }

            if (loginBerhasil) {
                int pilihan = 0;
                while (pilihan != 3) {
                    System.out.println("~~Transaksi Menu~~");
                    System.out.println("1. Belanja");
                    System.out.println("2. Top Up");
                    System.out.println("3. Keluar");
                    System.out.print("Pilih Menu: ");
                            
                    pilihan = in.nextInt();
                    in.nextLine(); 
                            
                    switch (pilihan) {
                        case 1:
                            System.out.print("Nominal Belanja: ");
                            double belanja = in.nextDouble();
                            in.nextLine();
                            userAktif.belanja(belanja);
                            break;
                        case 2:
                            System.out.print("Nominal Top Up: ");
                            double topup = in.nextDouble();
                            in.nextLine(); 
                            userAktif.topUp(topup);
                            break;
                        case 3:
                            System.out.println("Makasih Yah.");
                            break;
                        default:
                            System.out.println("Salah Pilihan!");
                    }
                }
            }
        }
    }
}