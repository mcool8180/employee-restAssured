package com.briozing.employee.utils;

import com.briozing.employee.factory.Log4JFactory;
import com.briozing.employee.models.EmployeeRequestVO;
import com.briozing.employee.models.EmployeeResponseVO;
import org.apache.log4j.Logger;

public class TestValidationHelper {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    public TestValidationHelper() {}
        public void verify_add_employee(EmployeeResponseVO actualResponse, EmployeeRequestVO employeeRequestVO) {
            logger.info("Id :- " + actualResponse.getId());
            AppAssert.assertTrue(String.valueOf(actualResponse.getId()) != null, "Employee Id is not null");
            logger.info("Name :- " + actualResponse.getName());
            AppAssert.assertTrue(actualResponse.getName()!= null, "Employee name is not null");
            logger.info("Email :- " + actualResponse.getEmailId());
            AppAssert.assertTrue(actualResponse.getEmailId()!= null, "Employee email is not null");
            AppAssert.assertEqual(actualResponse.getName(), employeeRequestVO.getName(),"Name : ");
            AppAssert.assertEqual(actualResponse.getEmailId(), employeeRequestVO.getEmailId(),"Email : ");
        }
    }

