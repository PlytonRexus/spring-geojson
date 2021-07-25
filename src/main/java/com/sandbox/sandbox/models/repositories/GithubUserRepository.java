package com.sandbox.sandbox.models.repositories;

import java.util.List;

import com.sandbox.sandbox.models.GithubUser;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GithubUserRepository extends MongoRepository<GithubUser, String> {
    public GithubUser findByName(String name);
    public List<GithubUser> findByCompany(String company);
}
