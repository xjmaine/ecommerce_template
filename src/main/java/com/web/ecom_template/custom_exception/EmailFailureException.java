package com.web.ecom_template.custom_exception;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public class EmailFailureException extends Exception{

    public final String MAIL_SERVER_CONNECTION_ERROR = "Mail server connection error";
    public final String MAIL_SERVER_CONNECTION_TIMEOUT = "Mail server connection timeout";
    public final String MAIL_NOT_SENT = "Mail not sent";
}
