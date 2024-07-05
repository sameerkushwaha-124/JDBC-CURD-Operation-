package CurdOperation;

import in.JDBCUtil.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertMySqlQuerry {
    public static void main(String args[]){
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = JDBCUtil.getJdbcConnection();
            if(connection != null){
                String myinsertquerry = "insert into student(id,name,age) values(?,?,?)";
                preparedstatement = connection.prepareStatement(myinsertquerry);

                if(preparedstatement != null){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Your Id : ");
                    int id = sc.nextInt();
                    preparedstatement.setInt(1,id);

                    System.out.println("Enter Your Name : ");
                    String name = sc.next();
                    preparedstatement.setString(2,name);

                    System.out.println("Enter Your Age : ");
                    int age = sc.nextInt();
                    preparedstatement.setInt(3,age);

                    int roweffected  = preparedstatement.executeUpdate();
                    if(roweffected == 1){
                        System.out.println("Row Inserted");
                    }else{
                        System.out.println("Row Not Inserted");
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                JDBCUtil.closeResource(connection,preparedstatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
