package com.kamilkalfas.docy.compiler.env;

import com.google.gson.Gson;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.contract.FileStore;
import com.kamilkalfas.docy.compiler.debug.tools.LogDecorator;
import com.kamilkalfas.docy.compiler.env.model.ModuleInfoDto;

import java.io.IOException;
import java.nio.file.Path;

public class EnvRepository implements Repository<ModuleInfoDto> {

    private final Gson gson;
    private final FileStore store;

    public EnvRepository(Gson gson, FileStore store) {
        this.gson = gson;
        this.store = store;
    }

    @Override
    public ModuleInfoDto get(Path path) {
        ModuleInfoDto retVal = ModuleInfoDto.DEFAULT;
        try {
            final String json = store.read(path);
            retVal = gson.fromJson(json, ModuleInfoDto.class);
        } catch (IOException ioe) {
            LogDecorator.warningJson(ioe.getMessage());
        }
        if (null == retVal) {
            retVal = ModuleInfoDto.DEFAULT;
        }
        return retVal;
    }

    @Override
    public boolean put(ModuleInfoDto instance) {
        boolean persisted;
        try {
            Path path = store.createFile();
            String json = gson.toJson(instance);
            store.write(path, json.getBytes());
            persisted = true;
        } catch (IOException ioe) {
            LogDecorator.warningJson(ioe.getMessage());
            persisted = false;
        }
        return persisted;
    }
}
