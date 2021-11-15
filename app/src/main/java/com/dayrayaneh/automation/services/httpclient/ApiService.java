package com.dayrayaneh.automation.services.httpclient;

import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;
import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.BazaryabiDetailModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
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

    @POST("services/contractExtension")
    Single<TamdidGharardadModel> getTamdidGharardad(@Body JsonObject jsonObject);


}
