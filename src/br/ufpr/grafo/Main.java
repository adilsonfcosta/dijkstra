package br.ufpr.grafo;
import java.util.Scanner;

/**
 *
 * @author ADILSON
 */
public class Main {
    private static final int S1 = 0;
    private static final int S2 = 1;
    private static final int S3 = 2;
    private static final int S4 = 3;
    private static final int S5 = 4;
    private static final int S6 = 5;

    private static int rota(String tipo, Scanner in) {
        while (true) {
            System.out.println(tipo + ":");
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Algoritmo de Dijkstra finalizado!");
                System.exit(0);
            }
            try {
                int no = Integer.parseInt(line);
                if (no >= 1 && no <= 6) return no-1;
            } catch (NumberFormatException e) {
            }
            System.out.println("Nó inválido, digite novamente.");
        }
    }


    public static void main(String[] args) {
        // Cria o grafo
        Grafo grafo = new Grafo(6);
        grafo.criaNo(S1, S2, 7);
        grafo.criaNo(S1, S3, 9);
        grafo.criaNo(S1, S6, 14);       
        grafo.criaNo(S2, S3, 10);
        grafo.criaNo(S2, S4, 15);        
        grafo.criaNo(S3, S4, 11);
        grafo.criaNo(S3, S6, 2);
        grafo.criaNo(S4, S5, 6);
        grafo.criaNo(S5, S6, 9);

        //Graphland Subway Terminal
        //-------------------------
        Scanner in = new Scanner(System.in);
        System.out.println("Algoritmo de Dijkstra para busca do caminho mais curto");
        System.out.println("-------------------------------------------------------");

        while (true) {
            System.out.println("Digite abaixo a Origem e o Destino da rota [1 a 6]. Deixe em branco para sair.");
            int origem = rota("Origem", in);
            int destino = rota("Destino", in);

            System.out.println("Rota mais curta: ");
            for (Integer no : grafo.caminho(origem, destino)) {
                System.out.print((no+1) + " -> ");
            }
            System.out.println("FIM");
        }
    }    
}
