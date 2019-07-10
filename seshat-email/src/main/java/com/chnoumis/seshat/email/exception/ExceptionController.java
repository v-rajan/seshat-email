package com.chnoumis.seshat.email.exception;

import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.SEWSRespCode_001;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

@Service
public class ExceptionController {

	public ResponseComType createException(Throwable th) {
		ResponseComType response = new ResponseComType();
		response = new ResponseComType();
		response.setRespCode(SEWSRespCode_001.SEWS_UNHANDLE_ERROR);
		return response;
	}
}
