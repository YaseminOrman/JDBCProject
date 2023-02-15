import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","rukiyetunc");
        Statement st = con.createStatement();

        String sql1 = "select company,number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 row only";
        ResultSet resultSet1 = st.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"--->"+ resultSet1.getInt("number_of_employees"));
        }
        String sql2 = "select company,number_of_employees \n" +
                "from companies where number_of_employees = (select max(number_of_employees) \n" +
                "from companies\n" +
                "where number_of_employees<(select max (number_of_employees)\n" +
                " from companies))\n" +
                "\n";

        ResultSet resultSet2 = st.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getString("company")+"--->"+resultSet2.getInt("number_of_employees"));
        }
        String sql3 = "select company ,number_of_employees from companies where number_of_employees < (select avg(number_of_employees) from companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while(resultSet3.next()){
            System.out.println(resultSet3.getString("company")+"--->"+resultSet3.getInt("number_of_employees"));
        }

        con.close();
        st.close();
    }
}
