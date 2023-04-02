package com.doctor.doctorbasiccrud.common.callback;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;
import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;
import com.doctor.doctorbasiccrud.biz.enums.ServiceNameEnum;
import com.doctor.doctorbasiccrud.biz.exception.TaskException;
import com.doctor.doctorbasiccrud.common.util.StringUtils;
import com.doctor.doctorbasiccrud.service.response.BaseResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponsePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    public static BaseResponse process(TransactionCallback callback) {
        BaseResponse response = null;
        ServiceNameEnum serviceName = null;
        try {
            serviceName = callback.getServiceName();
            callback.validate();
            response = callback.doTransaction();
        } catch (Exception e) {
            if (e instanceof TaskException) {
                response = (serviceName == ServiceNameEnum.GET_ALL_TASKS) ?
                        new TaskResponsePage(new ErrorContext(((TaskException) e).getErrorCodeEnum(), e.getMessage()))
                        : new TaskResponse(new ErrorContext(((TaskException) e).getErrorCodeEnum(), e.getMessage()));
            } else {
                response = (serviceName == ServiceNameEnum.GET_ALL_TASKS) ?
                        new TaskResponsePage(new ErrorContext(ErrorCodeEnum.UNKNOWN_ERROR, null))
                        : new TaskResponse(new ErrorContext(ErrorCodeEnum.UNKNOWN_ERROR, null));
            }
        } finally {
            if (response != null && response.getErrorContext().getErrorCode() == ErrorCodeEnum.SUCCESS) {
                LOGGER.info(getDigestLog(serviceName, response));
            } else {
                LOGGER.error(getDigestLog(serviceName, response));
            }
        }
        return response;
    }

    private static String getDigestLog(ServiceNameEnum serviceName, BaseResponse response) {
        if (response == null || serviceName == null || StringUtils.isBlank(serviceName.getName())) {
            return "NO INFO!";
        }
        String result = "DIGEST_LOG(" + serviceName.getName() + ",";
        result += response.getErrorContext().getErrorCode().getName() + ",";
        if (response instanceof TaskResponse res) {
            result += res.getTask().getId() + ",";
            result += res.getTask().getName() + ",";
            result += res.getTask().isCompleted();
        } else if (response instanceof TaskResponsePage res){
            result += res.getTasks().size();
        }
        return result + ")";
    }
}
