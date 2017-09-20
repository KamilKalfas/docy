package com.kamilkalfas.docy.compiler.env;

import com.google.gson.Gson;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.contract.Store;
import com.kamilkalfas.docy.compiler.env.model.ModuleInfoDto;

import java.io.IOException;
import java.nio.file.Path;

public class EnvRepository implements Repository<ModuleInfoDto> {

    private final Gson gson;
    private final Store store;

    public EnvRepository(Gson gson, Store store) {
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
            ioe.printStackTrace();
        }

        return retVal;
    }

    @Override
    public boolean put(ModuleInfoDto instance) {
        boolean peristed;
        try {
            Path path = store.createFile();
            String json = gson.toJson(instance);
            store.write(path, json.getBytes());
            peristed = true;
        } catch (IOException e) {
            e.printStackTrace();
            peristed = false;
        }
        return peristed;
    }
}
