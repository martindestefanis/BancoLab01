package ar.edu.utn.frsf.isi.dam.bancolab01.modelo;

public class Cliente {
    private String mail;
    private String cuil;

    public String getMail() {
        return mail;
    }

    public String getCuil() {
        return cuil;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "mail='" + mail + '\'' +
                ", cuil='" + cuil + '\'' +
                '}';
    }
}
