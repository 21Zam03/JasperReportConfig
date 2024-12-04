package com.zam.report;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ReportApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(ReportApplication.class, args);
		ReportApplication.createReport();
	}

	public static void createReport() throws JRException {
		String destinationPath = "src" + File.separator +
				"main" + File.separator +
				"resources" + File.separator +
				"static" + File.separator +
				"ReportGenerated.pdf";
		String filePath = "src" + File.separator +
				"main" + File.separator +
				"resources" + File.separator +
				"templates" + File.separator +
				"report" + File.separator +
				"Report.jrxml";

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("sale_id", "00023944");
		parameters.put("date", LocalDate.now().toString());
		parameters.put("total_price", 456.90);
		parameters.put("client", "Jose Zambrano");
		parameters.put("product_quantity", "4");
		parameters.put("image_dir", "classpath:/static/images/");
		JasperReport report = JasperCompileManager.compileReport(filePath);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(print, destinationPath);
		System.out.println("Report was created successfully");
	}
}
