package com.example.udemy.logic;

import com.example.udemy.model.TaskGroup;
import com.example.udemy.model.TaskGroupRepository;
import com.example.udemy.model.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {

    @Test
    @DisplayName("Should throw when undone tasks")
    void toggleGroup_undoneTasks_throwsIllegalStateException() {
        //given
        TaskRepository mockTaskRepository = getTaskRepository(true);
        //system under test
        var toTest = new TaskGroupService(null, mockTaskRepository);

        //when
        var exception = catchThrowable(() -> toTest.toggleGroup(1));

        //then
        assertThat(exception).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("undone tasks");
    }

    @Test
    @DisplayName("Should throw when no group")
    void toggleGroup_wrongId_throwsIllegalArgumentException() {
        //given
        TaskRepository mockTaskRepository = getTaskRepository(false);
        //and
        var mockRepository = mock(TaskGroupRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        //system under test
        var toTest = new TaskGroupService(mockRepository, mockTaskRepository);

        //when
        var exception = catchThrowable(() -> toTest.toggleGroup(1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id not found");
    }

    @Test
    @DisplayName("Should throw when no group")
    void toggleGroup_worksAsExpected() {
        //given
        TaskRepository mockTaskRepository = getTaskRepository(false);
        //and
        var group = new TaskGroup();
        var beforeToggle = group.isDone();

        var mockRepository = mock(TaskGroupRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(group));
        //system under test
        var toTest = new TaskGroupService(mockRepository, mockTaskRepository);

        //when
        toTest.toggleGroup(0);

        //then
        assertThat(group.isDone()).isEqualTo(!beforeToggle);
    }

    private TaskRepository getTaskRepository(final boolean result) {
        var mockTaskRepository  = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(result);
        return mockTaskRepository;
    }
}