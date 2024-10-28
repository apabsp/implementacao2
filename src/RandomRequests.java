import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRequests {

    private ArrayList<Integer> memoria; // Lista para simular a memória
    private int capacity; // Capacidade da memória
    private int pageFaults; // Contador de faltas de página
    private Random random; // Gerador de números aleatórios

    // Construtor
    public RandomRequests(int capacity) {
        this.capacity = capacity;
        this.memoria = new ArrayList<>(capacity);
        this.pageFaults = 0;
        this.random = new Random();
    }

    // Método para acessar uma página
    public void acessarPagina(int pagina) {
        if (memoria.contains(pagina)) {
            System.out.println("Página " + pagina + " já está na memória.");
        } else {
            if (memoria.size() < capacity) {
                memoria.add(pagina);
                System.out.println("Página " + pagina + " carregada na memória.");
            } else {
                // Substituir uma página aleatoriamente
                int indiceAleatorio = random.nextInt(capacity);
                int paginaRemovida = memoria.get(indiceAleatorio);
                memoria.set(indiceAleatorio, pagina);
                System.out.println("Página " + paginaRemovida + " substituída por " + pagina + ".");
            }
            pageFaults++;
        }
    }

    // Exibir o estado atual da memória
    public void mostrarMemoria() {
        System.out.println("Estado atual da memória: " + memoria);
    }

    // Retornar o total de faltas de página
    public int getPageFaults() {
        return pageFaults;
    }

    // Método principal para testar o funcionamento
    public static void main(String[] args) {
        System.out.println("Esse programa vai adicionar páginas a uma lista.");
        System.out.println("Se a página já foi adicionada, não ocorre falta de página.");
        System.out.println("Se não foi adicionada, ocorre uma falta de página.\n");

        RandomRequests gerenciador = new RandomRequests(3); // Capacidade da memória é 3

        // Simulação de acesso a páginas com números aleatórios entre 0 e 5
        int min = 0;
        int max = 5;
        int[] paginasAcessadas = new int[10]; // Array de 10 páginas para testar

        // Preencher o array com páginas aleatórias
        for (int i = 0; i < paginasAcessadas.length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            paginasAcessadas[i] = randomNum;
        }

        // Acessar as páginas simuladas
        for (int pagina : paginasAcessadas) {
            gerenciador.acessarPagina(pagina);
            gerenciador.mostrarMemoria();
        }

        // Exibir o total de faltas de página
        System.out.println("Total de faltas de página: " + gerenciador.getPageFaults());
    }
}
