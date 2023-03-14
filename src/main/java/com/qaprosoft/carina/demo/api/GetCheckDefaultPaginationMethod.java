package com.qaprosoft.carina.demo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.Configuration;

@Endpoint(url = "${base_url}/collections/9242294/photos", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCheckDefaultPaginationMethod extends AbstractApiMethodV2 {

    public GetCheckDefaultPaginationMethod() {
        addParameter("client_id", "oZqNRI9G64U6XQonRp5aZduKHySCnEB55zH_Xuko-ZI");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url_unsplash"));
    }
}
