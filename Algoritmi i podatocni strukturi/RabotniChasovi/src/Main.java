import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }

    public int vkupnoCasovi(){
        int vkupno=0;
        for ( int chas: casovi ) {
            vkupno+=chas;
        }
        return vkupno;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for ( int chas: casovi ) {
            sb.append(chas + "   ");
        }
        return sb.toString();
    }

}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }



    public RabotnaNedela[] getNedeli() {
        return nedeli;
    }

    public String getIme() {
        return ime;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(ime + "   ");
        for ( RabotnaNedela nedela: nedeli ) {
            sb.append(nedela.vkupnoCasovi() + "   ");
        }
        return sb.toString();
    }

}

public class Main {

    public static int sumNedeli(Rabotnik r){
        int vkupno=0;
        for ( RabotnaNedela nedela: r.getNedeli() ) {
            vkupno+=nedela.vkupnoCasovi();
        }
        return vkupno;
    }
    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza){
        Rabotnik najvreden=niza[0];
        for ( int i=0; i<niza.length;i++) {
            if ( sumNedeli(najvreden) < sumNedeli(niza[i]) ) {
                najvreden=niza[i];
            }
        }
        return najvreden;
    }
    public static void table(Rabotnik [] niza) {
        System.out.println("Rab   1   2   3   4   Vkupno");
        for ( Rabotnik rabotnik: niza ) {
            System.out.println(rabotnik.toString() + sumNedeli(rabotnik));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            RabotnaNedela[] nedeli = new RabotnaNedela[4];
            input.nextLine();
            String ime = input.nextLine();
            for(int j=0;j<4;j++){
                int[] casovi=new int[5];
                for(int k=0;k<5;k++){
                    casovi[k]=input.nextInt();
                }
                nedeli[j]=new RabotnaNedela(casovi,j+1);
            }
            niza[i]=new Rabotnik(ime,nedeli);
        }

        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " +najvreden_rabotnik(niza).getIme());

    }
}

