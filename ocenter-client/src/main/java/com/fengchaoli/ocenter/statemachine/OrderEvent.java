package com.fengchaoli.ocenter.statemachine;


public enum OrderEvent {
    CREATE,  //创建

    PAY,    //支付

    CONFIRM,  //确认

    SHIP,  //发货

    SETTLE,  //结算

    CLOSE,   //关闭
}
