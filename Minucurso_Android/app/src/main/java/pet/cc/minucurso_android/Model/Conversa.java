package pet.cc.minucurso_android.Model;

public class Conversa {

    private String titulo;
    private String previa;
    private String hora;
    private int qntMsgss;

    public Conversa(String titulo, String previa, String hora, int qntMsgss) {
        this.titulo = titulo;
        this.previa = previa;
        this.hora = hora;
        this.qntMsgss = qntMsgss;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrevia() {
        return previa;
    }

    public void setPrevia(String previa) {
        this.previa = previa;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getQntMsgss() {
        return qntMsgss;
    }

    public void setQntMsgss(int qntMsgss) {
        this.qntMsgss = qntMsgss;
    }
}
