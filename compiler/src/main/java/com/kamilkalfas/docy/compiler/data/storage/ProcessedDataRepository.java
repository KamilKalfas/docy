package com.kamilkalfas.docy.compiler.data.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kamilkalfas.docy.compiler.contract.FileStore;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.debug.tools.LogDecorator;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProcessedDataRepository implements Repository<List<AnnotationsDto>> {

    public static final Type TYPED_TOKEN = new TypeToken<List<AnnotationsDto>>() {}.getType();
    private Gson gson;
    private FileStore storeFile;

    public ProcessedDataRepository(final Gson gson, final FileStore storeFile) {
        this.gson = gson;
        this.storeFile = storeFile;
    }

    @Override
    public List<AnnotationsDto> get(final Path path) {
        List<AnnotationsDto> retVal = new ArrayList<>();
        try {
            final String json = storeFile.read(path);
            retVal = gson.fromJson(json, TYPED_TOKEN);
        } catch (IOException ioe) {
            LogDecorator.warningJson(ioe);
        }
        return retVal;
    }

    @Override
    public boolean put(List<AnnotationsDto> instance) {
        boolean persisted;
        if (null == instance || instance.isEmpty()) {
            persisted = false;
        } else {
            try {
                Path path = storeFile.createFile();
                String json = gson.toJson(instance);
                storeFile.write(path, json.getBytes());
                persisted = true;
            } catch (IOException e) {
                e.printStackTrace();
                persisted = false;
            }

        }
        return persisted;
    }
}
