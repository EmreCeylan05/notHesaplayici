import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
public class notHesaplayici {
    public static float ortalamaHesaplayici(int tur,float notlar,float notlar2){
        if(tur==1){
            return (float) (notlar*0.4+notlar2*0.6) ;
        }
        else{
            return (float) (notlar*0.3+notlar2*0.7);
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        String report = "karne.txt";
        System.out.println("Vize final orani seciniz\n1)V: 0.4 / F: 0.6\n2)V: 0.3 / F:0.7");
        int tip=scanner.nextInt();
        try{
            if(tip!=1&&tip!=2){
                scanner.close();
                throw new RuntimeException("Vize/Final turu 1 veya 2 olabilir!");
            }
        }
        catch(RuntimeException e){
            System.out.println("Hata yakalandi: " + e.getMessage());
        }
        int[] kademeler = new int[9];
        String[] kademeHarf = {"AA","BA","BB","CB","CC","DC","DD","FD","FF"};
        System.out.println("Bulunan dizinde hazir olarak 'kademeler.txt' varsa 'var' yoksa 'yok' yaziniz->:");
        String yoklama="";
        try{
            scanner.nextLine();
            yoklama = scanner.nextLine();
            if (!yoklama.equals("var") && !yoklama.equals("yok")){
                scanner.close();
                throw new RuntimeException("Beklenmeyen giris!");
            }
        }
        catch(RuntimeException e){
            System.out.println("Hata yakalandi: "+ e.getMessage());
        }
        if (yoklama.equals("var")){
            String dosyaYolu = "kademeler.txt"; // Okunacak dosyanın yolu
            try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
                for (int i = 0; i < 8; i++) {
                    kademeler[i] = Integer.parseInt(br.readLine());
                }
                System.out.println("KADEMELER\n");
                for (int i=0;i<8;i++) {
                    System.out.println(kademeHarf[i]+"\t"+kademeler[i]);
                }
            }
            catch (IOException e) {
                System.out.println("Dosya okuma hatasi: " + e.getMessage());
                return;
            }
        }
        else{//dosya bulunamaması durumunda !
            for(int i=0;i<8;i++){
                System.out.println(kademeHarf[i]+" icin min notu giriniz->: ");
                kademeler[i]=scanner.nextInt();
            }
        }
        kademeler[8]=0;
        System.out.println("Dersten gecmek icin gereken min harf CC=> "+kademeler[4]+" olacaktir\nFF = >0 & F = 0 Olarak ayarlandi");
        int gecmeNotu = kademeler[4];
        System.out.println("\nDers adetini giriniz->: ");
        int dersAdet = scanner.nextInt();
        int[] katsayilar = new int[dersAdet];
        String[] dersler = new String[dersAdet];
        String[] alinanHarfler = new String[dersAdet];
        float[][] notlar = new float[dersAdet][3];
        float[] dortluk = new float[dersAdet];
        float GenelOrt=0;       
        for(int i=0;i<dersAdet;i++){
            System.out.println("\nDersin adini giriniz->: ");
            scanner.nextLine();
            dersler[i]=scanner.nextLine();
            System.out.println("\nDersin katsayisini giriniz->: ");
            katsayilar[i]=scanner.nextInt();
            System.out.print("\nSirasiyla vizenizi ardindan da finalinizi giriniz\nvize->: ");
            notlar[i][0]=scanner.nextInt();
            System.out.print("\nFinal->: ");
            notlar[i][1]=scanner.nextInt();
            notlar[i][2]=ortalamaHesaplayici(tip, notlar[i][0], notlar[i][1]);
        }
        System.out.print("\nKARNENIZ\nDERS\tVize\tFinal\tOrt\tStatus\tHarf\t4'luk\n");
        for(int i=0;i<dersAdet;i++){
            System.out.print("\n"+dersler[i]+"\t"+notlar[i][0]+"\t"+notlar[i][1]+"\t"+notlar[i][2]+"\t");
            if(notlar[i][2]>=gecmeNotu){
                System.out.print("PASS");
            }
            else{
                System.out.print("FAIL");
            }
            if(notlar[i][2]>=kademeler[0]){
                dortluk[i]=4;
                alinanHarfler[i]="AA";
                System.out.print("\tAA\t"+dortluk[i]);
            }
            else if (notlar[i][2]>=kademeler[1]){
                dortluk[i]=(float) 3.5;
                alinanHarfler[i]="BA";
                System.out.print("\tBA\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[2]){
                dortluk[i]=3;
                alinanHarfler[i]="BB";
                System.out.print("\tBB\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[3]){
                dortluk[i]=(float) 2.5;
                alinanHarfler[i]="CB";
                System.out.print("\tCB\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[4]){
                dortluk[i]=2;
                alinanHarfler[i]="CC";
                System.out.print("\tCC\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[5]){
                dortluk[i]=(float)1.5;
                alinanHarfler[i]="DC";
                System.out.print("\tDC\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[6]){
                dortluk[i]=1;
                alinanHarfler[i]="DD";
                System.out.print("\tDD\t"+dortluk[i]);
            }
            else if(notlar[i][2]>=kademeler[7]){
                dortluk[i]=(float)0.5;
                alinanHarfler[i]="FD";
                System.out.print("\tFD\t"+dortluk[i]);
            }
            else{
                dortluk[i]=0;
                if(notlar[i][2]>kademeler[8]){
                    alinanHarfler[i]="FF";
                    System.out.print("\tFF\t"+dortluk[i]);
                }
                else{
                    alinanHarfler[i]="F";
                    System.out.print("\tF\t"+dortluk[i]);
                }
            }
            GenelOrt+=katsayilar[i]*dortluk[i];
        }
        double x=0;
        for(int j=0;j<dersAdet;j++){
            x+=katsayilar[j];
        }
        GenelOrt/=x;
        System.out.println("\nDönem ortalamaniz: " + GenelOrt);
        try (FileWriter writer = new FileWriter(report)) {
            writer.write("KARNENIZ\nDERS\tVize\tFinal\tOrt\tStatus\tHarf\t4'luk\n");
            for(int i = 0; i < dersAdet; i++) {
                writer.write(dersler[i] + "\t" + notlar[i][0] + "\t" + notlar[i][1] + "\t" + notlar[i][2] + "\t");
                if(notlar[i][2] >= gecmeNotu) {
                    writer.write("PASS");
                }
                else {
                    writer.write("FAIL");
                }
                writer.write("\t"+alinanHarfler[i]+"\t"+dortluk[i]);
                writer.write("\n");
            }
            writer.write("\nDönem ortlamaniz: " + GenelOrt);
            writer.close();
            System.out.println("Karneniz " + report+ " dosyasina yazildi.");
        }
        catch(IOException e){
            System.out.println("Dosya yazma hatasi: " + e.getMessage());
        }
        scanner.close();
    }
}