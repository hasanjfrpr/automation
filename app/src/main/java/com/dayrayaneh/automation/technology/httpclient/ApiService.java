package com.dayrayaneh.automation.technology.httpclient;

import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.GozareshKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.PersonalListModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details.DarsadKharidMoshtariDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details.DarsadKharidShahrestanDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;
import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare.ForooshNarmAfzarCompareModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.ForooshSakhtAfzarCompareModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.ProductCategories;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.BazaryabiDetailModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.paygiri.PaygiriModel;
import com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid.SefareshMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.TedadHokmKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.TedadHokmKarDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;
import com.dayrayaneh.automation.model.pishkhan.tickets.detilas.TicketDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.VoiceModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("Authorization/Login")
    Single<LoginModel> sendUsername(@Body JsonObject jsonObject );

    @POST("Marketing/getCount")
    Single<BazaryabiMainModel> getBazaryabiCount(@Body JsonObject jsonObject);

    @POST("Marketing/getDetails")
    Single<BazaryabiDetailModel> getBazaryabiDetail(@Body JsonObject jsonObject);

    @POST("CustomersPercent/PercentageOfPurchases")
    Single<DarsadkharidMoshtariModel> getDarsadKharidMoshtari(@Body JsonObject jsonObject);

    @POST("customersPercent/percentageOfpurchasesDetails")
    Single<DarsadKharidMoshtariDetailsModel> getDarsadKharidMoshtariDetails(@Body JsonObject jsonObject);

    @POST("citiesPercent/purchasePercentage")
    Single<DarsadKharidShahrestanModel> getDarsadKharidShahrestan(@Body JsonObject jsonObject);

    @POST("citiesPercent/purchasePercentageDetails")
    Single<DarsadKharidShahrestanDetailsModel> getDarsadKharidShahrestanDetails(@Body JsonObject jsonObject);

    @POST("orders/percentOforders")
    Single<DarsadSefareshatModel> getDarsadSefareshat(@Body JsonObject jsonObject);

    @POST("orders/percentOfdiscount")
    Single<DarsadTakhfifAzHarSefareshModel> getDarsadTakhfifAzHarSefaresh(@Body JsonObject jsonObject);

    @POST("orders/softsell")
    Single<ForooshNarmAfzarModel> getForooshNarmAfzar(@Body JsonObject jsonObject);

    @POST("orders/softsellCompare")
    Single<ForooshNarmAfzarCompareModel> getForooshNarmAfzarCompare(@Body JsonObject jsonObject);

    @POST("services/contractExtension")
    Single<TamdidGharardadModel> getTamdidGharardad(@Body JsonObject jsonObject);

    @POST("services/contractExtensionCompare")
    Single<TamdidGharardadCompareModel> getTamdidGharardadCompare(@Body JsonObject jsonObject);

    @POST("orders/hardsell")
    Single<ForooshSakhtAfzarModel> getForooshSakhtAfzar(@Body JsonObject jsonObject);

    @POST("categories/productCategories")
    Single<ProductCategories> getProductCategories();

    @POST("orders/hardsellCompare")
    Single<ForooshSakhtAfzarCompareModel> getForooshSakhtAfzarCompare(@Body JsonObject jsonObject);

    @POST("sentence/List")
    Single<HokmKarModel> getHokmKar(@Body JsonObject jsonObject);

    @POST("sentence/customerRequests")
    Single<HokmKarRequestModel> getHokmKarRequestModel(@Body JsonObject jsonObject);

    @POST("sentence/followers")
    Single<HokmKarFollowersModel> getHokmKarFollowersModel(@Body JsonObject jsonObject);

    @POST("Services/SupportServiceCount")
    Single<KhadamatPoshtibaniMainModel> getKhadamatPoshtibaniMain(@Body JsonObject jsonObject);

    @POST("Services/SupportServiceDetails")
    Single<KhadamatPoshtibaniDetailModel> getKhadamatPoshtibaniDetail(@Body JsonObject jsonObject);

    @POST("orders/ordersStatus")
    Single<VaziatSefareshatModel> getVaziatSefareshat(@Body JsonObject jsonObject);

    @POST("orders/newCustomersOrders")
    Single<SefareshMoshtariModel> getSefareshMoshtariJadid(@Body JsonObject jsonObject);

    @POST("sentence/count")
    Single<TedadHokmKarCountModel> getTedadHokmKarCount(@Body JsonObject jsonObject);

    @POST("sentence/Details")
    Single<TedadHokmKarDetailsModel> getTedadHokmKarDetail(@Body JsonObject jsonObject);

    @POST("personely/personelList")
    Single<PersonalListModel> getPersonaList();

    @POST("workReport/count")
    Single<GozareshKarCountModel> getGozareshKarCount(@Body JsonObject jsonObject);

    @POST("workReport/Details")
    Single<GozareshKarDetailsModel> getGozareshKarDetail(@Body JsonObject jsonObject);

    @POST("services/talks")
    Single<VoiceModel> getVoice(@Body JsonObject jsonObject);

    @POST("Ticket/Master")
    Single<TicketModel> getTickets(@Body JsonObject jsonObject);

    @POST("Ticket/Details/{id}")
    Single<TicketDetailsModel> getTicketDetails(@Path("id") int id);

    @POST("sentence/notclosedDetails")
    Single<UnDoneHokmKarModel> getUnDoneHokmKar(@Body JsonObject jsonObject);

    @POST("sentence/notclosedCount")
    Single<UnDoneHokmkarCountMode> getUndonHokmKarCount(@Body JsonObject jsonObject);

    @POST("marketing/getproformaDetails")
    Single<PaygiriModel> getPaygiri(@Body JsonObject jsonObject);
}
