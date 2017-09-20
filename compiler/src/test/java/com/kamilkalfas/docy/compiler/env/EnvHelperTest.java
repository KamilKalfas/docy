package com.kamilkalfas.docy.compiler.env;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, EnvHelper.class})
public class EnvHelperTest {

    private EnvHelper cut;

    @Before public void setUp() throws Exception {
        cut = new EnvHelper();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNumberOfModules_whit_null_param() throws Exception {
        // when
        cut.getNumberOfModules(null);
    }

    @Test(expected = IllegalStateException.class)
    public void getNumberOfModules_whit_not_empty_param_but_no_variable_def() throws Exception {
        // when
        cut.getNumberOfModules("DOCY_MODULES");
    }

    @Test public void getNumberOfModules_returned_value() throws Exception {
        // given
        mockStatic(System.class);

        expect(System.getenv("DOCY_MODULES")).andReturn("1").anyTimes();
        replayAll();

        // when
        cut.getNumberOfModules("DOCY_MODULES");

        PowerMockito.verifyStatic();
        System.getenv("DOCY_MODULES");
    }

}