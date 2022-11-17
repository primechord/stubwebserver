package com.primechord.stubwebserver.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primechord.stubwebserver.base.BaseTest
import com.primechord.stubwebserver.screens.MainScreen
import com.primechord.stubwebserver.stub.KeyValueContainer
import com.primechord.stubwebserver.stub.mockResponses
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapStyleTests : BaseTest() {

    companion object {
        private const val REPOS_REQUEST_PATH = "/repos"
    }

    private fun createResponseBodyForRepos(login: String): List<Map<String, Any>> {
        return listOf(
            mapOf(
                "allow_forking" to false,
                "archive_url" to "",
                "archived" to false,
                "assignees_url" to "",
                "blobs_url" to "",
                "branches_url" to "",
                "clone_url" to "",
                "collaborators_url" to "",
                "comments_url" to "",
                "commits_url" to "",
                "compare_url" to "",
                "contents_url" to "",
                "contributors_url" to "",
                "created_at" to "",
                "default_branch" to "",
                "deployments_url" to "",
                "description" to "",
                "disabled" to false,
                "downloads_url" to "",
                "events_url" to "",
                "fork" to false,
                "forks" to 0,
                "forks_count" to 0,
                "forks_url" to "",
                "full_name" to "",
                "git_commits_url" to "",
                "git_refs_url" to "",
                "git_tags_url" to "",
                "git_url" to "",
                "has_downloads" to false,
                "has_issues" to false,
                "has_pages" to false,
                "has_projects" to false,
                "has_wiki" to false,
                "homepage" to "",
                "hooks_url" to "",
                "html_url" to "",
                "id" to 0,
                "is_template" to false,
                "issue_comment_url" to "",
                "issue_events_url" to "",
                "issues_url" to "",
                "keys_url" to "",
                "labels_url" to "",
                "language" to "",
                "languages_url" to "",
                "license" to "",
                "merges_url" to "",
                "milestones_url" to "",
                "mirror_url" to "",
                "name" to "",
                "node_id" to "",
                "notifications_url" to "",
                "open_issues" to 0,
                "open_issues_count" to 0,
                "owner" to mapOf(
                    "login" to login, // expected value
                    "avatar_url" to "",
                    "events_url" to "",
                    "followers_url" to "",
                    "following_url" to "",
                    "gists_url" to "",
                    "gravatar_id" to "",
                    "html_url" to "",
                    "id" to 0,
                    "node_id" to "",
                    "organizations_url" to "",
                    "received_events_url" to "",
                    "repos_url" to "",
                    "site_admin" to false,
                    "starred_url" to "",
                    "subscriptions_url" to "",
                    "type" to "",
                    "url" to ""
                ),
                "private" to false,
                "pulls_url" to "",
                "pushed_at" to "",
                "releases_url" to "",
                "size" to 0,
                "ssh_url" to "",
                "stargazers_count" to 0,
                "stargazers_url" to "",
                "statuses_url" to "",
                "subscribers_url" to "",
                "subscription_url" to "",
                "svn_url" to "",
                "tags_url" to "",
                "teams_url" to "",
                "topics" to emptyList<String>(),
                "trees_url" to "",
                "updated_at" to "",
                "url" to "",
                "visibility" to "",
                "watchers" to 0,
                "watchers_count" to 0,
                "web_commit_signoff_required" to false
            )
        )
    }

    @Test
    fun withKeyValue() {
        server.mockResponses(
            REPOS_REQUEST_PATH to KeyValueContainer(
                createResponseBodyForRepos(login = "expected text")
            )
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("expected text")
                }
            }
        }
    }
}
