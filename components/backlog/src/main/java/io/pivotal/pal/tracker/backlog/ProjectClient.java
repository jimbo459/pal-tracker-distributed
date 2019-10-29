package io.pivotal.pal.tracker.backlog;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProjectClient {

    private final RestOperations restOperations;
    private final String endpoint;
    private ConcurrentMap<Long, ProjectInfo> projectInfoCache = new ConcurrentHashMap<>();

    private ProjectInfoRedisRepository redisRepository;


    public ProjectClient(RestOperations restOperations, String registrationServerEndpoint, ProjectInfoRedisRepository redisRepository) {
        this.restOperations = restOperations;
        this.endpoint = registrationServerEndpoint;
        this.redisRepository = redisRepository;
    }

    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        ProjectInfo projectInfo =  restOperations.getForObject(endpoint + "/projects/" + projectId, ProjectInfo.class);
        redisRepository.save(projectInfo);
        return projectInfo;
    }
    public ProjectInfo getProjectFromCache(long projectId) {
        return redisRepository.findById(projectId).get();
    }
}
