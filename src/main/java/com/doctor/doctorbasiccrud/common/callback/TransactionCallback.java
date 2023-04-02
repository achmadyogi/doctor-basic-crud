package com.doctor.doctorbasiccrud.common.callback;

import com.doctor.doctorbasiccrud.biz.enums.ServiceNameEnum;
import com.doctor.doctorbasiccrud.service.response.BaseResponse;

public abstract class TransactionCallback {
    public abstract ServiceNameEnum getServiceName();
    public abstract void validate();
    public abstract BaseResponse doTransaction();
}
