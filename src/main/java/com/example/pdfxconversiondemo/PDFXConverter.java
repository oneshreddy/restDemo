package com.example.pdfxconversiondemo;

import java.io.File;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.bcl.easypdf.printer.*;

public class PDFXConverter {

	private final long id;
	private byte[] tgtContent;

	public PDFXConverter(long idParam) {
		id=idParam;
	}

	public byte[] getConvertedData(){
		return tgtContent;
	}

	public String convertData(String srcContent){
		Printer printer = new Printer();
		Logger logger = LoggerFactory.getLogger(PDFXConverter.class);
		String result = "Success!";
		
		try
		{
			String srcFileName = "c:\\test\\restDemo\\temp"+id+".doc";
			String tgtFileName = "c:\\test\\restDemo\\temp"+id+".pdf";
			// FileWriter fw= new FileWriter(tempFileName);
			// fw.write(content);
			// fw.close();

			// Create an output stream to write to srcFile
			FileOutputStream srcFileStream = new FileOutputStream(srcFileName);

			// Creates an output stream writer specifying the encoding
			OutputStreamWriter srcWriter = new OutputStreamWriter(srcFileStream, Charset.forName("UTF8"));
			srcWriter.write(srcContent);
			srcWriter.close();

			PrintJob printjob = printer.getPrintJob();
			PDFSetting pdfSettings = printjob.getPDFSetting();


			pdfSettings.setStandardPdfAConformance(prnPdfAConformance.PRN_PDFA_CONFORM_NONE);
			pdfSettings.setStandardPdfXConformance(prnPdfXConformance.PRN_PDFX_CONFORM_1A);
			pdfSettings.setStandardCmykProfile("USWebCoatedSWOP.icc");


			printjob.PrintOut(srcFileName, tgtFileName);

			// Create an inout  stream
			File file = new File(tgtFileName);
			tgtContent = new byte[(int)file.length()];
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(tgtFileName)));
			dataInputStream.readFully(tgtContent);           
			dataInputStream.close();

			// file.delete();

			// file= new File(srcFileName);
			// file.delete();
		}
		catch(Exception ex)
		{
			result = ex.getMessage();
			logger.error(ex.getMessage());
		}
		finally
		{
			printer.dispose();
		}
		return result;
	}
	
	public String convertLocal(){
		Printer printer = new Printer();
		Logger logger = LoggerFactory.getLogger(PDFXConverter.class);
		String result = "Success!";

		try
		{

			PrintJob printjob = printer.getPrintJob();
			PDFSetting pdfSettings = printjob.getPDFSetting();


			pdfSettings.setStandardPdfAConformance(prnPdfAConformance.PRN_PDFA_CONFORM_NONE);
			pdfSettings.setStandardPdfXConformance(prnPdfXConformance.PRN_PDFX_CONFORM_1A);
			pdfSettings.setStandardCmykProfile("USWebCoatedSWOP.icc");


			printjob.PrintOut("c:\\test\\restDemo\\Statement of Account-Original.doc", "c:\\test\\restDemo\\Statement of Account-Original.pdf");

		}
		catch(Exception ex)
		{
			result = ex.getMessage();
			logger.error(ex.getMessage());
		}
		finally
		{
			printer.dispose();
		}
		return result;
	}
}