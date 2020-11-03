import java.sql.*;
import java.util.Scanner;

public class Persona {
    public void agregarPersona(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre ");
        String nombre=sc.nextLine();
        System.out.print("Ingrese el nombre ");
        String apellido=sc.nextLine();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        String sql= "insert into persona(nombre,apellido) values(?,?)";
        try (Connection connection= DriverManager.getConnection(url,usuario,password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){

            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,apellido);

            preparedStatement.executeUpdate();
            try(ResultSet rs= preparedStatement.getGeneratedKeys();){
                rs.next();
                int idGenerado = rs.getInt(1);
                System.out.println("Id generado "+idGenerado);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }






    }



}
