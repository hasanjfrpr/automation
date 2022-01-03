package com.dayrayaneh.automation.viewModel.pishkhan.ticket.repo;

import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface TicketRep {

    Single<TicketModel> getTickets(String startDate , String endDate , int UserCode , boolean NotChecked,
                                   boolean pending , boolean queue , boolean doing , boolean stoped , boolean finished , boolean canceled,
                                   String titleFilter , String descriptionFilter , boolean myTicket , boolean currentTicket , boolean allTicket);
}
