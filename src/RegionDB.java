import java.sql.*;
import java.util.Scanner;

public class RegionDB {
    public void listarRegiones(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";

        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        String sql = "select * from regions";


        try(Connection connection= DriverManager.getConnection(url,usuario,password);
            PreparedStatement pstmt =connection.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();){

            while (rs.next()){
                int regionId= rs.getInt(1);

                String regionName=rs.getString(2);
                System.out.println("regionId: "+regionId+" |regionName: "+regionName);

            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void agregarRegiones(){
        Scanner sc = new Scanner(System.in);


        System.out.print("Ingrese el nombre de la region ");
        String regionName=sc.nextLine();



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";

        //obteniendo el max id+1 porque el id no es ai
        String sql= "select max(region_id)+1 from regions";
        double nextId =0;
        try (Connection connection= DriverManager.getConnection(url,usuario,password);
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery(sql)){

            rs.next();
            nextId =rs.getDouble(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        sql= "insert into regions(region_id, region_name) values (?,?)";

        try (Connection connection= DriverManager.getConnection(url,usuario,password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){

            preparedStatement.setDouble(1,nextId);
            preparedStatement.setString(2,regionName);

            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void mostrarDepas(){
        String sql ="select c.country_name , l.city,d.department_name, count(e.employee_id) as `Numero de empleados` \n" +
                "from employees e\n" +
                "inner join departments d on e.department_id=d.department_id\n" +
                "inner join locations l on d.location_id=l.location_id\n" +
                "inner join  countries c on l.country_id=c.country_id\n" +
                "group by e.department_id\n" +
                "having `Numero de empleados`>3;";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";

        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        try(Connection connection= DriverManager.getConnection(url,usuario,password);
            PreparedStatement pstmt =connection.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();){

            while (rs.next()){
                String  countryName= rs.getString(1);
                String city=rs.getString(2);
                String  departmentName= rs.getString(3);
                int cantidadEmpleados=rs.getInt(4);
                System.out.println("countryName: "+countryName+" |city: "+city+
                        " |departmentName: "+departmentName+
                        " |cantidadEmpleados: "+cantidadEmpleados);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
