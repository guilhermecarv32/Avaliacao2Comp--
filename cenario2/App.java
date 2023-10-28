package cenario2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import com.github.javafaker.Faker;

public class App {
    private static final int TOTAL_DE_PARTIDAS = 10;
    private static final int TOTAL_DE_LEITURAS = 3;

    private static List<Thread> executores = new ArrayList<>();
    
    public static String generateRandomNome(Faker faker) {
        String[] nomes = {"1", "2", "3"};
        int index = faker.random().nextInt(nomes.length);
        return nomes[index];
    }
    
    public static void iniciarProcessamentoDeLeituras(Map<Partida, SensoresImpl> partidas) {
    for (Entry<Partida, SensoresImpl> item : partidas.entrySet()) {
        Partida partida = item.getKey();
        SensoresImpl sensores = item.getValue();

        Thread executor = new Thread(new ExecutorImpl(partida, sensores, null, TOTAL_DE_LEITURAS));
        executores.add(executor);
    }
}


    public static void esperarFinalizacaoDeProcessamento() throws InterruptedException {
        for (Thread executor : executores) {
            executor.join();
        }
    }

    public static Map<Partida, SensoresImpl> gerarPartidas() {
    Faker faker = new Faker();
    List<Chute> media = new ArrayList<>();
    media.add(new Chute(80, 34, 50));
    media.add(new Chute(82, 32, 52));
    media.add(new Chute(84, 30, 48));
    media.add(new Chute(85, 28, 54));

    Map<Partida, SensoresImpl> partidas = new TreeMap<>();
    for (int i = 0; i < TOTAL_DE_PARTIDAS; i++) {
        String nome = generateRandomNome(faker);
        Partida partida = new Partida(nome);

        partidas.put(partida, new SensoresImpl());
    }

    return partidas;
}


    public static void main(String[] args) throws Exception {
    Map<Partida, SensoresImpl> partidas = gerarPartidas();

    System.out.println("Iniciando o processamento");
    iniciarProcessamentoDeLeituras(partidas);

    for (Thread executor : executores) {
        executor.start();
    }

    for (Thread executor : executores) {
        executor.join();
    }

    System.out.println("Processamento finalizado");
}

}