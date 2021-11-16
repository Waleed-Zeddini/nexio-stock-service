package com.nexio.api.ms.config;

import java.net.URI;

/*
 * Copyright 2021 Zeddini, as indicated by the @author tags.
 *
 * Licensed under the zeddini License; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.zeddini.com/licenses/LICENSE-2.0
 *
 * @author  Zeddini Walid
 * @version 1.0.0
 * @since   2021-11-05 
 */
/**
 * Application constants. 
 */
public final class Constants {
	
    public static final String COMPANY_NAME = "NEXIO";
    public static final String APP_NAME = "Order MS";
    
    public static final String DTO_NAME_MS = "OrderDTO";

    //	 URL Rest to invocate
    //    public static final String URL_MS_PRODUIT = "http://order-service/order/";

    public static final String  PRODUIT_API_URL = "https://esitsi-pfa-api.herokuapp.com/api/produits/";
    
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    // ErrorConstants
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.zeddini.com/api/ms/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");

    
 
    private Constants() {
    }
}
