package entities;

public class Usuario {
    private int ID;
    private String Nombre;
    private String DNI;
    private String InformacionAdicional;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getInformacionAdicional() {
        return InformacionAdicional;
    }

    public void setInformacionAdicional(String InformacionAdicional) {
        this.InformacionAdicional = InformacionAdicional;
    }
    
    
}
