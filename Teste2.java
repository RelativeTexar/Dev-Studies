/*Fazer um programa para ler quatro valores inteiros A, B, C e D. A seguir, calcule e mostre a diferença do produto
de A e B pelo produto de C e D segundo a fórmula: DIFERENCA = (A * B - C * D)*/
import java.util.Scanner;
public class HelloWorld {
    public static void main(String[] args) {
        // Write your code here 💖
        Scanner scanner = new Scanner(System.in);
        int a, b, c, d;
        int diferenca;
        int multiAB;
        int multiCD;

        System.out.print("Escreva 4 numeros consecutivos");
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        multiAB = a * b;
        multiCD = c * d;
        diferenca = (multiAB - multiCD);
        
        System.out.print("A diferença do produto é" + diferenca);









    }
}
