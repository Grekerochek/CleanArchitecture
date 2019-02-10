package com.alexander.networking.domain.executors;

import com.alexander.networking.domain.interactors.AbstractInteractor;

public interface Executor {
    void execute(AbstractInteractor interactor);
}
