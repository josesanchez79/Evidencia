import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //inicializa objeto clinica con método para cargar información de un archivo y lector
        Clinica clinica = new Clinica();
        clinica.cargaInfo();
        Scanner lector = new Scanner(System.in);

        //admin hardcodeado para primer uso, se puede comentar una vez generados nuevos admins
        String admin1 = "admin";
        String contraseña1 = "contraseña";
        clinica.agregarAdmin(admin1, contraseña1);

        //indicador para terminar la sesión
        boolean sesion = false;

        //loop while para iterar menú
        while (!sesion) {

            //Se pide usuario y contraseña, el método verifica que sean correctos
            System.out.print("Usuario: ");
            String usuario = lector.nextLine();
            System.out.print("Contraseña: ");
            String contraseña = lector.nextLine();

            sesion = clinica.verificaAdmin(usuario, contraseña);

            if (!sesion) {
                System.out.println("Usuario o contraseña invalido, por favor intenta de nuevo");
            }
        }

        // Menú de opciones para el administrador a partir de un while loop y un switch
        int opc = 0;

        while (opc != 6) {
            System.out.println("Menu");
            System.out.println("1. Agregar doctor");
            System.out.println("2. Agregar paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Relacionar cita");
            System.out.println("5. Revisar citas");
            System.out.println("6. Salir");

            System.out.print("Introduce tu respuesta: ");
            opc = lector.nextInt();
            lector.nextLine();

            //Menú
            switch (opc) {

                //Agregar doctor
                case 1:
                    System.out.print("ID Doctor: ");
                    String doctorId = lector.nextLine();
                    System.out.print("Nombre doctor: ");
                    String doctorNombre = lector.nextLine();
                    System.out.print("Especialidad: ");
                    String doctorEspec = lector.nextLine();

                    Doctor nuevoDoctor = new Doctor(doctorId, doctorNombre, doctorEspec);
                    clinica.agregarDoctor(nuevoDoctor);
                    clinica.guardarInfo();
                    break;

                case 2:

                    //Agregar paciente
                    System.out.print("ID Paciente: ");
                    String pacienteId = lector.nextLine();
                    System.out.print("Nombre paciente: ");
                    String pacienteNombre = lector.nextLine();

                    Paciente nuevoPaciente = new Paciente(pacienteId, pacienteNombre);
                    clinica.agregarPaciente(nuevoPaciente);
                    clinica.guardarInfo();
                    break;

                case 3:

                    //Crear cita
                    System.out.print("ID Cita: ");
                    String citaId = lector.nextLine();
                    System.out.print("Dia: ");
                    String dia = lector.nextLine();
                    System.out.print("Hora: ");
                    String hora = lector.nextLine();
                    System.out.print("Motivo: ");
                    String motivo = lector.nextLine();

                    System.out.println("Doctores disponibles:");
                    for (Doctor doctor : clinica.getDoctores()) {
                        System.out.println(doctor.getId() + " - " + doctor.getNombre());
                    }
                    System.out.print("Selecciona el ID de un doctor: ");
                    String selecDoctorId = lector.nextLine();

                    System.out.println("Pacientes disponibles:");
                    for (Paciente paciente : clinica.getPacientes()) {
                        System.out.println(paciente.getId() + " - " + paciente.getNombre());
                    }
                    System.out.print("Selecciona el ID de un paciente: ");
                    String selecPacienteId = lector.nextLine();

                    Doctor selecDoctor = clinica.buscarDoctor(selecDoctorId);
                    Paciente selecPaciente = clinica.buscarPaciente(selecPacienteId);

                    if (selecDoctor != null && selecPaciente != null) {
                        Cita nuevaCita = new Cita(citaId, dia, hora, motivo, selecDoctor, selecPaciente);
                        clinica.crearCita(nuevaCita);
                        clinica.guardarInfo();
                    } else {
                        System.out.println("ID de paciente o doctor invalido, por favor intenta de nuevo.");
                    }
                    break;

                case 4:

                    //Relacionar cita por método
                    System.out.print("ID Cita: ");
                    String citaRelacion = lector.nextLine();
                    System.out.print("ID Doctor: ");
                    String doctorRelacion = lector.nextLine();
                    System.out.print("ID Paciente: ");
                    String pacienteRelacion = lector.nextLine();

                    clinica.relacionarCita(citaRelacion, doctorRelacion, pacienteRelacion);
                    clinica.guardarInfo();
                    break;

                case 5:

                    //Checar citas
                    clinica.checarCitas();
                    break;

                case 6:

                    //salida
                    System.out.println("Saliendo..");
                    break;

                default:

                    //default en caso de meter opc invalida
                    System.out.println("Opcion invalida, intenta de nuevo");
                    break;
            }
        }

        lector.close();
    }
}
