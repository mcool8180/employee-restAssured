package com.briozing.employee.suites;

import com.briozing.employee.factory.Log4JFactory;
import com.briozing.employee.helpers.EmployeeHelper;
import com.briozing.employee.models.EmployeeRequestVO;
import com.briozing.employee.models.EmployeeResponseVO;
import com.briozing.employee.utils.AppAssert;
import com.briozing.employee.utils.MainUtils;
import com.briozing.employee.utils.TestSteps;
import com.briozing.employee.utils.TestValidationHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class EmployeeTESTS {

    private com.briozing.employee.factory.Log4JFactory Log4JFactory;
    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private EmployeeHelper employeeHelper;
    private TestValidationHelper validationHelper;
    private JsonPath jsonPath;
    @BeforeClass(alwaysRun = true)
    public void setup() {
        employeeHelper = new EmployeeHelper();
        validationHelper = new TestValidationHelper();
    }


        @Test(groups={"smoke","addEmployee"})
        public void verify_add_employee() {
            try {
                FileInputStream fileInputStream= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/AddEmployee.json"));
                ObjectMapper mapper = new ObjectMapper();
                EmployeeRequestVO employeeRequestVO = mapper.readValue(fileInputStream, EmployeeRequestVO.class);
                System.out.println("DTO : " + employeeRequestVO.toString());

                final Map<String, Boolean> testSteps = new HashMap<>();
                testSteps.put(TestSteps.STEP_ADD_EMPLOYEE.name(), true);
                validateAddEmployee(testSteps, employeeRequestVO);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.info(ex);
                AppAssert.assertTrue(false, "Fails");
            }
        }
        private void validateAddEmployee(Map<String, Boolean> testSteps,EmployeeRequestVO employeeRequestVO) throws Exception {
            if (null != testSteps.get(TestSteps.STEP_ADD_EMPLOYEE.name()) && testSteps.get(TestSteps.STEP_ADD_EMPLOYEE.name())) {
                MainUtils.stepLog(logger, TestSteps.STEP_ADD_EMPLOYEE.name());
                final EmployeeResponseVO response =employeeHelper.addEmployee(employeeRequestVO,200)
                        .getBody().as(EmployeeResponseVO.class);
                validationHelper.verify_add_employee(response,employeeRequestVO);
            }
        }
    }
