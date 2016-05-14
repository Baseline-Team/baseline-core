package baseline;


import baseline.core.Project;
import baseline.xml.XmlService;
import org.apache.commons.cli.*;

import java.io.File;


public class BaseLine {
	static String version = "2.0.0";
	
	
	
	

	
	public static void main(String[] args) throws Exception {
		Options opts = new Options();
		System.out.println("BaseLine(c) version " + version);
		
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
		
		Project project = new XmlService().loadProject(file);
		
		project.execute(new BaseLineContext());
		
		
	}

}
