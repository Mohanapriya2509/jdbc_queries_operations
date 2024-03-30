import java.sql.*;

public class jdbc_queries {

    public static void main(String[] args) throws Exception {
        readRecords();
        insertRecords();
        insertRecordspst();
        delete();
        update();
        callablest();
        //callablest2();
    	callablest3();
    	Commit();
    	autocommit();
    	batchdemo();
        
    }
    //to display the database
    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        String query = "SELECT * FROM jdbcdemo";
        
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            System.out.println("id is: " + rs.getInt(1));
            System.out.println("name is: " + rs.getString(2));
            System.out.println("salary is: " + rs.getInt(3));
        }

        con.close();
    }
    //using variable to insert method
    public static void insertRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        String query = "Insert into jdbcdemo(ID,ENAME,SALARY) values(4,'Priya',25000),(5,'DHARU',35000)";
        
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        
        System.out.println("The no of rows affected:"+rows);

        con.close();
    }
    //using prepared statement to insert methods
    public static void insertRecordspst() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        int Id=4;
        String Ename="Mala";
        int Salary= 50000;
        String query = "Insert into jdbcdemo values(?,?,?)";
        
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement pst=con.prepareStatement(query);
        pst.setInt(1, Id);
        pst.setString(2,Ename);
        pst.setInt(3, Salary);
        int rows=pst.executeUpdate();
        
        System.out.println("The no of rows affected by preparedstatement:"+rows);

        con.close();
    }
    //using delete to delete the records or data's
    public static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        int Id=4;
        String query = "delete from jdbcdemo where Id="+Id;
        
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        int rows=st.executeUpdate(query);
        
        
        System.out.println("The no of rows affected by delete:"+rows);

        con.close();
    }
    public static void update()throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        int ID=3;
        String query ="update jdbcdemo set salary=26000 where ID=1";
        
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        int rows=st.executeUpdate(query);
        
        
        System.out.println("The no of rows affected by update:"+rows);

        con.close();
    }
  /*here are three types of statements
   1.Normal statement
   2.prepare statement
   3.callable statement
   */
    //using stored procedure
    public static void callablest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
       
        Connection con = DriverManager.getConnection(url, username, password);
        CallableStatement cst = con.prepareCall("{call GetEmp()}");
        ResultSet rs = cst.executeQuery(); // Corrected to execute the stored procedure and obtain the result set
        
        while (rs.next()) {
            System.out.println("id is: " + rs.getInt(1));
            System.out.println("name is: " + rs.getString(2));
            System.out.println("salary is: " + rs.getInt(3));
        }

        con.close();
    }
    public static void callablest2() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        int Id=3;
        Connection con = DriverManager.getConnection(url, username, password);
        CallableStatement cst = con.prepareCall("{call GetEmp(?)}");
        ResultSet rs = cst.executeQuery(); // Corrected to execute the stored procedure and obtain the result set
        
        while (rs.next()) {
            System.out.println("id is: " + rs.getInt(1));
            System.out.println("name is: " + rs.getString(2));
            System.out.println("salary is: " + rs.getInt(3));
        }

        con.close();
    }
    
    //using stored procedure
    public static void callablest3() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        int Id=3;
        Connection con = DriverManager.getConnection(url, username, password);
        CallableStatement cst = con.prepareCall("{call GetEmpId1(?,?)}");
        cst.setInt(1, Id);
        cst.registerOutParameter(2, Types.VARCHAR);
        ResultSet rs = cst.executeQuery();
        // Corrected to executeQuery() to retrieve result setS   
        System.out.println(cst.getString(2));
        con.close();
    }
    //using commit
    public static void Commit() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        String query="update jdbcdemo set salary= 400000 where ID=1";
        String query2="update jdbcdemo set salary= 500000 where ID=2";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        int rows1=st.executeUpdate(query);
        int rows2=st.executeUpdate(query2);
        System.out.println("The no of rows affted:"+rows1);
        System.out.println("The no of rows affted:"+rows2);
        
    }
    //using autocommit
    public static void autocommit() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        String query="update jdbcdemo set salary= 400000 where ID=1";
        String query2="update jdbcdemo set salary= 500000 where ID=2";
        Connection con = DriverManager.getConnection(url, username, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int rows1=st.executeUpdate(query);
        System.out.println("The no of rows affted:"+rows1);
        int rows2=st.executeUpdate(query2);
        System.out.println("The no of rows affted:"+rows2);
        if(rows1>0 && rows2>0) {
        	con.commit();
        }
        con.close();
    }
        
        //using batch demo
    public static void batchdemo() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Muramovi0527";
        String query = "update jdbcdemo set salary= 400000 where ID=1";
        String query2 = "update jdbcdemo set salary= 400000 where ID=2";
        String query3 = "update jdbcdemo set salary= 400000 where ID=3";
        String query4 = "update jdbcdemo set salary= 400000 where ID=5";

        Connection con = DriverManager.getConnection(url, username, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query);
        st.addBatch(query2);
        st.addBatch(query3);
        

        int[] res = st.executeBatch();
        for (int i = 0; i < res.length; i++) {
            System.out.println("the rows affected are: " + res[i]);
        }

        if (res.length > 0) {
            con.commit();
        }
        con.close();
    }

   
}
    

    
  
    
    


    






