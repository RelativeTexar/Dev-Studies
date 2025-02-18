import java.util.Scanner;
public class HelloWorld {
    public static void main(String[] args) {
        // Write your code here 💖
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Escreva a sua nota, vou te falar se voce passou ou não!");
        double nota = scanner.nextDouble();

        if (nota < 6) {
            System.out.print("Reprovado");
        }
        else if (nota == 6) {
            System.out.print("Passou, mas foi raspando!");
        }
        else if (nota > 6) {
            System.out.print("Voce foi otimo! Passou de ano.");
        }


    }
}
