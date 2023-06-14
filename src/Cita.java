public class Cita {
    private String id;
    private String dia;
    private String hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String dia, String hora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

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
}
