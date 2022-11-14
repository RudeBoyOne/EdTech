package com.edtech_api.integration_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({com.edtech_api.integration_tests.CarreiraIntegrationTest.class, com.edtech_api.integration_tests.BootcampIntegrationTest.class})
public class SuiteBootcampCarreiraTest {

}
