package com.briozing.employee.helpers;

import com.briozing.employee.common.Configuration;
import com.briozing.employee.models.EmployeeRequestVO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;


public class EmployeeHelper {

    private RequestSpecification requestSpecification;

    public EmployeeHelper() {
        requestSpecification = with()
                .contentType(io.restassured.http.ContentType.JSON)
                .log().all()
                .baseUri(Configuration.apiServer);
        System.out.println("Request Specification object is created");
    }
    public Response addEmployee(EmployeeRequestVO employeeRequestVO, int status) throws IOException {
        System.out.println("VO in :" + employeeRequestVO.toString());
        System.out.println("API url is "+ Configuration.apiServer);
        System.out.println("Request Specification is "+requestSpecification.toString());
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(employeeRequestVO)
                .post("/Employee/add");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }


}