import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class Clinica {
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private Map<String, String> administradores;

    public Clinica() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        administradores = new HashMap<>();
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Map<String, String> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(Map<String, String> administradores) {
        this.administradores = administradores;
    }

    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
        System.out.println("Doctor agregado correctamente");
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente agregado correctamente");
    }

    public void crearCita(Cita cita) {
        citas.add(cita);
        System.out.println("Cita creada correctamente");
    }

    public void relacionarCita(String citaId, String doctorId, String pacienteId) {
        Cita cita = buscarCita(citaId);
        Doctor doctor = buscarDoctor(doctorId);
        Paciente paciente = buscarPaciente(pacienteId);

        if (cita != null && doctor != null && paciente != null) {
            cita.setDoctor(doctor);
            cita.setPaciente(paciente);
            System.out.println("Appointment related successfully.");
        } else {
            System.out.println("Invalid appointment, doctor, or patient ID.");
        }
    }

    public void agregarAdmin(String usuario, String contraseña) {
        administradores.put(usuario, contraseña);
        System.out.println("Administrador agregado correctamente");
    }

    public boolean verificaAdmin(String usuario, String contraseña) {
        String contraseñaG = administradores.get(usuario);
        return contraseñaG != null && contraseñaG.equals(contraseña);
    }

    public Cita buscarCita(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    public Doctor buscarDoctor(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public Paciente buscarPaciente(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
}
