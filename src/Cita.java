public class Cita {

    //inicializando atributos
    private String id;
    private String dia;
    private String hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;


    //constructores
    public Cita(String id, String dia, String hora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public Cita() {
    }


    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    //método para pasar info a formato CSV
    public String toCSVString() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(id).append(",")
                .append(dia).append(",")
                .append(hora).append(",")
                .append(motivo).append(",")
                .append(doctor.getId()).append(",")
                .append(paciente.getId());
        return csvBuilder.toString();
    }
}
