package br.com.abevieiramota.loteria.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.abevieiramota.gui.wmain.WMain;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WMain.class)
			.headless(false)
			.run(args);
	}

}
