public class Paciente {

    //inicialiando atributos
    private String id;
    private String nombre;


    //constructor
    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
