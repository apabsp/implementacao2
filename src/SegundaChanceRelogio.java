import java.util.ArrayList;
//Basicamente um FIFO só que com second chanmce

public class SegundaChanceRelogio {

    private class Pagina {
        int numero;
        boolean bitReferencia;

        Pagina(int numero) {
            this.numero = numero;
            this.bitReferencia = true; // Página recebe "segunda chance" ao ser carregada
        }
    }

    private ArrayList<Pagina> memoria;
    private int capacity;
    private int pageFaults;
    private int ponteiro;


    public SegundaChanceRelogio(int capacity) {
        this.capacity = capacity;
        this.memoria = new ArrayList<>(capacity);
        this.pageFaults = 0;
        this.ponteiro = 0;
    }


    public void acessarPagina(int numeroPagina) {
        Pagina paginaExistente = buscarPagina(numeroPagina);

        if (paginaExistente != null) {
            System.out.println("Página " + numeroPagina + " já está na memória.");
            paginaExistente.bitReferencia = true;
        } else {
            System.out.println("Falta de página: " + numeroPagina);
            if (memoria.size() < capacity) {
                memoria.add(new Pagina(numeroPagina));
            } else {
                substituirPagina(numeroPagina);
            }
            pageFaults++;
        }
        mostrarMemoria();
    }


    private Pagina buscarPagina(int numeroPagina) {
        for (Pagina pagina : memoria) {
            if (pagina.numero == numeroPagina) {
                return pagina;
            }
        }
        return null;
    }

    // Método para substituir uma página usando o algoritmo de segunda chance
    private void substituirPagina(int numeroPagina) {
        while (true) {
            Pagina paginaAtual = memoria.get(ponteiro);

            if (!paginaAtual.bitReferencia) {
                System.out.println("Página " + paginaAtual.numero + " substituída por " + numeroPagina + ".");
                memoria.set(ponteiro, new Pagina(numeroPagina)); // Substitui a página
                ponteiro = (ponteiro + 1) % capacity; // Avança o ponteiro circularmente
                break;
            } else {
                // Página recebe segunda chance, zera o bit e avança o ponteiro
                paginaAtual.bitReferencia = false;
                ponteiro = (ponteiro + 1) % capacity;
            }
        }
    }

    // Exibir o estado atual da memória
    public void mostrarMemoria() {
        System.out.print("Estado atual da memória: [");
        for (Pagina pagina : memoria) {
            System.out.print(pagina.numero + " ");
        }
        System.out.println("]");
    }

    // Retornar o total de faltas de página
    public int getPageFaults() {
        return pageFaults;
    }

    // Método principal para testar o funcionamento
    public static void main(String[] args) {
        System.out.println("Simulação do Algoritmo de Segunda Chance (Relógio)\n");

        SegundaChanceRelogio gerenciador = new SegundaChanceRelogio(3); // Capacidade da memória é 3

        // Simulação de acesso a páginas
        int[] paginasAcessadas = {1, 2, 3, 4, 1, 5, 1, 6};

        for (int pagina : paginasAcessadas) {
            gerenciador.acessarPagina(pagina);
        }

        System.out.println("\nTotal de faltas de página: " + gerenciador.getPageFaults());
    }
}
