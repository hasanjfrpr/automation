package com.dayrayaneh.automation.viewModel.pishkhan.ticket;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseView;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.repo.TicketRep;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.repo.TicketRepImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TicketViewModel extends BaseViewModel {
    public MutableLiveData<TicketModel> ticketModelLiveData = new MutableLiveData<>();

    private TicketRep repo =new  TicketRepImpl();



    public void getTickets(String startDate , String endDate , int UserCode , boolean NotChecked,
                           boolean pending , boolean queue , boolean doing , boolean stoped , boolean finished , boolean canceled,
                           String titleFilter , String descriptionFilter , boolean myTicket , boolean currentTicket , boolean allTicket){
        repo.getTickets(startDate ,endDate , UserCode , NotChecked , pending , queue , doing , stoped , finished , canceled , titleFilter , descriptionFilter , myTicket ,currentTicket , allTicket)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<TicketModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull TicketModel ticketModel) {
                        ticketModelLiveData.setValue(ticketModel);
                    }
                });
    }
}
