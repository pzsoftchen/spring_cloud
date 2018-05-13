package com.fengchaoli.ocenter.statemachine;


public enum OrderState {
    CREATE_INIT,  //创建开始

    CREATE_PENDDING,  //创建中

    CREATE_DONE,     //创建完成

    PAY_INIT,   //支付开始

    PAY_DONE,    //支付完成

    CONFIRM_DONE,  //确认完成

    SHIP_DONE,  //发货完成

    SETTLE_DONE,  //结算完成

    CLOSE_DONE,   //关闭完成

}
