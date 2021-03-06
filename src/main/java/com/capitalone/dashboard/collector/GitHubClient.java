package com.capitalone.dashboard.collector;

import com.capitalone.dashboard.misc.HygieiaException;
import com.capitalone.dashboard.model.ChangeRepoResponse;
import com.capitalone.dashboard.model.CollectorItemMetadata;
import com.capitalone.dashboard.model.Commit;
import com.capitalone.dashboard.model.GitHubRateLimit;
import com.capitalone.dashboard.model.GitRequest;
import com.capitalone.dashboard.model.webhook.github.GitHubRepo;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * Client for fetching commit history from GitHub
 */
public interface GitHubClient {


    ChangeRepoResponse getChangedRepos(long lastEventId, long lastEventTimeStamp) throws MalformedURLException, HygieiaException;
	List<Commit> getCommits();
    List<GitRequest> getPulls();
    List<GitRequest> getIssues();
    String getLDAPDN(GitHubRepo repo, String user);

    void fireGraphQL(GitHubRepo repo, boolean firstRun, Map<Long, String> existingPRMap, Map<Long, String> prCloseMap, int offsetMinutes) throws MalformedURLException, HygieiaException;

    GitHubRateLimit getRateLimit(GitHubRepo repo) throws MalformedURLException, HygieiaException;

    boolean isUnderRateLimit();

    long getRepoOffsetTime(GitHubRepo repo);

    void fetchMetadata (GitHubRepo repo, CollectorItemMetadata collectorItemMetadata) throws MalformedURLException, HygieiaException;
}
