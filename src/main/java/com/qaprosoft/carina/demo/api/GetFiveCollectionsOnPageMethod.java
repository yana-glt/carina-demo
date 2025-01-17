package com.qaprosoft.carina.demo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.Configuration;

@Endpoint(url = "${base_url}/search/collections?client_id=${token}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/five_collections/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetFiveCollectionsOnPageMethod extends AbstractApiMethodV2 {

    public GetFiveCollectionsOnPageMethod(){
        addParameter("query", "krakow");
        addParameter("page", "1");
        addParameter("per_page", "5");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url_unsplash"));
        replaceUrlPlaceholder("token", Configuration.getEnvArg("access_token"));
    }
}
