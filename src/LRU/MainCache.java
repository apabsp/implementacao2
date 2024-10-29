package LRU;

import java.util.Scanner;

public class MainCache {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao LRU Cache!");
        System.out.print("Informe a capacidade do cache: ");
        int capacity = scanner.nextInt();

        LRUCache cache = new LRUCache(capacity);

        // Declare 'key' e 'value' fora do switch
        int key;
        int value;

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar um valor (put)");
            System.out.println("2. Consultar um valor (get)");
            System.out.println("3. Remover uma chave");
            System.out.println("4. Sair");
            System.out.print("Sua opção: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Informe a chave: ");
                    key = scanner.nextInt();
                    System.out.print("Informe o valor: ");
                    value = scanner.nextInt();
                    cache.put(key, value);
                    System.out.println("Valor adicionado!");
                    break;

                case 2:
                    System.out.print("Informe a chave para consulta: ");
                    key = scanner.nextInt();
                    int result = cache.get(key);
                    if (result == -1) {
                        System.out.println("Chave não encontrada no cache.");
                    } else {
                        System.out.println("Valor correspondente: " + result);
                    }
                    break;

                case 3:
                    System.out.print("Informe a chave a ser removida: ");
                    key = scanner.nextInt();
                    cache.remove(key);
                    break;

                case 4:
                    System.out.println("Encerrando o programa. Até mais!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
