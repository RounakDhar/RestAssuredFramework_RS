package ExcelIntegration;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {
	
	public static void main(String[] args) throws IOException {
		
		DataDriven dataDriven = new DataDriven();
		ArrayList<String> arrayListData =    dataDriven.getData("Add Profile", null);
		
//		String getDataIndexString = 	arrayListData.get(0);
//		System.out.println(getDataIndexString);
		
		System.out.println(arrayListData.get(0));
		System.out.println(arrayListData.get(1));
		System.out.println(arrayListData.get(2));
		System.out.println(arrayListData.get(3));
		
	}

}
