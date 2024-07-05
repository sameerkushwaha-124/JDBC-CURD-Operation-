package CurdOperation;

import in.JDBCUtil.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectQuerry {
    public static void main(String args[]){
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;

        try {
            connection = JDBCUtil.getJdbcConnection();

            if(connection != null){
                String mysqlquerry ="SELECT * FROM student WHERE id=?";
                preparedstatement = connection.prepareStatement(mysqlquerry);
                if(preparedstatement != null){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter the Id which you want to get from the DB : ");
                    int id = sc.nextInt();
                    preparedstatement.setInt(1,id);

                    resultset = preparedstatement.executeQuery();
                    if(resultset != null){
                        if(resultset.next()){
                            System.out.println("id\tname\tage");
                            System.out.println(resultset.getInt(1)+"\t"
                           + resultset.getString(2)+"\t"+resultset.getInt(3));
                        }
                    }
                    sc.close();
                }

            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                JDBCUtil.closeResource(connection,preparedstatement,resultset);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
