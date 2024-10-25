import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FifoQueueUserInteraction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> fila = new LinkedList<>();
        int escolha;

        do {
            System.out.println("\n--- Menu FIFO ---");
            System.out.println("1. Inserir elemento na fila");
            System.out.println("2. Remover elemento da fila");
            System.out.println("3. Mostrar estado atual da fila");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o número a ser inserido: ");
                    int numero = scanner.nextInt();
                    fila.add(numero);
                    System.out.println("Número " + numero + " inserido na fila.");
                    break;

                case 2:
                    if (fila.isEmpty()) {
                        System.out.println("A fila está vazia. Nada para remover.");
                    } else {
                        int removido = fila.poll();
                        System.out.println("Número " + removido + " removido da fila.");
                    }
                    break;

                case 3:
                    System.out.println("Estado atual da fila: " + fila);
                    break;

                case 4:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (escolha != 4);

        scanner.close();
    }
}
