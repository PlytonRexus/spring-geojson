package com.sandbox.sandbox;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sandbox.sandbox.models.GithubUser;
import com.sandbox.sandbox.models.repositories.GithubUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GithubUserController {

    @Autowired private GithubUserRepository repo;
    private RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<GithubUser> findUser(String name) {
        String url = String.format("https://api.github.com/users/%s", name);
        GithubUser results = restTemplate.getForObject(url, GithubUser.class);
        return CompletableFuture.completedFuture(results);
    }

    @GetMapping("/github")
    public GithubUser ghcontroller(
        @RequestParam(value = "name", defaultValue = "github") String name, 
        @RequestParam(value = "save", defaultValue = "false") boolean save)
        throws InterruptedException, ExecutionException {
        GithubUser tmp = findUser(name).get();
        if (save) {
            repo.save(tmp);
        }
        return tmp;
    }

    @PostMapping("/github")
    public GithubUser saveUser(
        @RequestParam(value = "name") String name) throws InterruptedException, ExecutionException {
        GithubUser tmp = findUser(name).get();
        repo.save(tmp);
        return tmp;
    }
}
