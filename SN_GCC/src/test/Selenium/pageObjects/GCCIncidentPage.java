package pageObjects;

import org.openqa.selenium.By;

public class GCCIncidentPage
{
	//Objects on GCC incident creation page - Client information -  High Incident
	public static By textbox_incidentNumber=By.xpath("//INPUT[@id='sys_readonly.u_bps_gcc_incident.u_incident_id']");
	public static By textbox_nextNotificationDue=By.xpath("//INPUT[@id='u_bps_gcc_incident.u_next_notification_due']");
/*	public static By lookup_companySearch =By.xpath("//A[@id='lookup.incident.company']");
	
//	public static By textbox_companySearch =By.xpath("//INPUT[@id='30accf4137835e003e75db9643990e0b_text']");
	public static By textbox_companySearch =By.xpath("//INPUT[@id='9290db8137835e003e75db9643990e74_text']");
*/	
	public static By textbox_client=By.xpath("//INPUT[@id='sys_display.u_bps_gcc_incident.u_client_name']");
	public static By button_lookupClient = By.xpath("//a[@id='lookup.u_bps_gcc_incident.u_client_name']");
	public static By link_clientName(String SDL, String ContractName, String client){return By.xpath("//a[text()='"+SDL+"']/parent::td/preceding-sibling::td[text()='"+ContractName+"']/preceding-sibling::td/a[text()='"+client+"']");}
	public static By textbox_masterContract=By.xpath("//INPUT[@id='sys_display.u_bps_gcc_incident.u_client_name.u_master_contract_name']");
	public static By textbox_SDL =By.xpath("//*[@id='sys_display.u_bps_gcc_incident.u_client_name.u_sdl']");
	public static By textbox_accountLead =By.xpath("//*[@id='sys_display.u_bps_gcc_incident.u_client_name.u_account_lead']");
	public static By textbox_primeMD =By.xpath("//*[@id='sys_display.u_bps_gcc_incident.u_client_name.u_prime_md']");
	public static By textbox_SDO = By.xpath("//input[@id='sys_display.u_bps_gcc_incident.u_client_name.u_sdo']");
	public static By textbox_SME = By.xpath("//*[@id='u_bps_gcc_incident.u_sme']");
	public static By textbox_deliveryLocation = By.xpath("//*[@id='u_bps_gcc_incident.u_client_name.u_delivery_location']");
	public static By textbox_geoRegion =By.xpath("//*[@id='sys_display.u_bps_gcc_incident.u_client_name.u_geo_region']");
	public static By textbox_assignedTo =By.xpath("//*[@id='sys_display.u_bps_gcc_incident.u_assigned_to']");
	public static By textbox_closureDate = By.xpath("//*[@id='u_bps_gcc_incident.u_target_closure_date']");
	public static By textbox_incidentDate = By.xpath("//*[@id='u_bps_gcc_incident.u_date_of_incident']");
	public static By textbox_description = By.xpath("//*[@id='u_bps_gcc_incident.u_incident_description']");
	
	public static By textbox_RCA_Narrative = By.id("u_bps_gcc_incident.u_rca_narrative");
	public static By textbox_causes = By.id("select_0u_bps_gcc_incident.u_causes");
	public static By button_edit_cause = By.id("u_bps_gcc_incident.u_watch_list_unlock");
	public static By button_add_remove_cause = By.xpath("//*[@id='u_bps_gcc_incident.u_causes_edit']//button[@data-original-title='Add/Remove multiple']");
	public static By button_lock_cause = By.id("u_bps_gcc_incident.u_causes_lock");
	public static By textbox_collection = By.id("_sys_user");
	public static By list_causes_left = By.id("select_0");
	public static By list_causes_right = By.id("select_1");
	public static By button_Add_cause = By.xpath("//*[@id='addRemoveButtons']/a[@data-original-title='Add']");
	public static By button_Remove_cause = By.xpath("//*[@id='addRemoveButtons']/a[@data-original-title='Remove']");
	public static By button_Save_cause = By.id("sysverb_save");
	/*public static By textbox_searchIncident_all = By.xpath("//input[@placeholder='Search']");
	public static By textbox_closeNotes = By.xpath("//*[@id='incident.close_notes']");
	public static By textbox_resolution = By.xpath("//*[@id='sys_display.incident.u_resolution']");
	public static By textbox_escalatedIncidentCount = By.xpath("//*[@id='sys_readonly.incident.u_escalated']");
	public static By textbox_incidentReopenCount = By.xpath("//*[@id='incident.reopen_count']");
	public static By textbox_description_incidentTask = By.xpath("//*[@id='u_incident_task.description']");
	public static By textbox_vendorTicket_incidentTask = By.xpath("//*[@id='u_incident_task.u_vendor_ticket_number']");
	public static By textbox_reportedToVendor_incidentTask =By.xpath("//*[@id='u_incident_task.u_reported_to_vendor']");
	public static By textbox_vendorResponded_incidentTask =By.xpath("//*[@id='u_incident_task.u_vendor_responded']");
	public static By textbox_vendorResolved_incidentTask = By.xpath("//*[@id='u_incident_task.u_vendor_resolved']");
	public static By textbox_assignmentGroup_problem = By.xpath("//*[@id='sys_display.problem.assignment_group']");
	public static By textbox_description_problem = By.xpath("//*[@id='problem.description']");
	public static By textbox_categorization_change = By.xpath("//*[@id='sys_display.change_request.u_categorization']");
	public static By textbox_assignmentGroup_change = By.xpath("//*[@id='sys_display.change_request.assignment_group']");
	public static By textbox_ownerGroup_change = By.xpath("//*[@id='sys_display.change_request.u_owner_group']");*/
	public static By dropdown_HRI = By.xpath("//select[@id='u_bps_gcc_incident.u_client_name.u_hri_list']");
	//public static By dropdown_risk_change = By.id("change_request.risk");
	public static By dropdown_offering = By.xpath("//select[@id='u_bps_gcc_incident.u_client_name.u_offering']");
	public static By dropdown_exposure = By.xpath("//select[@id='u_bps_gcc_incident.u_exposure']");
	public static By dropdown_subOffering = By.xpath("//select[@id='u_bps_gcc_incident.u_sub_offering']");
	public static By textbox_other_subOffering = By.xpath("//input[@id='u_bps_gcc_incident.u_other_sub_offering']");
	public static By dropdown_incidentType = By.xpath("//select[@id='u_bps_gcc_incident.u_incident_type']");
	public static By textbox_other_incidentType = By.xpath("//input[@id='u_bps_gcc_incident.u_other_incident_type']");
	public static By dropdown_status = By.xpath("//select[@id='u_bps_gcc_incident.u_status']");
	public static By dropdown_priority = By.xpath("//select[@id='u_bps_gcc_incident.u_priority']");
	
	
	
	public static By checkbox_closedOnInitial = By.xpath("//*[@id='ni.incident.u_closed_on_initial']");
	public static By dropdown_contactType = By.xpath("//select[@id='incident.contact_type']");
	public static By dropdown_incidentState = By.xpath("//*[@id='incident.state']");
	public static By dropdown_resolutionReason = By.xpath("//*[@id='incident.u_resolution_reason']");
	public static By dropdown_slaBreachReason = By.xpath("//*[@id='incident.u_sla_breach_reason']");
	public static By dropdown_closeCode = By.xpath("//*[@id='incident.close_code']");
	
	public static By button_saveIncident =By.xpath("//*[@id='sysverb_insert_and_stay']");
	public static By button_resolveIncident =By.xpath("//*[@id='resolve_incident']");
	public static By button_updateIncident =By.xpath("//button[@id='sysverb_update']");
	public static By button_newIncidentTask = By.xpath("//button[@id='sysverb_new' and text()='New']");
	public static By button_submit_incidentTask = By.xpath("//*[@id='sysverb_insert']");
	public static By button_moreOptions = By.id("toggleMoreOptions");
	public static By button_email = By.id("email_client_open");
	public static By dropdown_emailTemplate1 = By.id("s2id_canned");
	public static By dropdown_emailTemplate2 = By.id("canned");
	
	
	public static By link_listOfGCC = By.xpath("//a[@title='List of GCC Incidents']");
	public static By escalateIncident = By.xpath("//div[@class='context_item' and text()='Escalate Incident']");
	public static By saveIncident = By.xpath("//div[@class='context_item' and text()='Save']");
	public static By createProblem = By.xpath("//div[@class='context_item' and text()='Create Problem']");
	public static By createChange = By.xpath("//div[@class='context_item' and text()='Create Normal Change']");
	public static By link_createNewReport =By.xpath("//a[@title='Create a new report']");
	//public static By link_runReport = By.xpath("//a[@title='View / Run']");
	public static By link_runReport = By.xpath("//a[@title='Reporting portal']");
	
	
	public static By tabbed_root_analysis = By.xpath("//*[@id='tabs2_section']//span[(contains(text(), 'Root') and contains(text(), 'Cause') and contains(text(), 'Analysis']");
	public static By tabbed_case_Activity = By.xpath("//span[contains(text(), 'Case') and contains(text(), 'Activity')]");
	public static By tabbed_incident_case = By.xpath("//span[contains(text(), 'Incident') and contains(text(), 'Case')]");
	
//	/*/aChild[normalize-space() = 'Hello World']
//	//input[contains(@value,'Import') and contains(@value ,'Selected')and contains(@value ,'File')]
	
	public static By tabbed_task_SLA = By.xpath("//span[contains(text(), 'Task') and contains(text(), 'SLAs')]");
	public static By textbox_correctiveTasks = By.xpath("//span[contains(text(), 'Corrective') and contains(text(), 'Tasks')]");
	public static By textbox_preventiveTasks = By.xpath("//span[contains(text(), 'Preventive') and contains(text(), 'Tasks')]");
	
	public static By chkBox_correctiveTasks(String s){return By.xpath("//a[contains(text(),' "+s+" ')]/ancestor::td/preceding-sibling::td//input[contains(@id,'check_u_bps_gcc_incident.u_bps_gcc_corrective_tasks.')]");}
	public static By chkBox_preventiveTasks(String s){return By.xpath("//a[contains(text(),\""+s+"\")]/ancestor::td/preceding-sibling::td//input[contains(@id,'check_u_bps_gcc_incident.u_bps_gcc_preventative_task')]");}
	public static By chkBox_taskSLA(String s){return By.xpath("//a[contains(text(),\""+s+"\")]/ancestor::td/preceding-sibling::td//input[contains(@id,'check_u_bps_gcc_incident.task_sla.task')]");}
	
	public static By tab_notes = By.xpath("//span[2][text()='Notes']");
	public static By textbox_additionalComments = By.id("incident.comments");
	
}
