package com.dayrayaneh.automation.viewModel.pishkhan.ticket.repo;

import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;
import com.google.protobuf.Api;

import io.reactivex.Single;

public class TicketRepImpl implements TicketRep {

    private ApiService apiService;
    private JsonObject jsonObject;

    @Override
    public Single<TicketModel> getTickets(String startDate, String endDate, int UserCode, boolean NotChecked, boolean pending, boolean queue, boolean doing, boolean stoped,
                                          boolean finished, boolean canceled, String titleFilter, String descriptionFilter, boolean myTicket, boolean currentTicket, boolean allTicket) {
       apiService = ApiInstance.getApiInstance();
       jsonObject = new JsonObject();
       jsonObject.addProperty("startDate", startDate+" 00:00:00.000");
       jsonObject.addProperty("endDate", endDate+" 23:59:59.000");
       jsonObject.addProperty("userCode" , UserCode);
       jsonObject.addProperty("NotChecked" , NotChecked);
       jsonObject.addProperty("Pending" , pending);
       jsonObject.addProperty("Queue" , queue);
       jsonObject.addProperty("Doing" , doing);
       jsonObject.addProperty("Stoped" , stoped);
       jsonObject.addProperty("Finished" , finished);
       jsonObject.addProperty("Canceled" , canceled);
       jsonObject.addProperty("TitleFilter" , titleFilter);
       jsonObject.addProperty("DescriptionFilter" , descriptionFilter);
       jsonObject.addProperty("MyTicket" , myTicket);
       jsonObject.addProperty("CurrentTicket" , currentTicket);
       jsonObject.addProperty("AllTicket" , allTicket);
        return apiService.getTickets(jsonObject);
    }
}
