package baseline;



import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import baseline.core.Project;
import baseline.database.Crawler;

import baseline.model.Schema;
import baseline.model.table.ColumnDataType;
import baseline.xml.JaxbTools;



public class BaseLine {
	static String version ="1.0.0";
	
	
	
	

	
	public static void main(String[] args) throws Exception {
		Options opts = new Options();
		System.out.println("BaseLine.io(c) version "+version);
		
		opts.addOption("file",true, "Файл с описанием заданий(Jobs)");
		opts.addOption("job",true, "Выполнить данное задание");
		opts.addOption("help",false, "Помощь");
		
		
		
		opts.addOption("xsb", true, "Выгрузить XSD схему");
		CommandLineParser parser = new GnuParser();
		CommandLine cmd = parser.parse(opts, args);
		File file = null;
		
		if(args.length == 0){
			file = new File("jobs.xml");
		}else if(cmd.hasOption("help")){	
			HelpFormatter formater = new HelpFormatter();
			formater.printHelp("baseline [-file <file>][-job <jobname>][-help]", "Поумолчанию загружается файл jobs.xml", opts, "Good Luck");
			return;
		}else if(cmd.hasOption("file")){
			file = new File(cmd.getOptionValue("file"));
		}
		
		JaxbTools tools = new JaxbTools(new Class[]{Project.class});
		Project project = (Project) tools.FileToObject(file);
		
		project.execute(new BaseLineContext());
		
		
	}

}