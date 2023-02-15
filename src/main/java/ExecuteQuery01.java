import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","rukiyetunc");
        Statement st = con.createStatement();

        String sql1 = "select country_name from countries where region_id = 1";
        boolean result1 = st.execute(sql1);
        System.out.println("result1 = " + result1);
        ResultSet resultSet1 = st.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println( resultSet1.getString("country_name"));
        }

        String sql2 ="select country_id, country_name from countries where region_id >2";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getString("country_id")+"--->"+resultSet2.getString("country_name"));
        }

        String sql3 = "select company from companies where number_of_employees = (select min(number_of_employees) from companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while(resultSet3.next()){
            System.out.println(resultSet3.getString("company"));
        }

        con.close();
        st.close();


    }
}
