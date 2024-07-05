package CurdOperation;

import in.JDBCUtil.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteQuerry {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = JDBCUtil.getJdbcConnection();
            if(connection != null){
                String myinsertquerry = "delete from student where id=? ";
                preparedstatement = connection.prepareStatement(myinsertquerry);

                if(preparedstatement != null){
                    Scanner sc = new Scanner(System.in);

                    System.out.println("Enter the Id which you want to delete : ");
                    int id = sc.nextInt();
                    preparedstatement.setInt(1,id);



                    int roweffected  = preparedstatement.executeUpdate();
                    if(roweffected == 1){
                        System.out.println("Row Deleted");
                    }else{
                        System.out.println("Row Not Deleted");
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
