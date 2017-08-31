package pageObjects;

import org.openqa.selenium.By;

public class HomePage {
	
	//ATLAS
	/*public static By txtHomeSrch = By.xpath("//*[@id='spnNavbarSearchIcon']");
	public static By lnkMore =By.xpath("//*[@id='menu_more']");
	public static By lnkContactUs = By.xpath("//*[@id='a_more_ContactUs']");
	public static By txtSearchBox = By.xpath("(//*[@id='txtNavbarSearch'])[1]");*/
	
	
	//SERVICE NOW
	public static By changeDomain =By.xpath("//button[@id='nav-settings-button']");
	public static By closeDomain =By.xpath("//div[@id='settings_modal']");
	public static By Forms_tab = By.xpath("//*[@href='#settings_form']");
	public static By tabbedForms = By.xpath("//label[@for='33.48397466316955tabbed_forms']");
	public static By domainSelect =By.xpath("//*[@id='domain_picker_select']");
	public static By closePopup = By.xpath("//*[@class='btn close icon-cross']");
	
	public static By link_serviceCatalog= By.xpath("//A[text()='Service Catalog']");
//	public static By link_serviceCatalog= By.xpath("//LI[@id='module.345046fa37b3c600812bd5c543990eff']");
	
	//*[@id='345046fa37b3c600812bd5c543990eff']
	
	public static By table_catalog= By.xpath("//TABLE[@id='homepage_grid']");
	public static By SIQ= By.xpath("//A[@id='item_link_b1deb33a0f989240e65a4b9ce1050e51']");
	public static By requestedFor = By.xpath("//INPUT[@name='sys_display.IO:63ab540c2bd7420081e23c7e17da1507']");
	public static By country = By.xpath("//SELECT[@name='IO:ab4cd40c2bd7420081e23c7e17da15c0']");
	public static By description = By.xpath("//INPUT[@name='IO:631371df37ffc600812bd5c543990e3e']");
	public static By submitRequest = By.xpath("//BUTTON[@id='order_now']");
	public static By requestConfirmationMessage = By.xpath("//div[@id='sc_order_status_intro_text']");
	public static By requestId= By.xpath("//a[@id='requesturl']");
	public static By link_bookmark= By.xpath("//a[@id='permalink']");
	public static By full_description = By.xpath("//*[@id='sc_request.description']");
	public static By button_update = By.xpath("//button[@id='sysverb_update']");
	
	public static By link_items= By.xpath("//a[@id='8bb3e48cc0a8006401c49094e7db12cf']");
	public static By link_requestItem= By.xpath("//a[@class='linked formlink']");
	public static By requestItem_status =By.xpath("//select[@id='sc_req_item.state']");
	public static By requestItem_assignmentGroup =By.xpath("//input[@id='sys_display.sc_req_item.assignment_group']");
	public static By requestItem_category = By.xpath("//select[@id='sc_req_item.u_category']");
	public static By requestItem_type = By.xpath("//select[@id='sc_req_item.u_sub_category']");
	public static By requestItem_assignedTo =By.xpath("//input[@id='sys_display.sc_req_item.assigned_to']");
	public static By requestItem_notes_description = By.xpath("//*[@id='sc_req_item.description']");
	public static By requestItem_notes_editWatchList = By.xpath("//button[@id='sc_req_item.watch_list_unlock']");
	public static By requestItem_notes_addEmail = By.xpath("//input[@id='text.value.sc_req_item.watch_list']");
	public static By requestItem_notes_lock = By.xpath("//button[@id='sc_req_item.watch_list_lock']");
	
//	public static By requestItem_header =By.xpath("//div[@id='sc_req_item.form_header']");
	public static By requestItem_header =By.xpath("//span[@class='form_display_value']");
	public static By sendEmail = By.xpath("//div[@class='context_item' and text()='Send Custom Email']");
	public static By email_From = By.xpath("//input[@id='sys_display.u_email_client.u_from']");
	
	public static By email_To = By.xpath("//button[@data-ref='u_email_client.u_to_whom']");
	public static By email_input = By.xpath("//input[@name='sys_display.u_email_client.u_to_whom']");
	
	
	public static By email_template = By.xpath("//input[@id='sys_display.u_email_client.u_template']");
	public static By email_subject = By.xpath("//input[@id='u_email_client.u_subject']");
//	public static By email_message = By.xpath("//iframe[@id='u_email_client.u_message_ifr']");u_email_client.u_message
	public static By email_ccWatchList = By.xpath("//input[@id='ni.u_email_client.u_cc_watch_list' and @type='checkbox']");
	public static By email_message = By.xpath("//*[@id='tinymce']");
	public static By email_sendButton = By.xpath("//button[text()='Send Mail']");
	
	public static By notes_activity = By.xpath("//*[@name='activity_0_div']");
	
	
//	public static By tabbed_notes_link = By.xpath("//span[@class='tab_caption_text']");
	public static By tabbed_notes_link = By.xpath("//span[text()='Notes']");
	
	
	public static By panel_incident = By.xpath("//*[@id='div.dead1309c611228701e2brda7b4252474']");
//	public static By link_createNewIncident = By.xpath("//*[@id='module.14641d70c611228501114133b3cc88a1' and @modulename='Create New']");
//	public static By link_createNewIncident = By.xpath("//LI[@id='module.14641d70c611228501114133b3cc88a1']");
	public static By link_createNewGCC = By.xpath("//*[@id='45f43128db64c3000fc1f58dae96196b']");
	public static By panel_problem = By.xpath("//*[@id='div.deed4a24c6112287013cba41a9e205ca']");
	public static By link_createNewProblem = By.xpath("//LI[@id='module.a1beba50c611227801908558c921ab78']");
	
	public static By panel_change = By.xpath("//*[@id='div.deecb70ec611228700bafda6dde05102']");
	public static By link_createNewChange = By.xpath("//LI[@id='module.323bb07bc611227a018aea9eb8f3b35e']");
	
	
	
	
	//CADENCE
	public static By cad_signout =By.xpath("//A[@Text='Sign Out']");
	public static By cad_mainMenu_Recon =By.xpath("//A[@Text='RECONCILIATIONS']");
	public static By cad_mainMenu_1_Recon =By.xpath("//A[@textContents='Reconciliations'][2]");
	
	public static By cad_subMenu_Recon_ReconDue =By.xpath("//A[@textContents='Reconciliations Due']");
				
}
