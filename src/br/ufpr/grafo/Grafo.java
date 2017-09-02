package br.ufpr.grafo;
import java.util.*;

/**
 *
 * @author ADILSON
 */
public class Grafo {
    private static final int UNDEFINED = -1;
    private int vertices[][];

    public Grafo(int numVertices) {
        vertices = new int[numVertices][numVertices];
    }

    public void criaNo(int vert1, int vert2, int distancia) {
        vertices[vert1][vert2] = distancia;
        vertices[vert2][vert1] = distancia;
    }

    public void removeNo(int vert1, int vert2) {
        vertices[vert1][vert2] = 0;
        vertices[vert2][vert1] = 0;
    }

    public int getCusto(int vert1, int vert2) {
        return vertices[vert1][vert2];
    }

    // Retorna a lista de nós adjacentes ao nó atual
    public List<Integer> getAdjacentes(int vertice) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int i = 0; i < vertices[vertice].length; i++)
            if (vertices[vertice][i] > 0) {
                adjacentes.add(i);
            }
        return adjacentes;
    }

   // Retorno do caminho
    public List<Integer> caminho(int vertInicial, int vertFinal) {

        //Declara vetores e tabela hash
        int custo[] = new int[vertices.length];
        int antecessor[] = new int[vertices.length];
        Set<Integer> naoVisitado = new HashSet<>();

        //Nó inicial tem custo zero
        custo[vertInicial] = 0;

        //Todos os outros nós recebem custo máximo e antecessor indefinido
        for (int v = 0; v < vertices.length; v++) {
            if (v != vertInicial) {
                custo[v] = Integer.MAX_VALUE;
            }
            antecessor[v] = UNDEFINED;
            naoVisitado.add(v);
        }

        //Faz a busca no grafo
        //------------
        while (!naoVisitado.isEmpty()) {
            int perto = maisPerto(custo, naoVisitado);
            naoVisitado.remove(perto);

            for (Integer adjacente : getAdjacentes(perto)) {
                int custoTotal = custo[perto] + getCusto(perto, adjacente);
                if (custoTotal < custo[adjacente]) {
                    custo[adjacente] = custoTotal;
                    antecessor[adjacente] = perto;
                }
            }
            //Se encontrar, cria o caminho
            if (perto == vertFinal) {
                return criaCaminhoMaisCurto(antecessor, perto);
            }
        }

        //Se não encontrar, retorna a lista vazia
        return Collections.emptyList();
    }

    // Verifica qual nó adjacente está mais perto
    private int maisPerto(int[] dist, Set<Integer> naoVisitado) {
        double minDist = Integer.MAX_VALUE;
        int minIndex = 0;
        for (Integer i : naoVisitado) {
            if (dist[i] < minDist) {
                minDist = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Cria lista com o caminho mais curto da origem ao destino
    private List<Integer> criaCaminhoMaisCurto(int[] antecessor, int perto) {
        List<Integer> path = new ArrayList<>();
        path.add(perto);
        while (antecessor[perto] != UNDEFINED) {
            path.add(antecessor[perto]);
            perto = antecessor[perto];
        }
        Collections.reverse(path);
        return path;
    }       
}
