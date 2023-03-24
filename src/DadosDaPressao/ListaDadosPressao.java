
package DadosDaPressao;

import java.util.ArrayList;
import java.util.List;


public class ListaDadosPressao {
    private static final List<DadosPressao> listaDados = new ArrayList();
    
    public static List<DadosPressao> RetornaDados(){
        return listaDados;
    }
    
    public static void Adicionar(DadosPressao dados){
        listaDados.add(dados);
    }
    
    public static DadosPressao getDados(int id){
        return listaDados.get(id);
    }
    
    public static void Atualizar (DadosPressao dados, int posDados){
        listaDados.set(posDados, dados);
    }  
}
