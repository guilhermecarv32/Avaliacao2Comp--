package cenario2;

import java.util.List;

public class ExecutorImpl extends Executor {
    private Partida partida = null;
    private SensoresImpl sensores = null;
    private AnaliseDeMedias analise = new AnaliseDeMedias();

    public ExecutorImpl(Partida partida, SensoresImpl sensores, List<Chute> mediasChutes, int totalLeituras) {
        super(totalLeituras);

        this.partida = partida;
        this.sensores = sensores;
    }

    @Override
public void processarLeitura(int leituraAtual) {
    List<Chute> leituras = sensores.gerarLeituras(totalLeituras); // Gere as leituras uma vez

    for (int chuteNumero = 1; chuteNumero <= leituras.size(); chuteNumero++) {
        Chute leitura = leituras.get(chuteNumero - 1);
        partida.onLeitura(leitura);

        String informacoes = analise.atuar(partida.getLeituras());
        informacoes = informacoes.isEmpty() ? "Velocidade normal" : informacoes;
        informacoes += ": [Velocidade = " + leitura.getVelocidade() + " RPM = " + leitura.getRPM() + " Forca = " + leitura.getForca() + "]";

        System.out.println("Analise feita no chute #" + chuteNumero + " da partida " + partida.getId() + ": " + informacoes);
    }
}

}
