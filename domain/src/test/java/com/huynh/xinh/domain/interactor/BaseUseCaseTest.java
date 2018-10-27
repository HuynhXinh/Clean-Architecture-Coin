package com.huynh.xinh.domain.interactor;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;

import org.mockito.Mock;

public class BaseUseCaseTest {
    @Mock
    protected ThreadExecutor threadExecutor;

    @Mock
    protected PostExecutionThread postExecutionThread;
}
