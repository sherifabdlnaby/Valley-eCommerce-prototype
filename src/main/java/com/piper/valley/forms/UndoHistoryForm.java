package com.piper.valley.forms;

import javax.validation.constraints.NotNull;

public class UndoHistoryForm {

    @NotNull
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
