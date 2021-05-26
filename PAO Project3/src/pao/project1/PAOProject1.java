/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.sql.*;

/**
 *
 * @author Arthur
 */
public class PAOProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/paodb", "root", "root");

            
            PAOProject1 obj = new PAOProject1();
            obj.run(args);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) {
        Service service = new Service();

    }
}
