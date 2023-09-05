package devandroid.rafael.myapplication.model;

public class Pessoa {

    private String primeiroNome;
    private String sobreNome;
    private String cursoDescricao;
    private String telefoneContato;
    public Pessoa(){

    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public String getCursoDescricao() {
        return cursoDescricao;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }


    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setCursoDescricao(String cursoDescricao) {
        this.cursoDescricao = cursoDescricao;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", cursoDescricao='" + cursoDescricao + '\'' +
                ", telefoneContato='" + telefoneContato + '\'' +
                '}';
    }
}
