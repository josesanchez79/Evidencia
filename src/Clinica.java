import javax.print.Doc;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Clinica {
    private static final String archivo = "clinica_info.txt";
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
    public void cargaInfo(){
        doctores.clear();
        pacientes.clear();
        citas.clear();

        File archivo = new File("C:\\Users\\usuario\\IdeaProjects\\Evidencia\\src\\db\\clinica_info.txt");

        if (!archivo.exists()) {
            System.out.println("No se ha encontrado el archivo. Creando nuevos archivos.");

            // Crear directorio si no existe
            File directorio = archivo.getParentFile();
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear archivo
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
                return;
            }
        }

        try (Scanner lector = new Scanner(new FileReader(archivo))){
            String separador = "";
            while (lector.hasNextLine()){
                String linea = lector.nextLine().trim();
                if (!linea.isEmpty()){
                    if(linea.equals("Doctores")){
                        separador = "Doctores";
                    } else if (linea.equals("Pacientes")) {
                        separador = "Pacientes";
                    } else if (linea.equals("Citas")) {
                        separador = "Citas";
                    } else {
                        switch (separador) {
                            case "Doctores":
                                String[] infoDoctor = linea.split(",");
                                if (infoDoctor.length == 3){
                                    Doctor doctor = new Doctor(infoDoctor[0], infoDoctor[1], infoDoctor[2]);
                                    doctores.add(doctor);
                                }
                                break;
                            case "Pacientes":
                                String[] infoPaciente = linea.split(",");
                                if (infoPaciente.length == 2){
                                    Paciente paciente = new Paciente(infoPaciente[0], infoPaciente[1]);
                                    pacientes.add(paciente);
                                }
                                break;
                            case "Citas":
                                String[] infoCita = linea.split(",");
                                if (infoCita.length == 6){
                                    Cita cita = new Cita();
                                    cita.setId(infoCita[0]);
                                    cita.setDia(infoCita[1]);
                                    cita.setHora(infoCita[2]);
                                    cita.setMotivo(infoCita[3]);
                                    cita.setDoctor(buscarDoctor(infoCita[4]));
                                    cita.setPaciente(buscarPaciente(infoCita[5]));
                                    citas.add(cita);
                                }
                                break;
                        }
                    }
                }

            }   }catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");

        }
    }

    public void guardarInfo(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))){
            writer.write("Doctores");
            for (Doctor doctor : doctores){
                writer.write(doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad());
            }

            writer.write("Pacientes");
            for (Paciente paciente : pacientes){
                writer.write(paciente.getId() + "," + paciente.getNombre());
            }

            writer.write("Citas");
            for (Cita cita : citas){
                writer.write(cita.toCSVString());
            }
        } catch (IOException e){
            System.out.println("Error al guardar informacion al archivo");
        }
    }
}
