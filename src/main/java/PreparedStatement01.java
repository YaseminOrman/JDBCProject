import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","rukiyetunc");
        Statement st = con.createStatement();

        String sql1 = "update companies set number_of_employees = ? where company = ?";
        PreparedStatement pst1=con.prepareStatement(sql1);

        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        int numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);
        String sql2 = "select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql2);
        while(resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"---"+resultSet1.getString(2)+"---"+resultSet1.getInt(3));
        }
        con.close();
        st.close();
        pst1.close();
    }
}
