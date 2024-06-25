package com.furkanreyhan.SimpleLogin.service;

import com.furkanreyhan.SimpleLogin.entity.Project;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.repos.ProjectRepository;
import com.furkanreyhan.SimpleLogin.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    ProjectRepository projectRepository;
    UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository,UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Project assignUsersToProject(Long projectId, Long userId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalProject.isPresent() && optionalUser.isPresent()) {
            Project project = optionalProject.get();
            User user = optionalUser.get();

            if (!project.getUserList().contains(user)) {
                project.getUserList().add(user);
                projectRepository.save(project);
            }
            return project;
        }
        throw new RuntimeException("Project or User not found");
    }
}

