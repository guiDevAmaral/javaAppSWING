
package DadosDaPressao;


public class DadosPressao {
    
    private String data;
    private String hora;
    private int pSistolica;
    private int pDiastolica;
    private boolean estresse;

    public DadosPressao() {
    }

    public DadosPressao(String data, String hora, int pSistolica, int pDiastolica, boolean estresse) {
        this.data = data;
        this.hora = hora;
        this.pSistolica = pSistolica;
        this.pDiastolica = pDiastolica;
        this.estresse = estresse;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getpSistolica() {
        return pSistolica;
    }

    public void setpSistolica(int pSistolica) {
        this.pSistolica = pSistolica;
    }

    public int getpDiastolica() {
        return pDiastolica;
    }

    public void setpDiastolica(int pDiastolica) {
        this.pDiastolica = pDiastolica;
    }

    public boolean isEstresse() {
        return estresse;
    }

    public void setEstresse(boolean estresse) {
        this.estresse = estresse;
    }

    
    
    
    
}
