import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while(true){
            System.out.println("Ingrese la aopcion: ");
            System.out.println("1. Listar regiones");
            System.out.println("2. Agregar region");
            System.out.println("3. Mostrar los departamentos que tienen por lo menos 3 empleados");
            System.out.println("4. Agregar persona y mostrar el nuevo id generado");
            System.out.print("Opcion: ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            RegionDB regionDB = new RegionDB();
            Persona persona=new Persona();
            switch(opcion){
                case "1":
                    regionDB.listarRegiones();
                    break;
                case "2":
                    regionDB.agregarRegiones();
                    break;
                case "3":
                    regionDB.mostrarDepas();
                    break;
                case "4":
                    persona.agregarPersona();
                    break;


            }

            System.out.println("---------------------------");
        }










    }
}
/*
        Trabajo trabajo = new Trabajo();
        trabajo.guardarNuevoTrabajo();
        trabajo.listarTrabajos();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario= "root";
        String password="root";

        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        try (Connection connection= DriverManager.getConnection(url,usuario,password);){

            System.out.println("conexion exitosa");

            try(Statement stmt =connection.createStatement();) {

                String sql = "select * from employees";
                //select
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int employeeId = rs.getInt(1);
                    int id = rs.getInt("employee_id");

                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString(3);

                    System.out.println(employeeId + " | " + firstName + " | " + lastName);
                }
            }

            //insert, update o delete
            //stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/


