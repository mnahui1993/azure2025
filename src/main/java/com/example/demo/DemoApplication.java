package com.example.demo;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class DemoApplication  implements CommandLineRunner {
public class DemoApplication{
	//@Value("${database.secret.value}")
	//private String mySecret;
	//private final SecretClient secretClient;

	//public DemoApplication(SecretClient secretClient) {
	//	this.secretClient = secretClient;
	//}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/*@Override
	public void run(String... args) {
		//System.out.println("sampleProperty: " + secretClient.getSecret("conectionprueba").getValue());

		System.out.println("****secreto obtenido ***** " + mySecret);
	}

	public String getSecreto(){
		return mySecret;
	}*/

}
