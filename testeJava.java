/*Faça um programa para ler dois valores inteiros, e depois mostrar na tela a soma desses números com uma
mensagem explicativa, conforme exemplos.*/
import java.util.Scanner; 

public class HelloWorld {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double a, b;
        Double soma;

        System.out.print("Digite o primeiro valor");
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        soma = a + b;
        System.out.print("A soma dos dois números é:" + soma);
        

    }
}
