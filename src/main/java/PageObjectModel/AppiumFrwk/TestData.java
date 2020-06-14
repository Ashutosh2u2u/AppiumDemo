package PageObjectModel.AppiumFrwk;

import org.testng.annotations.DataProvider;

public class TestData 
{

@DataProvider(name="InputData")
public Object[][] getDataforEditField()
{
	//2 sets of data
	Object[][] obj=new Object[][]
			{
		{"hello"},{"@#$%"}
			};
			return obj;
}

}
