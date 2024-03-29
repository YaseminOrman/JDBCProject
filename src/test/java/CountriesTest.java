import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CountriesTest {
     /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Verify that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */
    @Test
    public void countryTest(){
      //User connects to the database
      JdbcUtils.connectToDatabase("localhost","postgres","postgres","rukiyetunc");
      JdbcUtils.createStatement();

      //  User sends the query to get the region ids from "countries" table
      List<Object> regionIds=JdbcUtils.getColumnList("region_id","countries") ;
        System.out.println("regionIds = " + regionIds);

        //Verify that the number of region ids greater than 1 is 17.
      int idsGreaterThan1 = regionIds.stream().filter(t->(Integer)t>1).collect(Collectors.toList()).size();
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);
        assertEquals(17,idsGreaterThan1);

        JdbcUtils.closeConnectionAndStatement();

    }

}
