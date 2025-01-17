package com.qaprosoft.carina.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.api.*;
import com.qaprosoft.carina.demo.model.Collection;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class APICarinaTest implements IAbstractTest {

    @Test()
    @MethodOwner(owner = "ygalitsyna")
    public void testGetPhotos() {
        GetPhotosMethod getPhotosMethod = new GetPhotosMethod();
        Response response = getPhotosMethod.callAPIExpectSuccess();
        getPhotosMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getPhotosMethod.validateResponseAgainstSchema("api/photos/_get/rs.schema");
        JsonPath json = response.body().jsonPath();
        Integer total = Integer.valueOf(json.getString("total"));
        Integer totalPages = Integer.valueOf(json.getString("total_pages"));
        Assert.assertEquals(total, totalPages);
    }

    @Test()
    @MethodOwner(owner = "ygalitsyna")
    public void testGetPhotosUnauthorized() {
        GetPhotosUnauthorizedMethod getPhotosUnauthorizedMethod = new GetPhotosUnauthorizedMethod();
        getPhotosUnauthorizedMethod.callAPIExpectSuccess();
        getPhotosUnauthorizedMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getPhotosUnauthorizedMethod.validateResponseAgainstSchema("api/photos_unauthorized/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testGetThreePhotosOnPage() {
        GetThreePhotosOnPageMethod getThreePhotosOnPageMethod = new GetThreePhotosOnPageMethod();
        getThreePhotosOnPageMethod.callAPIExpectSuccess();
        getThreePhotosOnPageMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getThreePhotosOnPageMethod.validateResponseAgainstSchema("api/three_photos/_get/rs.schema");
    }


    @Test()
    @MethodOwner(owner = "ygalitsyna")
    public void testGetCollectionsMethod(){
        GetCollectionsMethod getCollectionsMethod = new GetCollectionsMethod();
        Response response = getCollectionsMethod.callAPIExpectSuccess();
        getCollectionsMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCollectionsMethod.validateResponseAgainstSchema("api/collections/_get/rs.schema");
        JsonPath json = response.body().jsonPath();
        Integer total = Integer.valueOf(json.getString("total"));
        Integer totalPages = Integer.valueOf(json.getString("total_pages"));
        Assert.assertEquals(total, totalPages);
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testGetFiveCollectionsOnPage(){
        GetFiveCollectionsOnPageMethod getFiveCollectionsOnPageMethod = new GetFiveCollectionsOnPageMethod();
        getFiveCollectionsOnPageMethod.callAPIExpectSuccess();
        getFiveCollectionsOnPageMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getFiveCollectionsOnPageMethod.validateResponseAgainstSchema("api/five_collections/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testGetCollectionById(){
        GetCollectionByIdMethod getCollectionByIdMethod =  new GetCollectionByIdMethod(9242294);
        getCollectionByIdMethod.callAPIExpectSuccess();
        getCollectionByIdMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCollectionByIdMethod.validateResponseAgainstSchema("api/collection_by_id/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testGetCollectionsPhotosById(){
        GetCollectionsPhotosByIdMethod getCollectionsPhotosByIdMethod = new GetCollectionsPhotosByIdMethod(9242294);
        getCollectionsPhotosByIdMethod.callAPIExpectSuccess();
        getCollectionsPhotosByIdMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCollectionsPhotosByIdMethod.validateResponseAgainstSchema("api/collection_photos/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "ygalitsyna")
    public void testGetCheckDefaultPaginationMethod() throws JsonProcessingException {
        GetCheckDefaultPaginationMethod getCheckDefaultPaginationMethod = new GetCheckDefaultPaginationMethod(9242294);
        Response response = getCheckDefaultPaginationMethod.callAPIExpectSuccess();
        String json = response.body().asString();
        ObjectMapper om = new ObjectMapper();
        List<Collection> list = om.readValue(json, new TypeReference<>(){});
        Assert.assertEquals(list.size(), 10, "The default pagination (10 items per page) is not respected.");
    }
}
