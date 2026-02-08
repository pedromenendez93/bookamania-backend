package com.bookamania;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

@org.springframework.modulith.test.ApplicationModuleTest
public class ApplicationModuleTest {


    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(BookamaniaApplication.class).verify();
    }
}
