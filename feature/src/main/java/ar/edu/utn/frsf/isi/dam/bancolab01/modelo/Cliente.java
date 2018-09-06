package ar.edu.utn.frsf.isi.dam.bancolab01.modelo;

public class Cliente {
    private String mail;
    private String cuit;

    public String getMail() {
        return mail;
    }

    public String getCuit() {
        return cuit;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCuil(String cuil) {
        this.cuit = cuil;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "mail='" + mail + '\'' +
                ", cuil='" + cuit + '\'' +
                '}';
    }
}