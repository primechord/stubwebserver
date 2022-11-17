package com.primechord.stubwebserver.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primechord.stubwebserver.base.BaseTest
import com.primechord.stubwebserver.screens.MainScreen
import com.primechord.stubwebserver.stub.AssetContainer
import com.primechord.stubwebserver.stub.RawStringContainer
import com.primechord.stubwebserver.stub.mockResponses
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RawStyleTests : BaseTest() {

    companion object {
        private const val EXPECTED_LOGIN = "expected text"

        private const val REPOS_REQUEST_PATH = "/repos"
        private const val REPOS_RESPONSE_ASSET = "repos.json"
    }

    @Test
    fun withAsset() {
        server.mockResponses(
            REPOS_REQUEST_PATH to AssetContainer(REPOS_RESPONSE_ASSET),
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText(EXPECTED_LOGIN)
                }
            }
        }
    }

    // language=JSON
    private fun createResponseBodyForRepos(login: String) = """
          [
          {
            "allow_forking": false,
            "archive_url": "",
            "archived": false,
            "assignees_url": "",
            "blobs_url": "",
            "branches_url": "",
            "clone_url": "",
            "collaborators_url": "",
            "comments_url": "",
            "commits_url": "",
            "compare_url": "",
            "contents_url": "",
            "contributors_url": "",
            "created_at": "",
            "default_branch": "",
            "deployments_url": "",
            "description": "",
            "disabled": false,
            "downloads_url": "",
            "events_url": "",
            "fork": false,
            "forks": 0,
            "forks_count": 0,
            "forks_url": "",
            "full_name": "",
            "git_commits_url": "",
            "git_refs_url": "",
            "git_tags_url": "",
            "git_url": "",
            "has_downloads": false,
            "has_issues": false,
            "has_pages": false,
            "has_projects": false,
            "has_wiki": false,
            "homepage": "",
            "hooks_url": "",
            "html_url": "",
            "id": 0,
            "is_template": false,
            "issue_comment_url": "",
            "issue_events_url": "",
            "issues_url": "",
            "keys_url": "",
            "labels_url": "",
            "language": "",
            "languages_url": "",
            "license": "",
            "merges_url": "",
            "milestones_url": "",
            "mirror_url": "",
            "name": "",
            "node_id": "",
            "notifications_url": "",
            "open_issues": 0,
            "open_issues_count": 0,
            "owner": {
              "avatar_url": "",
              "events_url": "",
              "followers_url": "",
              "following_url": "",
              "gists_url": "",
              "gravatar_id": "",
              "html_url": "",
              "id": 0,
              "login": "$login",
              "node_id": "",
              "organizations_url": "",
              "received_events_url": "",
              "repos_url": "",
              "site_admin": false,
              "starred_url": "",
              "subscriptions_url": "",
              "type": "",
              "url": ""
            },
            "private": false,
            "pulls_url": "",
            "pushed_at": "",
            "releases_url": "",
            "size": 0,
            "ssh_url": "",
            "stargazers_count": 0,
            "stargazers_url": "",
            "statuses_url": "",
            "subscribers_url": "",
            "subscription_url": "",
            "svn_url": "",
            "tags_url": "",
            "teams_url": "",
            "topics": [],
            "trees_url": "",
            "updated_at": "",
            "url": "",
            "visibility": "",
            "watchers": 0,
            "watchers_count": 0,
            "web_commit_signoff_required": false
          }
        ]
    """.trimIndent()

    @Test
    fun withRawString() {
        val expectedResponseBody = createResponseBodyForRepos(EXPECTED_LOGIN)
        server.mockResponses(
            REPOS_REQUEST_PATH to RawStringContainer(expectedResponseBody),
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText(EXPECTED_LOGIN)
                }
            }
        }
    }
}
