package org.uncommons.reportng;

import com.ringcentral.qa.common.utils.CollectionUtils;
import com.ringcentral.qa.configuration.ConfigurationHolder;
import com.ringcentral.qa.configuration.knownfailures.KnownFailures;
import com.ringcentral.qa.jiraclient.Client;
import com.ringcentral.qa.jiraclient.JiraClientFactory;
import com.ringcentral.qa.jiraclient.JiraIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Anton Gnutov
 */
public class JiraConnector {
    private static final String UNKNOWN = "UNKNOWN";

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportNGUtils.class);

    private Client jiraClient;
    private Map<String, JiraIssue> issues = new TreeMap<>();

    public JiraConnector() {
        if (!"true".equalsIgnoreCase(System.getProperty("skipJiraCheck"))) {
            this.jiraClient = JiraClientFactory.create();
        }
    }

    /**
     * Answers JIRA issues map by key which satisfy known failures
     * @return map of JIRA issues by issue key
     */
    public Map<String, JiraIssue> getKnownFailures() {
        if (issues.size() == 0) {
            KnownFailures knownFailures = ConfigurationHolder.getInstance().getConfiguration().getKnownFailures();

            try {
                LOGGER.debug("Requesting known failures from jira...");
                for (String issue : knownFailures.getAllIssues()) {
                    if (!issues.containsKey(issue)) {
                        issues.put(issue, getIssue(issue));
                    }
                }
                LOGGER.debug("Done");
            } catch (Exception e) {
                LOGGER.warn("Could not receive information from jira", e);
            }

        }
        return issues;
    }

    private JiraIssue getIssue(String issueId) {
        JiraIssue issue = null;

        if (jiraClient != null) {
            issue = jiraClient.getIssue(issueId);
        }

        return issue != null ? issue : new JiraIssueStub(issueId);
    }

    public List<JiraIssue> getIssues(List<String> issues) {
        if (CollectionUtils.isNotEmpty(issues)) {
            List<JiraIssue> result = new ArrayList<>();
            for (String issue : issues) {
                if (jiraClient != null) {
                    final JiraIssue jiraIssue = jiraClient.getIssue(issue.trim());
                    if (jiraIssue != null) {
                        result.add(jiraIssue);
                    }
                }
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    private static class JiraIssueStub extends com.ringcentral.qa.jiraclient.JiraIssueStub {
        private JiraIssueStub(String key) {
            super(key);
        }

        @Override
        public String getSummary() {
            return "";
        }
    }
}
