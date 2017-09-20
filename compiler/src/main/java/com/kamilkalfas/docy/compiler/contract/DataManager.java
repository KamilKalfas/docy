package com.kamilkalfas.docy.compiler.contract;

import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;

import java.util.List;

public interface DataManager {

    List<AnnotationsDto> assemble();

    void clearStore();

    void clearEnv();
}
