package com.project.integratedservices.retofit;


import com.project.integratedservices.integratedServicesForAllTypes.view.CircularActivity;
import com.project.integratedservices.repository.authencationRepo.remote.response.AttendanceCheckResponse;
import com.project.integratedservices.repository.authencationRepo.remote.response.DailyAttendanceCheck;
import com.project.integratedservices.repository.authencationRepo.remote.response.DailyAttendanceTime;
import com.project.integratedservices.repository.authencationRepo.remote.response.loginModel.LoginResponse;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AgentCommisionTotal;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AgentCommissionDetailsResponse;

import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AgentDetail;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AlertMessageResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AllBranchDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplicationNoWisePremiumAmount;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplicationNumberWisePaymentNewBusinessItem;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplicationNumberWisePaymentNewPayment;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplicationNumberWisePaymentResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ApplyLeaveResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AssignCustomerResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AttendanceDetailsResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AttendanceResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AvailablePlansResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchWiseBusinessResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BranchwiseJoiningResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.BusinessReportResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.ChangePasswordResponseModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.CollectionDateItem;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.CompanyNamesResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndFollowUpVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndInterestedVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndNotInterestedVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.KycResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISBusinessSummaryResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCollectionRegisterResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISCompanyDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISIndividualBusinessResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.MISSuggestionComplainResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.NewCustomerListResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PhaseDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PlanDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PolicyNamesResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.PremiumCalculatorList;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.SalesDetailsResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.StartVisitCheckResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.StartVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.StatementDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.TeamDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VisitStateCheckResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint1Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint2Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint3Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint4Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.VoucherPrint5Response;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_detailis_promotion_detail.PromotionDetailsResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_bank_detail.BankDetailResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_field_work.FieldWorkResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_payment_detail.PaymentDetail;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_voucher.VoucherDetail;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.branch_details.BranchDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.circular_response.CircularResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.grade_details.GradeDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.intro_details.IntroDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.menu_status_response.MenueStatusResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.DeleteSms;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response.SmsDetailsResposne;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.mis_agent_joining_details.MisAgentJoiningDetails;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.new_joinee_final_submit.NewJoiningFinalRespons;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.phas_master.PhaseMasterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApis {

//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("LoginDetails/LoginValues")
//    Call<List<LoginResponse>> callLogIn(@Query("Userid") String Userid, @Query("Passowrd") String Passowrd, @Query("imei_code") String imei_code, @Query("tokenid") String tokenid);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("LoginDetails/LoginValues")
    Call<List<LoginResponse>> callLogIn(@Query("AgentCode") String agentCode);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("LoginDetails/LoginValuesDetails")
    Call<List<UserDetailsResponse>> getUserDetails(@Query("AgentCode") String agentCode, @Query("ImeiCode") String ImeiCode, @Query("Token") String Token);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/AttandanceStatusCheck")
    Call<List<DailyAttendanceCheck>> attendanceStatusCheck(@Query("AgentCode_Attendance") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/StatEndTime")
    Call<List<DailyAttendanceTime>> attendanceTime(@Query("AgentCode_SETime") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AvailablePlan/PlanNameDetails")
    Call<List<AvailablePlansResponse>> getAvailablePlans();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AvailablePlan/AvailablePlanDetails")
    Call<List<PlanDetailsResponse>> getSelectedPlansDetails(@Query("PlanCompanyName") String selectedPlanName);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/StartAttendance")
    Call<List<AttendanceResponse>> startAttendance(@Query("AgentCode") String agentCode, @Query("startTime") String startTime, @Query("start_latitude") String start_latitude, @Query("start_longitude") String start_longitude);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/EndAttendance")
    Call<List<AttendanceResponse>> endAttendance(@Query("AgentCode") String agentCode, @Query("EndTime") String endTime, @Query("end_latitude") String end_latitude, @Query("end_longitude") String end_longitude);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("ChangePassword/ChangePasswordDetails")
    Call<List<ChangePasswordResponseModel>> changePassword(@Query("AgentCode") String agentCode, @Query("oldPassword") String oldPassword, @Query("NewPassword") String newPassword);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("TeamDetails/TeamAll")
    Call<List<TeamDetailsResponse>> getTeamDetails(@Query("AgentCode") String agentCode, @Query("TeamNo") String teamNo);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("BranchDetails/AllBranchDetails")
    Call<List<AllBranchDetailsResponse>> getAllBranch();


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("SelfDetails/SelfBusinessDetails")
    Call<List<BusinessReportResponsePojo>> getBusinessReport(@Query("AgentCode") String agentCode, @Query("StartDate") String startDate, @Query("EndDate") String endDate, @Query("Type") String type,@Query("LGCode") String LGCode);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/AttendanceDetails")
    Call<List<AttendanceDetailsResponsePojo>> getAttendatnceDetails(@Query("AgentCode") String agentCode, @Query("Month") String month, @Query("Year") String year);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/SalesDetails")
    Call<List<SalesDetailsResponsePojo>> getSalesDetails(@Query("AgentCode") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Attendance/NewAssignCustomer")
    Call<List<AssignCustomerResponsePojo>> assignCustomer(@Query("AgentCode") String agentCode, @Query("AssignAgentCode") String assignAgentCode,
                                                          @Query("CustomerName") String customerName, @Query("Address") String address,
                                                          @Query("MobileNumber") String mobileNumber, @Query("PinCode") String pinCode,
                                                          @Query("EmailID") String email);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/NewCustomerDetails")
    Call<List<NewCustomerListResponsePojo>> getNewCustomers(@Query("AgentCodeInt") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/NewCustomerDetailsWithCustomerID")
    Call<List<NewCustomerListResponsePojo>> getCustomerDetails(@Query("CustomerId") String customerId);

//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("Customer/FollowUpButtonList")
//    Call<List<FollowUpCustomersResponsePojo>> getFollowupCustomers(@Query("AgentCode") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/FollowUpButtonList")
    Call<List<NewCustomerListResponsePojo>> getFollowupCustomers(@Query("AgentCode") String agentCode);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/NewCustomerEnter")
    Call<List<AttendanceCheckResponse>> assignCustomerFromCustomer(@Query("AgentCode") String agentCode, @Query("AsignAgentCode") String assignAgentCode,
                                                                   @Query("CustomerName") String customerName, @Query("Address") String address,
                                                                   @Query("Contact") String mobileNumber, @Query("PinCode") String pinCode,
                                                                   @Query("Email") String email);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/VistiStateCheck")
    Call<List<VisitStateCheckResponse>> chekVisitStae(@Query("AgentCode") String agentCode, @Query("CustomerId") String CustomerId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/CustomerVisitStatusChecking")
    Call<List<StartVisitCheckResponse>> startVisitCheck(@Query("AgentCodeVisit") String agentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/NewVisitStart")
    Call<List<StartVisitResponse>> startVisit(@Query("AgentCode") String agentCode, @Query("CustomerId") String customerId,
                                              @Query("Lat") String latitude, @Query("Long") String longitude);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("PremiumCalculator/PremiumCalculatorDetails")
    Call<List<PremiumCalculatorList>> getList();


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/CustomerFollowUp")
    Call<List<EndFollowUpVisitResponse>> endFollowUpVisit(@Query("CustomerId") String CustomerId, @Query("FollowUpDate") String FollowUpDate, @Query("Comment") String Comment);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("Customer/CompanyNameDisp")
    Call<List<CompanyNamesResponsePojo>> getCompanyNames();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/PolicyNameDisp")
    Call<List<PolicyNamesResponsePojo>> getPolicyNames(@Query("CompanyName") String CompanyName);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("Kyc/KYCDisplay")
    Call<List<KycResponsePojo>> getKycDetails();


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/VisitInterested")
    Call<List<EndInterestedVisitResponse>> endVisitInterested(@Query("AgentCode") String AgentCode,
                                                              @Query("CustomerId") String CustomerId,
                                                              @Query("ApplicationNo") String ApplicationNo,
                                                              @Query("PolicyPlan") String PolicyPlan,
                                                              @Query("PaymentMode") String PaymentMode,
                                                              @Query("Amount") String Amount,
                                                              @Query("AddressId") String AddressId,
                                                              @Query("AgeId") String AgeId,
                                                              @Query("Id") String Id,
                                                              @Query("ChequeNo") String ChequeNo,
                                                              @Query("Comments") String Comments,
                                                              @Query("ImageName") String ImageName);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Customer/VisitNotInterested")
    Call<List<EndNotInterestedVisitResponse>> endVisitNotInterested(@Query("AgentCode") String AgentCode,
                                                                    @Query("CustomerId") String CustomerId,
                                                                    @Query("Comments") String Comments);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("LeaveApplication/ApplyLeaveApplication")
    Call<List<ApplyLeaveResponse>> applyLeave(@Query("AgentCode") String AgentCode,
                                              @Query("LeaveText") String LeaveText,
                                              @Query("LeaveStartDate") String LeaveStartDate,
                                              @Query("LeaveEndDate") String LeaveEndDate);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("SMSAlert/SMSDisplay")
    Call<List<AlertMessageResponse>> getAlertMessage(@Query("AgentCode") String AgentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISBranchDetails")
    Call<List<BranchDetailsResponse>> getMISBranchDetails(@Query("RoleId") String roleId, @Query("UserId") String userId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISCompanyDetails")
    Call<List<MISCompanyDetailsResponse>> getMISCompanyDetails();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISBranchWiseBusinessDetails")
    Call<List<BranchWiseBusinessResponse>> submitMISBRanchWiseBusinessDetails(@Query("BranchID") String branchId, @Query("startdate") String startdate, @Query("enddate") String enddate, @Query("Code") String code, @Query("Insurance") String insurance, @Query("Type") String type, @Query("CodeType") String codeType, @Query("InsuranceComp") String insuranceComp, @Query("BusinessType") String businessType);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISIndividualBusinessDetails")
    Call<List<MISIndividualBusinessResponse>> submitMISIndividualBusinessDetails(@Query("AgentCode") String AgentCode, @Query("startdate") String startdate, @Query("enddate") String enddate, @Query("roleId") String roleId,@Query("LoginId") String loginId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISBusinessSummary?Sl=1")
    Call<List<MISBusinessSummaryResponse>> submitMISBusinessSummary(@Query("startdate") String startdate, @Query("enddate") String enddate,@Query("pCode") String pCode,@Query("p_loginCode") String lCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISCollectionRegister")
    Call<List<MISCollectionRegisterResponse>> submitMISCollectionRegisterResponse(@Query("start_date") String startdate, @Query("end_date") String enddate, @Query("code") String loginCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("MIS/MISSuggestionComplain")
    Call<List<MISSuggestionComplainResponse>> submitMISSuggestionComplain(@Query("Suggestion") String suggestion, @Query("UserId") String userId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("Voucher/PhaseDetails")
    Call<List<PhaseDetailsResponse>> getPhaseDetails();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/StatementDetails")
    Call<List<StatementDetailsResponse>> getStatementDetails(@Query("PhaseId") String PhaseId, @Query("RoleId") String RoleId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/VoucherPrint1")
    Call<List<VoucherPrint1Response>> getVoucherPrint1(@Query("StementID") String StementID, @Query("AgentCode") String AgentCode,@Query("LCode") String lcode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/VoucherPrint2")
    Call<List<VoucherPrint2Response>> getVoucherPrint2(@Query("StementID1") String StementID, @Query("AgentCode1") String AgentCode,@Query("LCode1") String lcode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/VoucherPrint3")
    Call<List<VoucherPrint3Response>> getVoucherPrint3(@Query("StementID2") String StementID, @Query("AgentCode2") String AgentCode,@Query("LCode2") String lcode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/VoucherPrint4")
    Call<List<VoucherPrint4Response>> getVoucherPrint4(@Query("StementID3") String StementID, @Query("AgentCode3") String AgentCode, @Query("LCode3") String lcode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("Voucher/VoucherPrint5")
    Call<List<VoucherPrint5Response>> getVoucherPrint5(@Query("StementID4") String StementID, @Query("AgentCode4") String AgentCode, @Query("LCode4") String lcode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISBranchWiseJoining")
    Call<List<BranchwiseJoiningResponse>> getBranchwiseJoiningDetails( @Query("SDate") String startdate, @Query("EDate") String enddate,@Query("AgCode") String aCode , @Query("LoginCode")String loginCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/MISAgentAll")
    Call<List<AgentDetail>> getAgentDetails(@Query("St_D") String startdate, @Query("Et_D") String enddate,@Query("Agent_Num") String agent_num,@Query("LA_Co") String la_co);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISAgentCommissionDetails")
    Call<List<AgentCommissionDetailsResponse>> getAgentCommisionDetails(@Query("ACode") String aCode,@Query("LoginCode") String loginCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISApplicationNoWisePaymentDetails")
    Call<List<ApplicationNumberWisePaymentNewBusinessItem>> getApplicationNoWisePayment(@Query("ApplicationNo") String ApplicationNo , @Query("AGLoginCode") String la_co);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISApplicationNoWisePaymentDetails")
    Call<List<ApplicationNumberWisePaymentNewPayment>> getApplicationNoWisePaymentNEW(@Query("ApplNo") String ApplicationNo , @Query("LoginCode") String la_co);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISAgentCommissionTotal")
    Call<List<AgentCommisionTotal>> getAgentCommisionTotal(@Query("A_Code") String A_Code,@Query("L_Code") String L_Code);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISApplicationNoWisePaymentDetails")
    Call<List<ApplicationNoWisePremiumAmount>> getApplicationNoWisePremiumAmount(@Query("ANo") String A_Code,@Query("LCode") String L_Code);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/MISAgentJoiningDetails")
    Call<List<MisAgentJoiningDetails>> getAgentJoiningDetailsDetails(@Query("StDate") String startdate, @Query("EtDate") String enddate,@Query("AgentNumber") String agent_num,@Query("LACode") String la_co);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/PhaseMaster")
    Call<List<PhaseMasterResponse>> getPhasMaster();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/IntroDetails")
    Call<List<IntroDetails>> getIntroDetails(@Query("p_agentCode") String introducerCode,@Query("p_loginCode") String agentId);
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/BranchDetailsApp?PBranch=0")
    Call<List<BranchDetails>> getjoiningBranch();
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/GradeDetailsApp")
    Call<List<GradeDetails>> getGradeNameSpinnerData(@Query("Grade")String grade);
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("AgentJoining/FinalAgentJoining")
    Call<List<NewJoiningFinalRespons>> fialSubmitForNewAgent(@Query("AGName") String agName, @Query("Gender")String gender, @Query("DOB")String dob, @Query("Introducer")String introducer, @Query("BranchID")String branchId, @Query("AddedBy")String addedBy, @Query("EnrollAmount")String enrollAmount, @Query("RankID")String rankId, @Query("Code")String code, @Query("PrintStatus")String printStatus, @Query("CompanyID")String compId, @Query("PhaseID")String phaseId, @Query("PhoneNo")String phoneNumber);
    @GET("AgentDetails/FieldWorkDetails")
    Call<List<FieldWorkResponse>> fetchFieldWorkDetails(@Query("AgCode") String agentCode, @Query("AGLogin") String loggedInAgentsId);
    @GET("AgentDetails/BankDetails")
    Call<List<BankDetailResponse>> fetchBankDetails(@Query("Ag_Code") String agentCode, @Query("AG_Login") String loggedInAgentsId);
    @GET("AgentDetails/PromotionDetails")
    Call<List<PromotionDetailsResponse>> fetchPromotionDetails(@Query("AgCodeP") String agentCode, @Query("AGLoginP") String loggedInAgentsId);
    @GET("AgentDetails/VoucherDetails")
    Call<List<VoucherDetail>> fetchVoucherDetails(@Query("AgCodeV") String agentCode, @Query("AGLoginV") String loggedInAgentsId);
    @GET("AgentDetails/PaymentDetails")
    Call<List<PaymentDetail>> fetchPaymentDetails(@Query("AgCodePD") String agentCode, @Query("AGLoginPD") String loggedInAgentsId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("SMSAlert/SMSDisplay")
    Call<List<SmsDetailsResposne>> getSmsDetails(@Query("ACode") String AgentCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("SMSAlert/SMSDisplay")
    Call<List<DeleteSms>> deleteSms(@Query("Slno") String Slno);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MenuPermission/MenuPermissionDetails")
    Call<List<MenueStatusResponse>> getMenueStatus(@Query("Rollid")String rolId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("Circular")
    Call<List<CircularResponse>> getCircularResponse(@Query("SDate")String startDate, @Query("EDate") String endDate);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/RegisterDateRange?")
    Call<List<CollectionDateItem>> getCollectionDate(@Query("AgCode")String agCode);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("MIS/CollectionFYDetails?")
    Call<List<CollectionDateItem>> getCollectionDetails(@Query("Code")String code);

}
