package com.tanvir.dollartouch.DataModel;

import java.util.List;

public class WithdrawList {
    List <WithdrawModel> withdraw=null;

    public WithdrawList(List<WithdrawModel> withdraw) {
        this.withdraw = withdraw;
    }

    public List<WithdrawModel> getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(List<WithdrawModel> withdraw) {
        this.withdraw = withdraw;
    }
}
