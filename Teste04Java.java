import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numero;
        System.out.println("Escreva um número:");
        numero = scanner.nextInt();

        boolean ehPrimo = true;

        if (numero <= 1) {
            ehPrimo = false; // Números menores ou iguais a 1 não são primos
        } else {
            for (int i = 2; i <= Math.sqrt(numero); i++) {
                if (numero % i == 0) {
                    ehPrimo = false; // Se for divisível por algum número, não é primo
                    break;
                }
            }
        }

        if (ehPrimo) {
            System.out.println(numero + " é primo.");
        } else {
            System.out.println(numero + " não é primo.");
        }
    }
}
