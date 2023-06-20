package logfilegenerator.Java_LogFileGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LogFileUtility {

	private static String LogfilePath;
	private static File logDirectory, file;
	private static LocalDate today = LocalDate.now();
	private static String formattedDate = today.format(DateTimeFormatter.ofPattern("d-M-yyyy"));


	private static String fileName;
	private static BufferedWriter writer;
	private static LocalDateTime currentDateTime = LocalDateTime.now();;
	private static String logTDformat = currentDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy hh:mm:ss a"));;

	private static Path result = null;

	// Static Name for LogFile -->extension as .txt or .log
	public static void WriteLog(String msg) {

		try {
			  fileName = "AutomationLogReport_" + formattedDate + ".txt";
			writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString()+ "\\Logs\\"+fileName, true));
			writer.append(logTDformat + " : ");
			writer.append(msg + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Error in Writing log " + ex.getMessage());
		}
	}

	// Customized Name for LogFile ==> passing Specific Input Names like ===> INFO,ERROR..etc

	public static void WriteLog(String msg, String logfileName) {

		try {
			fileName = "AutomationLogReport_" +logfileName + "_" + formattedDate + ".txt";
			writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "\\Logs\\"+fileName, true));
			writer.append(logTDformat + " : ");
			writer.append(msg + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Error in Writing log " + ex.getMessage());
		}
	}

	// Define Log File Path
	public static void GetLogFilePath() {
		try {
			String currentDirectory = System.getProperty("user.dir");
			LogfilePath = currentDirectory + "\\Logs\\";

			System.out.println(LogfilePath);
		} catch (Exception ex) {
			System.out.println("Fetching Log files path Error :" + ex.getMessage());
		}

	}

	// Step 1 - Delete the Old Exisitng Log File
	public static void DeleteLogFiles() {
		logDirectory = new File(LogfilePath.toString());
		try {
			if (logDirectory.exists()) {
				File filesList[] = logDirectory.listFiles();
				for (File file : filesList) {
					if (file.isFile()) {
						file.delete();
					}
					System.out.println("Deleted");
				}

			}
		} catch (Exception ex) {
			System.out.println("Deleting Files Error :" + ex.getMessage());
		}
	}

	

	public static void main(String[] args) {

		GetLogFilePath();
		DeleteLogFiles();
		WriteLog("First message");
		WriteLog("Second message");
		WriteLog("Third message", "Info");
		WriteLog("Fourth message", "Error");
	}
//Moving a File->pass the source and destination path
	private static void moveFile(String src, String dest) 
	{
		
		try 
		{
			result = Files.move(Paths.get(src), Paths.get(dest));
		} 
		catch (IOException e) 
		{
			System.out.println("Exception while moving file: " + e.getMessage());
		}
		if (result != null) 
		{
			System.out.println("File moved successfully.");
		} else 
		{
			System.out.println("File movement failed.");
		}
	}
}
