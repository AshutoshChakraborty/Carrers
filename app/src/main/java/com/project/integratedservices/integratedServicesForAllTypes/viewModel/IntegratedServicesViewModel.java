package com.project.integratedservices.integratedServicesForAllTypes.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.project.integratedservices.repository.authencationRepo.remote.response.AttendanceCheckResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.ApplyLeaveRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AssignCustomerActivityRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AssignCustomerFragmentRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AttendanceDetailsRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AttendanceRequestModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.BusinessReportRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.ChangePasswordRequestModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.SalesDetailsRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.StartVisitRequest;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.TeamAllRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.VisitInterestedRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.VisitNotInterestedRequestPojo;
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
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.CollectionReportResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.CompanyNamesResponsePojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndFollowUpVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndInterestedVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.EndNotInterestedVisitResponse;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.FollowUpCustomersResponsePojo;
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
import com.project.integratedservices.retofit.RemoteClient;
import com.project.integratedservices.retofit.RetrofitApis;
import com.project.supportClasses.SharedPref;

import java.lang.reflect.Field;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class IntegratedServicesViewModel extends ViewModel {
    private RetrofitApis apiClient = RemoteClient.getRetrofitApi();
    private MutableLiveData<String> apiError;
    private MutableLiveData<List<AvailablePlansResponse>> availablePlansResponseMutableLiveData;
    private MutableLiveData<List<PlanDetailsResponse>> planDetailsResponseMutableLiveData;
    private MutableLiveData<List<AttendanceResponse>> attendanceStartResponseLiveData;
    private MutableLiveData<List<AttendanceResponse>> attendanceEndResponseLiveData;
    private MutableLiveData<List<ChangePasswordResponseModel>> changePasswordResponseLiveData;
    private MutableLiveData<List<TeamDetailsResponse>> teamDetailsResponseLiveData;
    private MutableLiveData<List<AllBranchDetailsResponse>> allBranchResponseLiveData;
    private MutableLiveData<List<BusinessReportResponsePojo>> businessReportResponseLiveData;
    private MutableLiveData<List<AttendanceDetailsResponsePojo>> attendanceDetailsLiveData;
    private MutableLiveData<List<SalesDetailsResponsePojo>> salesDetailsLiveData;
    private MutableLiveData<List<AssignCustomerResponsePojo>> assignCustomerLiveData;
    private MutableLiveData<List<NewCustomerListResponsePojo>> newCustomerListLiveData;
    private MutableLiveData<List<FollowUpCustomersResponsePojo>> followupCustomerListLiveData;
    private MutableLiveData<List<AttendanceCheckResponse>> assignCustomerFromCustomerResponse;
    private MutableLiveData<List<StartVisitResponse>> startVisitResponse;
    private MutableLiveData<List<StartVisitCheckResponse>> startVisitCheckResponse;
    private MutableLiveData<List<PremiumCalculatorList>> premiumCalculatorlist;
    private MutableLiveData<List<EndFollowUpVisitResponse>> endFollowUpVisitLiveData;
    private MutableLiveData<List<CompanyNamesResponsePojo>> CompanyNamesResponseLiveData;
    private MutableLiveData<List<PolicyNamesResponsePojo>> policyNamesResponseLiveData;
    private MutableLiveData<List<KycResponsePojo>> kycResponseLiveData;
    private MutableLiveData<List<EndInterestedVisitResponse>> endInterestedVisitResponseLiveData;
    private MutableLiveData<List<EndNotInterestedVisitResponse>> endNotInterestedVisitResponseLiveData;
    private MutableLiveData<List<ApplyLeaveResponse>> applyLeaveResponseLiveData;
    private MutableLiveData<List<AlertMessageResponse>> alertResponseLiveData;
    private MutableLiveData<List<DeleteSms>> deleteSmsLiveData;
    private MutableLiveData<List<MenueStatusResponse>> menueStatusLiveData;
    private MutableLiveData<List<CircularResponse>> circularResponseLiveData;
    private MutableLiveData<List<SmsDetailsResposne>> smsDetailsLiveData;
    private MutableLiveData<List<VisitStateCheckResponse>> visitStateCheckResponseLiveData;
    private MutableLiveData<List<BranchDetailsResponse>> misBranchDetailsResponseLiveData;
    private MutableLiveData<List<MISCompanyDetailsResponse>> misCompanyDetailsResponseLiveData;
    private MutableLiveData<List<BranchWiseBusinessResponse>> branchWiseBusinessResponseLiveData;
    private MutableLiveData<List<MISIndividualBusinessResponse>> misIndividualBusinessResponseLiveData;
    private MutableLiveData<List<MISBusinessSummaryResponse>> misBusinessSummaryResponseLiveData;
    private MutableLiveData<List<MISCollectionRegisterResponse>> misCollectionRegisterResponseLiveData;
    private MutableLiveData<List<MISSuggestionComplainResponse>> misSuggestionComplainResponseLiveData;
    private MutableLiveData<List<PhaseDetailsResponse>> phaseDetailsResponseLiveData;
    private MutableLiveData<List<StatementDetailsResponse>> statementDetailsResponseLiveData;
    private MutableLiveData<List<VoucherPrint1Response>> voucherPrint1ResponseLiveData;
    private MutableLiveData<List<VoucherPrint2Response>> voucherPrint2ResponseLiveData;
    private MutableLiveData<List<VoucherPrint3Response>> voucherPrint3ResponseLiveData;
    private MutableLiveData<List<VoucherPrint4Response>> voucherPrint4ResponseLiveData;
    private MutableLiveData<List<VoucherPrint5Response>> voucherPrint5ResponseLiveData;

    private MutableLiveData<List<BranchwiseJoiningResponse>> mBranchWiseJoiningResponseLiveData;
    private MutableLiveData<List<AgentDetail>> mAgentDetailisResponseLiveData;
    private MutableLiveData<List<MisAgentJoiningDetails>> misAgentDetailisResponseLiveData;
    private MutableLiveData<List<PhaseMasterResponse>> phaseMasterLiveData;
    private MutableLiveData<List<IntroDetails>> introDetailsLiveData;
    private MutableLiveData<List<BranchDetails>> joiningBranchLiveData;
    private MutableLiveData<List<GradeDetails>> gradeNameSpinnerLiveData;
    private MutableLiveData<List<NewJoiningFinalRespons>> newJoiningfinalLiveData;
    private MutableLiveData<List<FieldWorkResponse>> fieldWorkLiveData;
    private MutableLiveData<List<BankDetailResponse>> bankDetailsLiveData;
    private MutableLiveData<List<PromotionDetailsResponse>> promotionDetailsLiveData;
    private MutableLiveData<List<VoucherDetail>> voucherDetailsLiveData;
    private MutableLiveData<List<PaymentDetail>> paymentDetailsLiveData;
    private MutableLiveData<List<AgentCommissionDetailsResponse>> mAgentCommisionDetailsLiveData;
    private MutableLiveData<List<ApplicationNumberWisePaymentNewBusinessItem>> mApplicationWisePaymentLiveData;
    private MutableLiveData<List<ApplicationNumberWisePaymentNewPayment>> mApplicationWisePayment1newLiveData;
    private MutableLiveData<List<AgentCommisionTotal>> mAgentCommisionTotalLiveData;
    private MutableLiveData<List<ApplicationNoWisePremiumAmount>> mApplicationWisePremiumAmountLiveData;
    private MutableLiveData<List<CollectionDateItem>> mCollectionDateLiveData;
    private MutableLiveData<List<CollectionReportResponse>> mCollectionReportLiveData;

    public MutableLiveData<String> getApiError() {

        if (apiError == null) {
            apiError = new MutableLiveData<>();
        }
        return apiError;

    }

    public MutableLiveData<List<AvailablePlansResponse>> getAvailablePlans() {

        if (availablePlansResponseMutableLiveData == null) {
            availablePlansResponseMutableLiveData = new MutableLiveData<>();
        }
        return availablePlansResponseMutableLiveData;

    }

    public MutableLiveData<List<MisAgentJoiningDetails>> getMisAgentJoiningDetails() {

        if (misAgentDetailisResponseLiveData == null) {
            misAgentDetailisResponseLiveData = new MutableLiveData<>();
        }
        return misAgentDetailisResponseLiveData;

    }

    public MutableLiveData<List<PhaseMasterResponse>> getPhaseMasterObserver() {

        if (phaseMasterLiveData == null) {
            phaseMasterLiveData = new MutableLiveData<>();
        }
        return phaseMasterLiveData;

    }

    public MutableLiveData<List<IntroDetails>> getIntroDetailsObserver() {

        if (introDetailsLiveData == null) {
            introDetailsLiveData = new MutableLiveData<>();
        }
        return introDetailsLiveData;

    }

    public MutableLiveData<List<BranchDetails>> getJoiningBrachDetailsObsever() {

        if (joiningBranchLiveData == null) {
            joiningBranchLiveData = new MutableLiveData<>();
        }
        return joiningBranchLiveData;

    }

    public MutableLiveData<List<GradeDetails>> getGradeNameSpinnerObserver() {

        if (gradeNameSpinnerLiveData == null) {
            gradeNameSpinnerLiveData = new MutableLiveData<>();
        }
        return gradeNameSpinnerLiveData;

    }

    public MutableLiveData<List<NewJoiningFinalRespons>> submitNewJoineeFinalDataObserver() {

        if (newJoiningfinalLiveData == null) {
            newJoiningfinalLiveData = new MutableLiveData<>();
        }
        return newJoiningfinalLiveData;

    }

    public MutableLiveData<List<FieldWorkResponse>> getFieldWorkObserver() {

        if (fieldWorkLiveData == null) {
            fieldWorkLiveData = new MutableLiveData<>();
        }
        return fieldWorkLiveData;

    }

    public MutableLiveData<List<BankDetailResponse>> getBankDetailObserver() {

        if (bankDetailsLiveData == null) {
            bankDetailsLiveData = new MutableLiveData<>();
        }
        return bankDetailsLiveData;

    }

    public MutableLiveData<List<PromotionDetailsResponse>> getPromotionDetailObserver() {

        if (promotionDetailsLiveData == null) {
            promotionDetailsLiveData = new MutableLiveData<>();
        }
        return promotionDetailsLiveData;

    }

    public MutableLiveData<List<VoucherDetail>> getVoucherDetailsObserver() {

        if (voucherDetailsLiveData == null) {
            voucherDetailsLiveData = new MutableLiveData<>();
        }
        return voucherDetailsLiveData;

    }

    public MutableLiveData<List<PaymentDetail>> getPaymentDetailsObserver() {

        if (paymentDetailsLiveData == null) {
            paymentDetailsLiveData = new MutableLiveData<>();
        }
        return paymentDetailsLiveData;

    }


    public MutableLiveData<List<PlanDetailsResponse>> getPlanDetails() {

        if (planDetailsResponseMutableLiveData == null) {
            planDetailsResponseMutableLiveData = new MutableLiveData<>();
        }
        return planDetailsResponseMutableLiveData;

    }

    public MutableLiveData<List<AttendanceResponse>> getStartAttendanceResponse() {

        if (attendanceStartResponseLiveData == null) {
            attendanceStartResponseLiveData = new MutableLiveData<>();
        }
        return attendanceStartResponseLiveData;
    }

    public MutableLiveData<List<AttendanceResponse>> getEndAttendanceResponse() {

        if (attendanceEndResponseLiveData == null) {
            attendanceEndResponseLiveData = new MutableLiveData<>();
        }
        return attendanceEndResponseLiveData;
    }

    public MutableLiveData<List<ChangePasswordResponseModel>> getChangePasswordResponse() {

        if (changePasswordResponseLiveData == null) {
            changePasswordResponseLiveData = new MutableLiveData<>();
        }
        return changePasswordResponseLiveData;
    }

    public MutableLiveData<List<TeamDetailsResponse>> getTeamDetailsResponse() {

        if (teamDetailsResponseLiveData == null) {
            teamDetailsResponseLiveData = new MutableLiveData<>();
        }
        return teamDetailsResponseLiveData;
    }

    public MutableLiveData<List<AllBranchDetailsResponse>> getAllBranchData() {

        if (allBranchResponseLiveData == null) {
            allBranchResponseLiveData = new MutableLiveData<>();
        }
        return allBranchResponseLiveData;
    }

    public MutableLiveData<List<BusinessReportResponsePojo>> getBusinessReportResponse() {

        if (businessReportResponseLiveData == null) {
            businessReportResponseLiveData = new MutableLiveData<>();
        }
        return businessReportResponseLiveData;
    }

    public MutableLiveData<List<AttendanceDetailsResponsePojo>> getAttendanceDetailsLiveData() {

        if (attendanceDetailsLiveData == null) {
            attendanceDetailsLiveData = new MutableLiveData<>();
        }
        return attendanceDetailsLiveData;
    }

    public MutableLiveData<List<SalesDetailsResponsePojo>> getSalesDetailsLiveData() {

        if (salesDetailsLiveData == null) {
            salesDetailsLiveData = new MutableLiveData<>();
        }
        return salesDetailsLiveData;
    }

    public MutableLiveData<List<AssignCustomerResponsePojo>> getAssignCustomerLiveData() {

        if (assignCustomerLiveData == null) {
            assignCustomerLiveData = new MutableLiveData<>();
        }
        return assignCustomerLiveData;
    }

    public MutableLiveData<List<NewCustomerListResponsePojo>> getNewCustomerListResponse() {

        if (newCustomerListLiveData == null) {
            newCustomerListLiveData = new MutableLiveData<>();
        }
        return newCustomerListLiveData;
    }

    public MutableLiveData<List<FollowUpCustomersResponsePojo>> getFollowupCustomerList() {

        if (followupCustomerListLiveData == null) {
            followupCustomerListLiveData = new MutableLiveData<>();
        }
        return followupCustomerListLiveData;
    }

    public MutableLiveData<List<AttendanceCheckResponse>> getAssignCustomerFromCustomerLiveData() {

        if (assignCustomerFromCustomerResponse == null) {
            assignCustomerFromCustomerResponse = new MutableLiveData<>();
        }
        return assignCustomerFromCustomerResponse;
    }

    public MutableLiveData<List<StartVisitCheckResponse>> getStartVisitCheckResponse() {

        if (startVisitCheckResponse == null) {
            startVisitCheckResponse = new MutableLiveData<>();
        }
        return startVisitCheckResponse;
    }

    public MutableLiveData<List<StartVisitResponse>> getStartVisitResponse() {

        if (startVisitResponse == null) {
            startVisitResponse = new MutableLiveData<>();
        }
        return startVisitResponse;
    }

    public MutableLiveData<List<PremiumCalculatorList>> getPremiumCalculatorlistResponse() {

        if (premiumCalculatorlist == null) {
            premiumCalculatorlist = new MutableLiveData<>();
        }
        return premiumCalculatorlist;
    }

    public MutableLiveData<List<EndFollowUpVisitResponse>> getEndFollowUpVisitLiveData() {

        if (endFollowUpVisitLiveData == null) {
            endFollowUpVisitLiveData = new MutableLiveData<>();
        }
        return endFollowUpVisitLiveData;
    }

    public MutableLiveData<List<CompanyNamesResponsePojo>> getCompanyNamesResponseLiveData() {

        if (CompanyNamesResponseLiveData == null) {
            CompanyNamesResponseLiveData = new MutableLiveData<>();
        }
        return CompanyNamesResponseLiveData;
    }

    public MutableLiveData<List<PolicyNamesResponsePojo>> getPolicyNamesResponseLiveData() {

        if (policyNamesResponseLiveData == null) {
            policyNamesResponseLiveData = new MutableLiveData<>();
        }
        return policyNamesResponseLiveData;
    }

    public MutableLiveData<List<KycResponsePojo>> getKycResponseLiveData() {

        if (kycResponseLiveData == null) {
            kycResponseLiveData = new MutableLiveData<>();
        }
        return kycResponseLiveData;
    }


    public MutableLiveData<List<EndInterestedVisitResponse>> getEndInterestedVisitResponseLiveData() {

        if (endInterestedVisitResponseLiveData == null) {
            endInterestedVisitResponseLiveData = new MutableLiveData<>();
        }
        return endInterestedVisitResponseLiveData;
    }

    public MutableLiveData<List<EndNotInterestedVisitResponse>> getEndNotInterestedVisitResponseLiveData() {

        if (endNotInterestedVisitResponseLiveData == null) {
            endNotInterestedVisitResponseLiveData = new MutableLiveData<>();
        }
        return endNotInterestedVisitResponseLiveData;
    }

    public MutableLiveData<List<ApplyLeaveResponse>> getLeaveResponseLiveData() {

        if (applyLeaveResponseLiveData == null) {
            applyLeaveResponseLiveData = new MutableLiveData<>();
        }
        return applyLeaveResponseLiveData;
    }

    public MutableLiveData<List<VisitStateCheckResponse>> getVisitStateCheckResponseLiveData() {

        if (visitStateCheckResponseLiveData == null) {
            visitStateCheckResponseLiveData = new MutableLiveData<>();
        }
        return visitStateCheckResponseLiveData;
    }

    public MutableLiveData<List<AlertMessageResponse>> getAlertMsgResponseLiveData() {

        if (alertResponseLiveData == null) {
            alertResponseLiveData = new MutableLiveData<>();
        }
        return alertResponseLiveData;
    }

    public MutableLiveData<List<DeleteSms>> getDeleteSmsResponseLiveData() {

        if (deleteSmsLiveData == null) {
            deleteSmsLiveData = new MutableLiveData<>();
        }
        return deleteSmsLiveData;
    }

    public MutableLiveData<List<MenueStatusResponse>> getMenueStatusLiveData() {

        if (menueStatusLiveData == null) {
            menueStatusLiveData = new MutableLiveData<>();
        }
        return menueStatusLiveData;
    }

    public MutableLiveData<List<CircularResponse>> getCircularResponseLiveData() {

        if (circularResponseLiveData == null) {
            circularResponseLiveData = new MutableLiveData<>();
        }
        return circularResponseLiveData;
    }

    public MutableLiveData<List<SmsDetailsResposne>> getSmsDetailsLiveData() {

        if (smsDetailsLiveData == null) {
            smsDetailsLiveData = new MutableLiveData<>();
        }
        return smsDetailsLiveData;
    }

    public MutableLiveData<List<BranchDetailsResponse>> getMISBranchDetailsResponseLiveData() {

        if (misBranchDetailsResponseLiveData == null) {
            misBranchDetailsResponseLiveData = new MutableLiveData<>();
        }
        return misBranchDetailsResponseLiveData;
    }

    public MutableLiveData<List<MISCompanyDetailsResponse>> getMISCompanyDetailsResponseLiveData() {

        if (misCompanyDetailsResponseLiveData == null) {
            misCompanyDetailsResponseLiveData = new MutableLiveData<>();
        }
        return misCompanyDetailsResponseLiveData;
    }

    public MutableLiveData<List<BranchWiseBusinessResponse>> getBranchWiseBusinessResponseLiveData() {

        if (branchWiseBusinessResponseLiveData == null) {
            branchWiseBusinessResponseLiveData = new MutableLiveData<>();
        }
        return branchWiseBusinessResponseLiveData;
    }

    public MutableLiveData<List<MISIndividualBusinessResponse>> getMisIndividualBusinessResponseLiveData() {

        if (misIndividualBusinessResponseLiveData == null) {
            misIndividualBusinessResponseLiveData = new MutableLiveData<>();
        }
        return misIndividualBusinessResponseLiveData;
    }

    public MutableLiveData<List<MISBusinessSummaryResponse>> getMisBusinessSummaryResponseLiveData() {

        if (misBusinessSummaryResponseLiveData == null) {
            misBusinessSummaryResponseLiveData = new MutableLiveData<>();
        }
        return misBusinessSummaryResponseLiveData;
    }

    public MutableLiveData<List<MISCollectionRegisterResponse>> getMisCollectionRegisterResponseLiveData() {

        if (misCollectionRegisterResponseLiveData == null) {
            misCollectionRegisterResponseLiveData = new MutableLiveData<>();
        }
        return misCollectionRegisterResponseLiveData;
    }

    public MutableLiveData<List<MISSuggestionComplainResponse>> getMisSuggestionComplainResponseLiveData() {

        if (misSuggestionComplainResponseLiveData == null) {
            misSuggestionComplainResponseLiveData = new MutableLiveData<>();
        }
        return misSuggestionComplainResponseLiveData;
    }

    public MutableLiveData<List<PhaseDetailsResponse>> getPhaseDetailsResponseLiveData() {

        if (phaseDetailsResponseLiveData == null) {
            phaseDetailsResponseLiveData = new MutableLiveData<>();
        }
        return phaseDetailsResponseLiveData;
    }

    public MutableLiveData<List<StatementDetailsResponse>> getStatementDetailsResponseLiveData() {

        if (statementDetailsResponseLiveData == null) {
            statementDetailsResponseLiveData = new MutableLiveData<>();
        }
        return statementDetailsResponseLiveData;
    }


    public MutableLiveData<List<VoucherPrint1Response>> getVoucherPrint1ResponseLiveData() {

        if (voucherPrint1ResponseLiveData == null) {
            voucherPrint1ResponseLiveData = new MutableLiveData<>();
        }
        return voucherPrint1ResponseLiveData;
    }

    public MutableLiveData<List<VoucherPrint2Response>> getVoucherPrint2ResponseLiveData() {

        if (voucherPrint2ResponseLiveData == null) {
            voucherPrint2ResponseLiveData = new MutableLiveData<>();
        }
        return voucherPrint2ResponseLiveData;
    }

    public MutableLiveData<List<VoucherPrint3Response>> getVoucherPrint3ResponseLiveData() {

        if (voucherPrint3ResponseLiveData == null) {
            voucherPrint3ResponseLiveData = new MutableLiveData<>();
        }
        return voucherPrint3ResponseLiveData;
    }
    public MutableLiveData<List<VoucherPrint4Response>> getVoucherPrint4ResponseLiveData() {

        if (voucherPrint4ResponseLiveData == null) {
            voucherPrint4ResponseLiveData = new MutableLiveData<>();
        }
        return voucherPrint4ResponseLiveData;
    }

    public MutableLiveData<List<VoucherPrint5Response>> getVoucherPrint5ResponseLiveData() {

        if (voucherPrint5ResponseLiveData == null) {
            voucherPrint5ResponseLiveData = new MutableLiveData<>();
        }
        return voucherPrint5ResponseLiveData;
    }


    public MutableLiveData<List<BranchwiseJoiningResponse>> getBranchWiseJoiningResponseLiveData() {

        if (mBranchWiseJoiningResponseLiveData == null) {
            mBranchWiseJoiningResponseLiveData = new MutableLiveData<>();
        }
        return mBranchWiseJoiningResponseLiveData;
    }

    public MutableLiveData<List<AgentDetail>> getAgentDetailsLiveData() {

        if (mAgentDetailisResponseLiveData == null) {
            mAgentDetailisResponseLiveData = new MutableLiveData<>();
        }
        return mAgentDetailisResponseLiveData;
    }

    public MutableLiveData<List<AgentCommissionDetailsResponse>> getAgentCommisionDetailsLiveData() {

        if (mAgentCommisionDetailsLiveData == null) {
            mAgentCommisionDetailsLiveData = new MutableLiveData<>();
        }
        return mAgentCommisionDetailsLiveData;
    }

    public MutableLiveData<List<ApplicationNumberWisePaymentNewBusinessItem>> getApplicationNoWisePaymentLiveData() {

        if (mApplicationWisePaymentLiveData == null) {
            mApplicationWisePaymentLiveData = new MutableLiveData<>();
        }
        return mApplicationWisePaymentLiveData;
    }

    public MutableLiveData<List<ApplicationNumberWisePaymentNewPayment>> getApplicationNoWisePaymentNewLiveData() {

        if (mApplicationWisePayment1newLiveData == null) {
            mApplicationWisePayment1newLiveData = new MutableLiveData<>();
        }
        return mApplicationWisePayment1newLiveData;
    }


    public MutableLiveData<List<AgentCommisionTotal>> getmAgentCommisionTotalLiveData() {

        if (mAgentCommisionTotalLiveData == null) {
            mAgentCommisionTotalLiveData = new MutableLiveData<>();
        }
        return mAgentCommisionTotalLiveData;
    }

    public MutableLiveData<List<ApplicationNoWisePremiumAmount>> getApplicationWisePremiumAmountLiveData() {

        if (mApplicationWisePremiumAmountLiveData == null) {
            mApplicationWisePremiumAmountLiveData = new MutableLiveData<>();
        }
        return mApplicationWisePremiumAmountLiveData;
    }

    public MutableLiveData<List<CollectionDateItem>> getCollectionDate() {

        if (mCollectionDateLiveData == null) {
            mCollectionDateLiveData = new MutableLiveData<>();
        }
        return mCollectionDateLiveData;
    }

    public MutableLiveData<List<CollectionReportResponse>> getCollectionReport() {

        if (mCollectionReportLiveData == null) {
            mCollectionReportLiveData = new MutableLiveData<>();
        }
        return mCollectionReportLiveData;
    }


    public void callAvailablePlans() {
        apiClient.getAvailablePlans().enqueue(new Callback<List<AvailablePlansResponse>>() {
            @Override
            public void onResponse(Call<List<AvailablePlansResponse>> call, Response<List<AvailablePlansResponse>> response) {
                availablePlansResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AvailablePlansResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callPlanDetails(String selectedPlanName) {
        apiClient.getSelectedPlansDetails(selectedPlanName).enqueue(new Callback<List<PlanDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<PlanDetailsResponse>> call, Response<List<PlanDetailsResponse>> response) {
                planDetailsResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PlanDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callStartAttendance(AttendanceRequestModel model) {
        apiClient.startAttendance(model.getAgentCode(), model.getStartTime(), model.getStart_latitude(), model.getStart_longitude()).enqueue(new Callback<List<AttendanceResponse>>() {
            @Override
            public void onResponse(Call<List<AttendanceResponse>> call, Response<List<AttendanceResponse>> response) {
                attendanceStartResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AttendanceResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callEndAttendance(AttendanceRequestModel model) {
        apiClient.endAttendance(model.getAgentCode(), model.getEndTime(), model.getEnd_latitude(), model.getEnd_longitude()).enqueue(new Callback<List<AttendanceResponse>>() {
            @Override
            public void onResponse(Call<List<AttendanceResponse>> call, Response<List<AttendanceResponse>> response) {
                attendanceEndResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AttendanceResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callChangePassword(ChangePasswordRequestModel model) {
        apiClient.changePassword(model.getAgentCode(), model.getOldPassword(), model.getNewPassword()).enqueue(new Callback<List<ChangePasswordResponseModel>>() {
            @Override
            public void onResponse(Call<List<ChangePasswordResponseModel>> call, Response<List<ChangePasswordResponseModel>> response) {
                changePasswordResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ChangePasswordResponseModel>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callTeamDetails(TeamAllRequestPojo requestPojo) {
        apiClient.getTeamDetails(requestPojo.getAgentCode(), requestPojo.getTeamNo()).enqueue(new Callback<List<TeamDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<TeamDetailsResponse>> call, Response<List<TeamDetailsResponse>> response) {
                teamDetailsResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TeamDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callAllBranchDetails() {
        apiClient.getAllBranch().enqueue(new Callback<List<AllBranchDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<AllBranchDetailsResponse>> call, Response<List<AllBranchDetailsResponse>> response) {
                allBranchResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AllBranchDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getBusinessReport(BusinessReportRequestPojo requestPojo,String loginId) {
        apiClient.getBusinessReport(requestPojo.getAgentCode(), requestPojo.getStartDate(), requestPojo.getEndDate(), requestPojo.getType(),loginId).enqueue(new Callback<List<BusinessReportResponsePojo>>() {
            @Override
            public void onResponse(Call<List<BusinessReportResponsePojo>> call, Response<List<BusinessReportResponsePojo>> response) {
                businessReportResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BusinessReportResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getAttendanceRequest(AttendanceDetailsRequestPojo requestPojo) {
        apiClient.getAttendatnceDetails(requestPojo.getAgentCode(), requestPojo.getMonth(), requestPojo.getYear()).enqueue(new Callback<List<AttendanceDetailsResponsePojo>>() {
            @Override
            public void onResponse(Call<List<AttendanceDetailsResponsePojo>> call, Response<List<AttendanceDetailsResponsePojo>> response) {
                attendanceDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AttendanceDetailsResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getSalesDetailsRequest(SalesDetailsRequestPojo requestPojo) {
        apiClient.getSalesDetails(requestPojo.getAgentCode()).enqueue(new Callback<List<SalesDetailsResponsePojo>>() {
            @Override
            public void onResponse(Call<List<SalesDetailsResponsePojo>> call, Response<List<SalesDetailsResponsePojo>> response) {
                salesDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SalesDetailsResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void assignNewCustomerRequest(AssignCustomerActivityRequestPojo requestPojo) {
        apiClient.assignCustomer(requestPojo.getAgentCode(), requestPojo.getAssignAgentCode(), requestPojo.getCustomerName(), requestPojo.getAddress(), requestPojo.getMobileNumber(), requestPojo.getPinCode(), requestPojo.getEmailID()).enqueue(new Callback<List<AssignCustomerResponsePojo>>() {
            @Override
            public void onResponse(Call<List<AssignCustomerResponsePojo>> call, Response<List<AssignCustomerResponsePojo>> response) {
                assignCustomerLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AssignCustomerResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getNewCustomers(String agentCode) {
        apiClient.getNewCustomers(agentCode).enqueue(new Callback<List<NewCustomerListResponsePojo>>() {
            @Override
            public void onResponse(Call<List<NewCustomerListResponsePojo>> call, Response<List<NewCustomerListResponsePojo>> response) {
                newCustomerListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NewCustomerListResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

//    public void getFollowUpCustomers(String agentCode)
//    {
//        apiClient.getFollowupCustomers(agentCode).enqueue(new Callback<List<FollowUpCustomersResponsePojo>>() {
//            @Override
//            public void onResponse(Call<List<FollowUpCustomersResponsePojo>> call, Response<List<FollowUpCustomersResponsePojo>> response) {
//                followupCustomerListLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<FollowUpCustomersResponsePojo>> call, Throwable t) {
//                apiError.setValue(t.getLocalizedMessage());
//            }
//        });
//    }

    public void getFollowUpCustomers(String agentCode) {
        apiClient.getFollowupCustomers(agentCode).enqueue(new Callback<List<NewCustomerListResponsePojo>>() {
            @Override
            public void onResponse(Call<List<NewCustomerListResponsePojo>> call, Response<List<NewCustomerListResponsePojo>> response) {
                newCustomerListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NewCustomerListResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void assignNewCustomerFromCustomerRequest(AssignCustomerFragmentRequestPojo requestPojo) {
        apiClient.assignCustomerFromCustomer(requestPojo.getAgentCode(), requestPojo.getAsignAgentCode(), requestPojo.getCustomerName(), requestPojo.getAddress(), requestPojo.getContact(), requestPojo.getPinCode(), requestPojo.getEmail()).enqueue(new Callback<List<AttendanceCheckResponse>>() {
            @Override
            public void onResponse(Call<List<AttendanceCheckResponse>> call, Response<List<AttendanceCheckResponse>> response) {
                assignCustomerFromCustomerResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AttendanceCheckResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void startVisitStateCheckRequest(String agentCodeVisit, String CustomerId) {
        apiClient.chekVisitStae(agentCodeVisit, CustomerId).enqueue(new Callback<List<VisitStateCheckResponse>>() {
            @Override
            public void onResponse(Call<List<VisitStateCheckResponse>> call, Response<List<VisitStateCheckResponse>> response) {
                visitStateCheckResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VisitStateCheckResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }


    public void startVisitCheckRequest(String agentCodeVisit) {
        apiClient.startVisitCheck(agentCodeVisit).enqueue(new Callback<List<StartVisitCheckResponse>>() {
            @Override
            public void onResponse(Call<List<StartVisitCheckResponse>> call, Response<List<StartVisitCheckResponse>> response) {
                startVisitCheckResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<StartVisitCheckResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void startVisitRequest(StartVisitRequest request) {
        apiClient.startVisit(request.getAgentCode(), request.getCustomerId(), request.getLat(), request.getLong()).enqueue(new Callback<List<StartVisitResponse>>() {
            @Override
            public void onResponse(Call<List<StartVisitResponse>> call, Response<List<StartVisitResponse>> response) {
                startVisitResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<StartVisitResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getPremiumCalculatorList() {
        apiClient.getList().enqueue(new Callback<List<PremiumCalculatorList>>() {
            @Override
            public void onResponse(Call<List<PremiumCalculatorList>> call, Response<List<PremiumCalculatorList>> response) {
                premiumCalculatorlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PremiumCalculatorList>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void endFollowUpVisit(String customerId, String followUpDate, String comment) {
        apiClient.endFollowUpVisit(customerId, followUpDate, comment).enqueue(new Callback<List<EndFollowUpVisitResponse>>() {
            @Override
            public void onResponse(Call<List<EndFollowUpVisitResponse>> call, Response<List<EndFollowUpVisitResponse>> response) {
                endFollowUpVisitLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<EndFollowUpVisitResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }


    public void getComnayNamesRequest() {
        apiClient.getCompanyNames().enqueue(new Callback<List<CompanyNamesResponsePojo>>() {
            @Override
            public void onResponse(Call<List<CompanyNamesResponsePojo>> call, Response<List<CompanyNamesResponsePojo>> response) {
                CompanyNamesResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CompanyNamesResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getPolicyNamesRequest(String CompanyName) {
        apiClient.getPolicyNames(CompanyName).enqueue(new Callback<List<PolicyNamesResponsePojo>>() {
            @Override
            public void onResponse(Call<List<PolicyNamesResponsePojo>> call, Response<List<PolicyNamesResponsePojo>> response) {
                policyNamesResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PolicyNamesResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getKycDetails() {
        apiClient.getKycDetails().enqueue(new Callback<List<KycResponsePojo>>() {
            @Override
            public void onResponse(Call<List<KycResponsePojo>> call, Response<List<KycResponsePojo>> response) {
                kycResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<KycResponsePojo>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void endVisitForInterested(VisitInterestedRequestPojo requestPojo) {
        apiClient.endVisitInterested(requestPojo.getAgentCode(),
                requestPojo.getCustomerId(),
                requestPojo.getApplicationNo(),
                requestPojo.getPolicyPlan(),
                requestPojo.getPaymentMode(),
                requestPojo.getAmount(),
                requestPojo.getAddressId(),
                requestPojo.getAgeId(),
                requestPojo.getId(),
                requestPojo.getChequeNo(),
                requestPojo.getComments(),
                requestPojo.getImageName())
                .enqueue(new Callback<List<EndInterestedVisitResponse>>() {
                    @Override
                    public void onResponse(Call<List<EndInterestedVisitResponse>> call, Response<List<EndInterestedVisitResponse>> response) {
                        endInterestedVisitResponseLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<EndInterestedVisitResponse>> call, Throwable t) {
                        apiError.setValue(t.getLocalizedMessage());
                    }
                });
    }

    public void endVisitForNotInterested(VisitNotInterestedRequestPojo requestPojo) {
        apiClient.endVisitNotInterested(requestPojo.getAgentCode(),
                requestPojo.getCustomerId(),
                requestPojo.getComments()).enqueue(new Callback<List<EndNotInterestedVisitResponse>>() {
            @Override
            public void onResponse(Call<List<EndNotInterestedVisitResponse>> call, Response<List<EndNotInterestedVisitResponse>> response) {
                endNotInterestedVisitResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<EndNotInterestedVisitResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void applyLeave(ApplyLeaveRequestPojo requestPojo) {
        apiClient.applyLeave(requestPojo.getAgentCode(), requestPojo.getLeaveText(), requestPojo.getLeaveStartDate(), requestPojo.getLeaveEndDate()).enqueue(new Callback<List<ApplyLeaveResponse>>() {
            @Override
            public void onResponse(Call<List<ApplyLeaveResponse>> call, Response<List<ApplyLeaveResponse>> response) {
                applyLeaveResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ApplyLeaveResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }


    public void getAlertMessage(String agentCode) {
        apiClient.getAlertMessage(agentCode).enqueue(new Callback<List<AlertMessageResponse>>() {
            @Override
            public void onResponse(Call<List<AlertMessageResponse>> call, Response<List<AlertMessageResponse>> response) {
                alertResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AlertMessageResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getMISBranchDetails(String roleId, String userId) {
        apiClient.getMISBranchDetails(roleId, userId).enqueue(new Callback<List<BranchDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<BranchDetailsResponse>> call, Response<List<BranchDetailsResponse>> response) {
                misBranchDetailsResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BranchDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getMISCompanyDetails() {
        apiClient.getMISCompanyDetails().enqueue(new Callback<List<MISCompanyDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<MISCompanyDetailsResponse>> call, Response<List<MISCompanyDetailsResponse>> response) {
                misCompanyDetailsResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MISCompanyDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitMISBRanchWiseBusinessDetails(String branchId, String startdate, String enddate, String code, String insurance, String type, String codeType, String insuranceComp, String businessType) {
        apiClient.submitMISBRanchWiseBusinessDetails(branchId, startdate, enddate, code, insurance, type, codeType, insuranceComp, businessType).enqueue(new Callback<List<BranchWiseBusinessResponse>>() {
            @Override
            public void onResponse(Call<List<BranchWiseBusinessResponse>> call, Response<List<BranchWiseBusinessResponse>> response) {
                branchWiseBusinessResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BranchWiseBusinessResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitMISIndividualBusinessDetails(String agentCode, String startdate, String enddate, String roleId, String loginId) {
        apiClient.submitMISIndividualBusinessDetails(agentCode, startdate, enddate, roleId, loginId).enqueue(new Callback<List<MISIndividualBusinessResponse>>() {
            @Override
            public void onResponse(Call<List<MISIndividualBusinessResponse>> call, Response<List<MISIndividualBusinessResponse>> response) {
                if (response.body().get(0).getStatus() != null && response.body().get(0).getStatus().equalsIgnoreCase("Success")) {
                    misIndividualBusinessResponseLiveData.setValue(response.body());
                } else {
                    if (response.body().get(0).getStatus()!=null && !response.body().get(0).getStatus().isEmpty()) {
                        apiError.setValue(response.body().get(0).getStatus());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<MISIndividualBusinessResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitMISIndividualBusinessDetails(String startdate, String enddate,String uid,String loginId) {
        apiClient.submitMISBusinessSummary(startdate, enddate,uid,loginId).enqueue(new Callback<List<MISBusinessSummaryResponse>>() {
            @Override
            public void onResponse(Call<List<MISBusinessSummaryResponse>> call, Response<List<MISBusinessSummaryResponse>> response) {
                misBusinessSummaryResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MISBusinessSummaryResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitMISCollectionRegisterResponse(String startdate, String enddate , String loginCode) {
        apiClient.submitMISCollectionRegisterResponse(startdate, enddate ,loginCode).enqueue(new Callback<List<MISCollectionRegisterResponse>>() {
            @Override
            public void onResponse(Call<List<MISCollectionRegisterResponse>> call, Response<List<MISCollectionRegisterResponse>> response) {

                misCollectionRegisterResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MISCollectionRegisterResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitMISSuggestionComplain(String suggestion, String userId) {
        apiClient.submitMISSuggestionComplain(suggestion, userId).enqueue(new Callback<List<MISSuggestionComplainResponse>>() {
            @Override
            public void onResponse(Call<List<MISSuggestionComplainResponse>> call, Response<List<MISSuggestionComplainResponse>> response) {
                misSuggestionComplainResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MISSuggestionComplainResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getPhaseDetails() {
        apiClient.getPhaseDetails().enqueue(new Callback<List<PhaseDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<PhaseDetailsResponse>> call, Response<List<PhaseDetailsResponse>> response) {
                phaseDetailsResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PhaseDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getStatementDetails(String phaseId, String roleId) {
        apiClient.getStatementDetails(phaseId, roleId).enqueue(new Callback<List<StatementDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<StatementDetailsResponse>> call, Response<List<StatementDetailsResponse>> response) {
                statementDetailsResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<StatementDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getVoucherPrint1(String statementId, String agentCode, String lcode) {
        apiClient.getVoucherPrint1(statementId, agentCode, lcode).enqueue(new Callback<List<VoucherPrint1Response>>() {
            @Override
            public void onResponse(Call<List<VoucherPrint1Response>> call, Response<List<VoucherPrint1Response>> response) {
                voucherPrint1ResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherPrint1Response>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getVoucherPrint2(String statementId, String agentCode, String lcode) {
        apiClient.getVoucherPrint2(statementId, agentCode, lcode).enqueue(new Callback<List<VoucherPrint2Response>>() {
            @Override
            public void onResponse(Call<List<VoucherPrint2Response>> call, Response<List<VoucherPrint2Response>> response) {
                voucherPrint2ResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherPrint2Response>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getVoucherPrint3(String statementId, String agentCode, String lcode) {
        apiClient.getVoucherPrint3(statementId, agentCode, lcode).enqueue(new Callback<List<VoucherPrint3Response>>() {
            @Override
            public void onResponse(Call<List<VoucherPrint3Response>> call, Response<List<VoucherPrint3Response>> response) {
                voucherPrint3ResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherPrint3Response>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getVoucherPrint4(String statementId, String agentCode, String lcode) {
        apiClient.getVoucherPrint4(statementId, agentCode, lcode).enqueue(new Callback<List<VoucherPrint4Response>>() {
            @Override
            public void onResponse(Call<List<VoucherPrint4Response>> call, Response<List<VoucherPrint4Response>> response) {
                voucherPrint4ResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherPrint4Response>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getVoucherPrint5(String statementId, String agentCode, String lcode) {
        apiClient.getVoucherPrint5(statementId, agentCode, lcode).enqueue(new Callback<List<VoucherPrint5Response>>() {
            @Override
            public void onResponse(Call<List<VoucherPrint5Response>> call, Response<List<VoucherPrint5Response>> response) {
                voucherPrint5ResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherPrint5Response>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }


    /**
     * ******************************************************************************************************************************************
     * ***************************   New code On Mis Report   **********************************************************************************
     */

    public void getBranchwiseJoiningDetails(String startdate, String enddate, String agentCode , String loginCode) {
        apiClient.getBranchwiseJoiningDetails(startdate, enddate, agentCode,loginCode).enqueue(new Callback<List<BranchwiseJoiningResponse>>() {
            @Override
            public void onResponse(Call<List<BranchwiseJoiningResponse>> call, Response<List<BranchwiseJoiningResponse>> response) {
                if (response.body() != null) {
                    Log.e("branch Enrollment", new Gson().toJson(response.body()));
                    if (response.body() != null && response.body().size() == 1) {
                        if (response.body().get(0).getAgentCode() == null) {
                            if (response.body().get(0).getStatus() != null && !response.body().get(0).getStatus().isEmpty()) {
                                apiError.setValue(response.body().get(0).getStatus());
                            }
                        }
                    }
                    mBranchWiseJoiningResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<BranchwiseJoiningResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getAgentDetails(String startdate, String enddate, String agentCode, String agentId) {
        apiClient.getAgentDetails(startdate, enddate, agentCode, agentId).enqueue(new Callback<List<AgentDetail>>() {
            @Override
            public void onResponse(Call<List<AgentDetail>> call, Response<List<AgentDetail>> response) {
                if (response.body()!=null && response.body().size() == 1) {
                    if (response.body().get(0).getCode() == null) {
                        if (response.body().get(0).getStatus() != null && !response.body().get(0).getStatus().isEmpty()) {
                            apiError.setValue(response.body().get(0).getStatus());
                        }
                    }
                }
                mAgentDetailisResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AgentDetail>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getAgentCommisionDetails(String code, String aId) {
        apiClient.getAgentCommisionDetails(code, aId).enqueue(new Callback<List<AgentCommissionDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<AgentCommissionDetailsResponse>> call, Response<List<AgentCommissionDetailsResponse>> response) {
                if (response.body()!=null && response.body().size() == 1) {
                    if (response.body().get(0).getAGCODE() == null) {
                        if (response.body().get(0).getStatus()!=null && !response.body().get(0).getStatus().isEmpty()) {
                            apiError.setValue(response.body().get(0).getStatus());
                        }
                    }
                }
                mAgentCommisionDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AgentCommissionDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getApplicationNoWisePayment(String ApplicationNo , String LoginCode) {
        apiClient.getApplicationNoWisePayment(ApplicationNo , LoginCode).enqueue(new Callback<List<ApplicationNumberWisePaymentNewBusinessItem>>() {
            @Override
            public void onResponse(Call<List<ApplicationNumberWisePaymentNewBusinessItem>> call, Response<List<ApplicationNumberWisePaymentNewBusinessItem>> response) {
                mApplicationWisePaymentLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ApplicationNumberWisePaymentNewBusinessItem>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getApplicationNoWisePaymentNew(String ApplicationNo , String LoginCode) {
        apiClient.getApplicationNoWisePaymentNEW(ApplicationNo , LoginCode).enqueue(new Callback<List<ApplicationNumberWisePaymentNewPayment>>() {
            @Override
            public void onResponse(Call<List<ApplicationNumberWisePaymentNewPayment>> call, Response<List<ApplicationNumberWisePaymentNewPayment>> response) {
                mApplicationWisePayment1newLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ApplicationNumberWisePaymentNewPayment>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getAgentCommisionTotal(String aCode, String lCode) {
        apiClient.getAgentCommisionTotal(aCode, lCode).enqueue(new Callback<List<AgentCommisionTotal>>() {
            @Override
            public void onResponse(Call<List<AgentCommisionTotal>> call, Response<List<AgentCommisionTotal>> response) {
                mAgentCommisionTotalLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AgentCommisionTotal>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getApplicationNoWisePremiumAmount(String ApplicationNo , String agentId) {
        apiClient.getApplicationNoWisePremiumAmount(ApplicationNo , agentId).enqueue(new Callback<List<ApplicationNoWisePremiumAmount>>() {
            @Override
            public void onResponse(Call<List<ApplicationNoWisePremiumAmount>> call, Response<List<ApplicationNoWisePremiumAmount>> response) {
                mApplicationWisePremiumAmountLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ApplicationNoWisePremiumAmount>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getMisAgentJoiningDetils(String startdate, String enddate, String agentCode, String agentId) {
        apiClient.getAgentJoiningDetailsDetails(startdate, enddate, agentCode, agentId).enqueue(new Callback<List<MisAgentJoiningDetails>>() {
            @Override
            public void onResponse(Call<List<MisAgentJoiningDetails>> call, Response<List<MisAgentJoiningDetails>> response) {
                misAgentDetailisResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MisAgentJoiningDetails>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getPhaseMaster() {
        apiClient.getPhasMaster().enqueue(new Callback<List<PhaseMasterResponse>>() {
            @Override
            public void onResponse(Call<List<PhaseMasterResponse>> call, Response<List<PhaseMasterResponse>> response) {
                phaseMasterLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PhaseMasterResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getIntroDetails(String introducerCode, String agentId) {
        apiClient.getIntroDetails(introducerCode, agentId).enqueue(new Callback<List<IntroDetails>>() {
            @Override
            public void onResponse(Call<List<IntroDetails>> call, Response<List<IntroDetails>> response) {
                introDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<IntroDetails>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getJoiningBranchSpinner() {
        apiClient.getjoiningBranch().enqueue(new Callback<List<BranchDetails>>() {
            @Override
            public void onResponse(Call<List<BranchDetails>> call, Response<List<BranchDetails>> response) {
                joiningBranchLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BranchDetails>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getGradeNameSpinner(String gradeId) {
        apiClient.getGradeNameSpinnerData(gradeId).enqueue(new Callback<List<GradeDetails>>() {
            @Override
            public void onResponse(Call<List<GradeDetails>> call, Response<List<GradeDetails>> response) {
                gradeNameSpinnerLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GradeDetails>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void submitApi(String agName, String gender, String dob, String introducer, String branchId, String addedBy, String enrollAmount, String rankId, String code, String printStatus, String compId, String phaseId, String phoneNumber) {
        apiClient.fialSubmitForNewAgent(agName, gender, dob, introducer, branchId, addedBy, enrollAmount, rankId, code, printStatus, compId, phaseId, phoneNumber).enqueue(new Callback<List<NewJoiningFinalRespons>>() {
            @Override
            public void onResponse(Call<List<NewJoiningFinalRespons>> call, Response<List<NewJoiningFinalRespons>> response) {
                newJoiningfinalLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NewJoiningFinalRespons>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void fetchFieldWorkDetails(String agentCode, String loggedInAgentsId) {
        apiClient.fetchFieldWorkDetails(agentCode, loggedInAgentsId).enqueue(new Callback<List<FieldWorkResponse>>() {
            @Override
            public void onResponse(Call<List<FieldWorkResponse>> call, Response<List<FieldWorkResponse>> response) {
                if (response.body() != null && response.body().size() == 1) {
                    if (response.body().get(0).getAgRankId() == null) {
                        if (response.body().get(0).getStatus() != null && !response.body().get(0).getStatus().isEmpty()) {
                            apiError.setValue(response.body().get(0).getStatus());
                        }
                    }
                }
                fieldWorkLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<FieldWorkResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void fetchBankDetails(String agentCode, String loggedInAgentsId) {
        apiClient.fetchBankDetails(agentCode, loggedInAgentsId).enqueue(new Callback<List<BankDetailResponse>>() {
            @Override
            public void onResponse(Call<List<BankDetailResponse>> call, Response<List<BankDetailResponse>> response) {
                bankDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BankDetailResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void fetchPromotionDetails(String agentCode, String loggedInAgentsId) {
        apiClient.fetchPromotionDetails(agentCode, loggedInAgentsId).enqueue(new Callback<List<PromotionDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<PromotionDetailsResponse>> call, Response<List<PromotionDetailsResponse>> response) {
                promotionDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PromotionDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void fetchVoucherDetails(String agentCode, String loggedInAgentsId) {
        apiClient.fetchVoucherDetails(agentCode, loggedInAgentsId).enqueue(new Callback<List<VoucherDetail>>() {
            @Override
            public void onResponse(Call<List<VoucherDetail>> call, Response<List<VoucherDetail>> response) {
                voucherDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<VoucherDetail>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void fetchPaymentDetails(String agentCode, String loggedInAgentsId) {
        apiClient.fetchPaymentDetails(agentCode, loggedInAgentsId).enqueue(new Callback<List<PaymentDetail>>() {
            @Override
            public void onResponse(Call<List<PaymentDetail>> call, Response<List<PaymentDetail>> response) {
                paymentDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PaymentDetail>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getSmsDetails(String data) {
        apiClient.getSmsDetails(data).enqueue(new Callback<List<SmsDetailsResposne>>() {
            @Override
            public void onResponse(Call<List<SmsDetailsResposne>> call, Response<List<SmsDetailsResposne>> response) {
                smsDetailsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SmsDetailsResposne>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void deleteSms(String slNo) {
        apiClient.deleteSms(slNo).enqueue(new Callback<List<DeleteSms>>() {
            @Override
            public void onResponse(Call<List<DeleteSms>> call, Response<List<DeleteSms>> response) {
                deleteSmsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DeleteSms>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void getMenueStatus(String rolId) {
        apiClient.getMenueStatus(rolId).enqueue(new Callback<List<MenueStatusResponse>>() {
            @Override
            public void onResponse(Call<List<MenueStatusResponse>> call, Response<List<MenueStatusResponse>> response) {
                menueStatusLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MenueStatusResponse>> call, Throwable t) {
                if(t.getLocalizedMessage() != null) {
                    apiError.setValue(t.getLocalizedMessage());
                }
            }
        });
    }

    public void getPrizeReport(String agentId, String startDate, String endDate) {
        apiClient.getCircularResponse(startDate, endDate).enqueue(new Callback<List<CircularResponse>>() {
            @Override
            public void onResponse(Call<List<CircularResponse>> call, Response<List<CircularResponse>> response) {
                circularResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CircularResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }


}
