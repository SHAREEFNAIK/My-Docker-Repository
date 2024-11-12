package com.naik.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.naik.bindings.CoSummery;
import com.naik.entity.ApplicationRegistrationEntity;
import com.naik.entity.CoTriggerEntity;
import com.naik.entity.DcCaseEntity;
import com.naik.entity.EligibilityDetailsEntity;
import com.naik.repository.IAppRegisterRepository;
import com.naik.repository.ICoTriggerRepository;
import com.naik.repository.IDcCaseRepository;
import com.naik.repository.IEligibilityDetailsRepository;
import com.naik.utils.emailUtils;
@Service
public class CorespondenceServiceMgmtImpl implements ICorespondenceServiceMgmt{

	@Autowired
	private ICoTriggerRepository triggerRepo;
	@Autowired
	private IEligibilityDetailsRepository elgiRepo;
	@Autowired
	private IDcCaseRepository caseRepo;
	@Autowired
	private IAppRegisterRepository appRepo;
	@Autowired
	private emailUtils mailUtil;
	Integer successTriggers=0;
	Integer pendingTriggers=0;
	
	@Override
	public CoSummery processPendingTriggers() {
		ApplicationRegistrationEntity appEntity=null;
		EligibilityDetailsEntity elgiEntity=null;
		
		// get all pending triggers
		List<CoTriggerEntity> triggerList= triggerRepo.findByTriggerStatus("pending");
		
		  // prepare cosummery report
		  CoSummery summery = new CoSummery();
		  summery.setTotalTriggers(triggerList.size());
		 //process pending trigger in multi threaded env..using executor framework
		  ExecutorService executorService= Executors.newFixedThreadPool(10);
		  ExecutorCompletionService<Object> pool=new ExecutorCompletionService<Object>(executorService);
		
		//process each pending trigger 
		for(CoTriggerEntity triggerEntity:triggerList) {
			pool.submit(()-> {
				try {
				processTrigger(summery,triggerEntity);
				successTriggers++;
			}
			catch (Exception e) {
				e.printStackTrace();
				pendingTriggers++;
			}
			return null;
		});
		}//for
		summery.setPendingTriggers(pendingTriggers);
		summery.setSuccessTriggers(successTriggers);
		
		return summery;
	}// process pending trigger method
	
	private ApplicationRegistrationEntity processTrigger(CoSummery summery,CoTriggerEntity triggerEntity) throws Exception {
	
		ApplicationRegistrationEntity appEntity=null;
		// get EligibilityDetails based on caseNo
		EligibilityDetailsEntity elgiEntity= elgiRepo.findByCaseNo(triggerEntity.getCaseNo());
		// get App id based on caseNo
		Optional<DcCaseEntity> optCaseEntity=caseRepo.findById(triggerEntity.getCaseNo());
		if (optCaseEntity.isPresent()) {
			DcCaseEntity caseEntity= optCaseEntity.get();
			Integer appId= caseEntity.getAppId();
			//get citizen details based on appid
			Optional<ApplicationRegistrationEntity> optAppEntity=appRepo.findById(appId);
			if(optAppEntity.isPresent()) {
				appEntity= optAppEntity.get();
			}
		}
		// generate pdf doc having elgibility Details and send that pdf doc to email
		generatePdfAndSendMail(elgiEntity,appEntity);
		return appEntity;	
	}// process trigger method

	private void generatePdfAndSendMail(EligibilityDetailsEntity elgiEntity, ApplicationRegistrationEntity appEntity) throws Exception {
		//create document object
		Document document = new Document(PageSize.A4);
		//create pdf file to write content to it
		File file= new File(elgiEntity.getCaseNo()+"pdf");
		FileOutputStream fos = new FileOutputStream(file);
		// use pdfwriter to write doc, res obj
		PdfWriter.getInstance(document,fos);
		// open document
		 document.open();
		 // define font of paragraph
		 Font font= FontFactory.getFont(FontFactory.TIMES_BOLD);
		 font.setSize(30);
		 font.setColor(Font.BOLDITALIC, 30, 20);
		 
		 //create paragraph having content and having above font style
		 Paragraph para = new Paragraph("Plan Approval/Denial Communication", font);
		 para.setAlignment("center");
		 //add para to doc
		 document.add(para);
		 
		// Display Search results as pdf table
				 PdfPTable table = new PdfPTable(10);
				 table.setSpacingBefore(5.0f);
				 table.setWidthPercentage(90);
				 table.setWidths(new float[] {5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f});
				 
				 // prepare heading rows cells in pdf tables
				 PdfPCell cell = new PdfPCell();
				 cell.setBackgroundColor(Color.green);
				 cell.setPadding(5);
				 Font cellfont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				 cellfont.setColor(Color.red);
				 
				 cell.setPhrase(new Phrase("TraceId",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("CaseNo",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("HolderName",cellfont));
				 table.addCell(cell);
				 cell.setPhrase(new Phrase("HolderSSN",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("PlanName",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("PlanStatus",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("planStartDate",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("PlanEndDate",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("BenefitAmt",cellfont));
				 table.addCell(cell);
				 
				 cell.setPhrase(new Phrase("DenialReason",cellfont));
				 table.addCell(cell);
				 
				//add datacell to pdftable
				 
					 table.addCell(String.valueOf(elgiEntity.getEdTraceId()));
					 table.addCell(String.valueOf(elgiEntity.getCaseNo()));
					 table.addCell(elgiEntity.getHolderName());
					 table.addCell(String.valueOf(elgiEntity.getHolderSSN()));
					 table.addCell(elgiEntity.getPlanName());
					 table.addCell(elgiEntity.getPlanStatus());
					 table.addCell(String.valueOf(elgiEntity.getStartDate()));
					 table.addCell(String.valueOf(elgiEntity.getEndDate()));
					 table.addCell(String.valueOf(elgiEntity.getBenefitAmount()));
					 table.addCell(String.valueOf(elgiEntity.getDenialReason()));
				
					 // add table to document
					 document.add(table);
					 //close doc
					 document.close();
					 // send the generated pdf doc as email message
					 String subject="plan Approval/Denial mail";
					 String body= " Hello Mr/Miss/Mrs."+appEntity.getFullName()+",This mail contains Complete Details Plan approval/Denial";
					 mailUtil.sendmails(appEntity.getEmail(), subject, body, file);
					 // update Co trigger table
					 updateCotrigger(elgiEntity.getCaseNo(),file);
					 
		
	}

	private void updateCotrigger(Integer caseNo, File file)throws Exception {
		// check trigger availability based on caseno
		CoTriggerEntity triggerEntity= triggerRepo.findByCaseNo(caseNo);
		// get Byte[] representing pdf doc content
		byte[] pdfContent = new byte[(int)file.length()];
		FileInputStream fis =new FileInputStream(file);
		fis.read(pdfContent);
		if(triggerEntity!=null) {
			triggerEntity.setCoNoticePdf(pdfContent);
			triggerEntity.setCoTriggerStatus("completed");
			triggerRepo.save(triggerEntity);	
		}
		fis.close();
	}
	
}// class
