package com.dayrayaneh.automation.services.httpclient;

import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
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
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("Authorization/Login")
    Single<LoginModel> sendUsername(@Body JsonObject jsonObject);

    @POST("Marketing/getCount")
    Single<BazaryabiMainModel> getBazaryabiCount(@Body JsonObject jsonObject);

    @POST("Marketing/getDetails")
    Single<BazaryabiDetailModel> getBazaryabiDetail(@Body JsonObject jsonObject);

    @POST("CustomersPercent/PercentageOfPurchases")
    Single<DarsadkharidMoshtariModel> getDarsadKharidMoshtari(@Body JsonObject jsonObject);

    @POST("citiesPercent/purchasePercentage")
    Single<DarsadKharidShahrestanModel> getDarsadKharidShahrestan(@Body JsonObject jsonObject);

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

    @POST("sentence/Details")
    Single<HokmKarModel> getHokmKar(@Body JsonObject jsonObject);

    @POST("sentence/customerRequest")
    Single<HokmKarRequestModel> getHokmKarRequestModel(@Body JsonObject jsonObject);

    @POST("sentence/followers")
    Single<HokmKarFollowersModel> getHokmKarFollowersModel(@Body JsonObject jsonObject);

    @POST("Services/SupportServiceCount")
    Single<KhadamatPoshtibaniMainModel> getKhadamatPoshtibaniMain(@Body JsonObject jsonObject);

    @POST("Services/SupportServiceDetails")
    Single<KhadamatPoshtibaniDetailModel> getKhadamatPoshtibaniDetail(@Body JsonObject jsonObject);

    @POST("orders/ordersStatus")
    Single<VaziatSefareshatModel> getVaziatSefareshat(@Body JsonObject jsonObject);

//    @POST("orders/newCustomersOrders")
//    Single<> getSefareshMoshtariJadid(@Body JsonObject jsonObject);
}
