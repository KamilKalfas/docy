package com.kamilkalfas.docy.compiler.env;

import com.google.gson.Gson;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.contract.Store;
import com.kamilkalfas.docy.compiler.env.model.ModuleInfoDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Path;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
public class EnvRepositoryTest {

    private Gson mockGson;
    @Mock private Store mockStore;

    private Repository<ModuleInfoDto> cut;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockGson = PowerMockito.mock(Gson.class);

        when(mockGson.toJson(any())).thenReturn("im not a json");

        cut = new EnvRepository(mockGson, mockStore);
    }

    @Test public void get() throws Exception {
        // when
        cut.get(any(Path.class));

        // then
        verify(mockStore, times(1)).read(any(Path.class));
        verify(mockGson, times(1)).fromJson(anyString(), same(ModuleInfoDto.class));
    }

    @Test public void put() throws Exception {
        // when
        boolean actual = cut.put(ModuleInfoDto.DEFAULT);

        // then
        verify(mockStore, times(1)).createFile();
        verify(mockGson, times(1)).toJson(any(ModuleInfoDto.class));
        verify(mockStore, times(1)).write(any(Path.class), any(byte[].class));

        assertTrue("Object should be persisted", actual);
    }

}