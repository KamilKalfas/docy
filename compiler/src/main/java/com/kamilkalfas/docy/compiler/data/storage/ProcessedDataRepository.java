package com.kamilkalfas.docy.compiler.data.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kamilkalfas.docy.compiler.contract.IFile;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProcessedDataRepository implements Repository<List<AnnotationsDto>> {

    private Gson gson;
    private IFile storeFile;

    public ProcessedDataRepository(final Gson gson, final IFile storeFile) {
        this.gson = gson;
        this.storeFile = storeFile;
    }

    @Override
    public List<AnnotationsDto> get() {
        List<AnnotationsDto> retVal = new ArrayList<>();
        try {
            final Path path = storeFile.createFile();
            final String json = storeFile.read(path);
            final Type typeToken = new TypeToken<List<AnnotationsDto>>() {}.getType();
            retVal = gson.fromJson(json, typeToken);
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
