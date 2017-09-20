package com.kamilkalfas.docy.compiler.env.model;

public class ModuleInfoDto {

    public static final ModuleInfoDto DEFAULT = new ModuleInfoDto();

    private Integer envModuleNumber;
    private Integer currentModuleNumber;

    private ModuleInfoDto() {
        envModuleNumber = -1;
        currentModuleNumber = -1;
    }

    public ModuleInfoDto(int envModuleNumber) {
        this.envModuleNumber = envModuleNumber;
        this.currentModuleNumber = 1;
    }

    public Integer getEnvModuleNumber() {
        return envModuleNumber;
    }

    public Integer getCurrentModuleNumber() {
        return currentModuleNumber;
    }

    public void moduleProcessed() {
        this.currentModuleNumber +=1;
    }

}
