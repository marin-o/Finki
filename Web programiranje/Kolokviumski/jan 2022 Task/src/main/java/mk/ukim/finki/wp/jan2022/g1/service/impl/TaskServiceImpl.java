package mk.ukim.finki.wp.jan2022.g1.service.impl;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.TaskCategory;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidTaskIdException;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidUserIdException;
import mk.ukim.finki.wp.jan2022.g1.repository.TaskRepository;
import mk.ukim.finki.wp.jan2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.jan2022.g1.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);
    }

    @Override
    public Task create(String title, String description, TaskCategory category, List<Long> assignees, LocalDate dueDate) {
        List<User> assigneesList = userRepository.findAllById(assignees);
//        if(assigneesList.size() < assignees.size())
//            throw new InvalidTaskIdException();

        return taskRepository.save(
                new Task(title,description,category,assigneesList,dueDate)
        );
    }

    @Override
    public Task update(Long id, String title, String description, TaskCategory category, List<Long> assignees) {
        List<User> assigneesList = userRepository.findAllById(assignees);
//        if(assigneesList.size() < assignees.size())
//            throw new InvalidTaskIdException();

        Task task = findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setAssignees(assigneesList);

        return taskRepository.save(task);
    }

    @Override
    public Task delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public Task markDone(Long id) {
        Task task = findById(id);
        task.setDone(true);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<Task> filter(Long assigneeId, Integer lessThanDayBeforeDueDate) {
        if(assigneeId == null && lessThanDayBeforeDueDate == null){
            return listAll();
        }
        if(assigneeId == null){
            return taskRepository.findByDueDateLessThan(LocalDate.now().plusDays(lessThanDayBeforeDueDate));
        }
        if(lessThanDayBeforeDueDate == null){
            return taskRepository.findByAssigneesContaining(userRepository.findById(assigneeId).orElseThrow(InvalidUserIdException::new));
        }
        return taskRepository.findByAssigneesContainingAndDueDateLessThan(userRepository.findById(assigneeId).orElseThrow(InvalidUserIdException::new),LocalDate.now().plusDays(lessThanDayBeforeDueDate));
    }
}
