import java.sql.*;
import java.util.Scanner;

public class Trabajo {
    public void guardarNuevoTrabajo(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el job id: ");
        String jobId=sc.nextLine();

        System.out.print("Ingrese el job title: ");
        String jobTitle=sc.nextLine();

        System.out.print("Ingrese el min salary (Presione enter si no tiene salario): ");
        String minSalaryStr =sc.nextLine();
        int minSalary=-1;
        try {
             minSalary = Integer.parseInt(minSalaryStr);
        } catch (NumberFormatException e) {

        }
        System.out.print("Ingrese el max salary (Presione enter si no tiene salario): ");
        String maxSalaryStr =sc.nextLine();
        int maxSalary=-1;
        try {
            maxSalary = Integer.parseInt(maxSalaryStr);
        } catch (NumberFormatException e) {

        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";

        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        String sql = "insert into jobs(job_id,job_title, min_salary,max_salary) VALUES(?,?,?,?)";


        try (Connection connection= DriverManager.getConnection(url,usuario,password);
            PreparedStatement pstmt =connection.prepareStatement(sql);){

            pstmt.setString(1,jobId);
            pstmt.setString(2,jobTitle);
            if(minSalary==-1){
                pstmt.setNull(3,Types.INTEGER);
            }else{
                pstmt.setInt(3,minSalary);
            }
            if(maxSalary==-1){
                pstmt.setNull(4,Types.INTEGER);
            }else{
                pstmt.setInt(4,maxSalary);
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void listarTrabajos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usuario="root";
        String password="root";

        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        String sql = "select * from jobs";


        try(Connection connection= DriverManager.getConnection(url,usuario,password);
            PreparedStatement pstmt =connection.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();){

            while (rs.next()){
                String jobId= rs.getString(1);
                String jobTitle=rs.getString(2);
                int minSalary= rs.getInt("min_salary");
                int maxSalary= rs.getInt("max_salary");
                System.out.println("JobId: "+jobId+" |jobTitle: "+jobTitle+" |minSalary: "+minSalary+" |maxSalary: "+maxSalary);

            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}


/*String sql = "insert into jobs(job_id,job_title, min_salary,max_salary)"+
                        "VALUES('"+jobId+"','"+jobTitle+"',"+minSalary+","+maxSalary+")";*/
