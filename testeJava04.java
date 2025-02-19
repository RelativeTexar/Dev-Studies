import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        // Write your code here 💖
        Scanner scanner = new Scanner(System.in);
        int alcool = 0;
        int gasolina = 0;
        int diesel = 0;

        while (true) {
            System.out.println("Digite o código do tipo de combustivel (1=alcool 2=gasolina 3=diesel 4=sair");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                System.out.println("voce escolheu alcol");
                alcool++;
            }
            else if (escolha == 2) {
                System.out.println("voce escolheu gasolina");
                gasolina++;
            }
            else if (escolha == 3) {
                System.out.println("voce escolheu diesel");
                diesel++;
            }
            else if (escolha == 4) {
                break;
            }
        
        }
        System.out.println("Obrigado por comprar aqui.");
        System.out.println("quantidade de gasolina:" + gasolina +  "quantidade de alcool" + alcool + "quantidade de diesel" + diesel);
        }
    }

