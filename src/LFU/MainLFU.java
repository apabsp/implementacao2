package LFU;

public class MainLFU {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(5); // Cria cache com capacidade 5

        // Adiciona 10 elementos (o cache só pode guardar 5)
        for (int i = 1; i <= 10; i++) {
            cache.put(i, i * 10); // Exemplo: Chave 1 -> Valor 10, Chave 2 -> Valor 20, etc.
            System.out.println("Inserido: Chave " + i + ", Valor " + (i * 10));
        }

        // Acessa algumas chaves para mudar a frequência
        for (int i = 1; i <= 5; i++) {
            System.out.println("Valor da Chave " + i + ": " + cache.get(i));
        }

        // Exibe o estado final do cache
        System.out.println("\nEstado Final do Cache:");
        cache.displayCache();
    }
}
