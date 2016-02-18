import java.net.*;
import java.io.*;
import java.io.BufferedReader;
 
public class Protocol {
 
    public String processInput(String theInput) {
        String theOutput = "";
		try(BufferedReader br = new BufferedReader(new FileReader("accounts.txt")) ){
			String[] tmp = new String[2];
			String tmps = "";
			while((tmps = br.readLine()) != null){
				tmp = tmps.split(";");
				if(theInput.equals(tmp[0]) ){
					theOutput = tmp[1];
					break;
				}else{
					theOutput = "Ki vagy?";
				}
			}
		}catch(Exception e){
			return e.getMessage();
		}
        return theOutput;
    }
}
