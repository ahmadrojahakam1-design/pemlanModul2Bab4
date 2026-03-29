package pemlanModul2Bab4;

import java.util.ArrayList;
import java.util.Scanner;

public class mainSwalayan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        ArrayList<akun> daftarUser = new ArrayList<>();
        daftarUser.add(new akun("Ayes", "3812349021", 4000000, "4321"));
        daftarUser.add(new akun("Satria", "5656569090", 1500000, "1234"));
        daftarUser.add(new akun("Udin", "7489721236", 2000000, "7777"));

        System.out.println("Datang Selamat di Swalayan");
        System.out.print("Masukkan Nomor Pelanggan: ");
        String nomerInput = in.nextLine();

        akun userAktif = null;
        for (akun user : daftarUser) {
            if (user.getNomer().equals(nomerInput)) {
                userAktif = user;
                break;
            }
        }

        if (userAktif != null) {
            System.out.println("Nomornya Cocok!");
            boolean loginSuccess = false;

            while (!loginSuccess && !userAktif.getdiBlokir()) {
                System.out.print("Masukkan PIN: ");
                String pinInput = in.nextLine();
                
                if (userAktif.login(pinInput)) {
                    loginSuccess = true;
                    System.out.println("Login Berhasil!");
                } else if (userAktif.getdiBlokir()) {
                    break; 
                }
            }

            if (loginSuccess) {
                int pilihan = 0;
                while (pilihan != 3) {
                    System.out.println("\n~~Transaksi Menu~~");
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
                            userAktif.belanja(belanja);
                            break;
                        case 2:
                            System.out.print("Nominal Top Up: ");
                            double topup = in.nextDouble();
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
        } else {
            System.out.println("Error nomor '" + nomerInput + "' tidak ditemukan!");
        }

        in.close();
    }
}