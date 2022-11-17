package com.primechord.stubwebserver.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primechord.stubwebserver.base.BaseTest
import com.primechord.stubwebserver.screens.MainScreen
import com.primechord.stubwebserver.stub.ModelContainer
import com.primechord.stubwebserver.stub.mockResponses
import org.junit.Test
import org.junit.runner.RunWith
import com.primechord.stubwebserver.gen_models.Owner as GenOwner
import com.primechord.stubwebserver.gen_models.RepoItem as GenRepoItem
import com.primechord.stubwebserver.prod_models.Owner as ProdOwner
import com.primechord.stubwebserver.prod_models.RepoItem as ProdRepoItem

@RunWith(AndroidJUnit4::class)
class ModelStyleTests : BaseTest() {

    companion object {
        private const val REPOS_REQUEST_PATH = "/repos"
    }

    @Test
    fun withGenModelsFirst() {
        server.mockResponses( // намерение создать произвольное количество стабов
            REPOS_REQUEST_PATH to ModelContainer( // создается пара из Path и тела ответа
                listOf( // массив объектов в теле ответа
                    GenRepoItem( // первый объект в теле ответа
                        owner = GenOwner( // вложенный в него дочерний объект
                            // указываем значение, отличающееся от значения по умолчанию
                            // для того, чтобы в дальнейшем его протестировать
                            login = "expected text"
                        )
                    )
                )
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

    @Test
    fun withGenModelsSecond() {
        server.mockResponses(
            REPOS_REQUEST_PATH to ModelContainer(
                listOf(
                    GenRepoItem(
                        owner = GenOwner(
                            id = 1234567890
                        )
                    )
                )
            ),
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("1234567890")
                }
            }
        }
    }

    @Test
    fun withProdModels() {
        server.mockResponses(
            REPOS_REQUEST_PATH to ModelContainer(
                generateReposItemsFromProductionModels("defaultLogin")
            ),
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("defaultLogin")
                }
            }
        }
    }

    private fun generateReposItemsFromProductionModels(
        ownerLogin: String
    ): List<ProdRepoItem> {
        return listOf(
            ProdRepoItem(
                owner = ProdOwner(
                    login = ownerLogin,
                    avatar_url = "",
                    events_url = "",
                    followers_url = "",
                    following_url = "",
                    gists_url = "",
                    gravatar_id = "",
                    html_url = "",
                    id = 0,
                    node_id = "",
                    organizations_url = "",
                    received_events_url = "",
                    repos_url = "",
                    site_admin = false,
                    starred_url = "",
                    subscriptions_url = "",
                    type = "",
                    url = ""
                ),
                name = "defaultName",
                allow_forking = false,
                archive_url = "",
                archived = false,
                assignees_url = "",
                blobs_url = "",
                branches_url = "",
                clone_url = "",
                collaborators_url = "",
                comments_url = "",
                commits_url = "",
                compare_url = "",
                contents_url = "",
                contributors_url = "",
                created_at = "",
                default_branch = "",
                deployments_url = "",
                description = "",
                disabled = false,
                downloads_url = "",
                events_url = "",
                fork = false,
                forks = 0,
                forks_count = 0,
                forks_url = "",
                full_name = "",
                git_commits_url = "",
                git_refs_url = "",
                git_tags_url = "",
                git_url = "",
                has_downloads = false,
                has_issues = false,
                has_pages = false,
                has_projects = false,
                has_wiki = false,
                homepage = "",
                hooks_url = "",
                html_url = "",
                id = 0,
                is_template = false,
                issue_comment_url = "",
                issue_events_url = "",
                issues_url = "",
                keys_url = "",
                labels_url = "",
                language = "",
                languages_url = "",
                license = "",
                merges_url = "",
                milestones_url = "",
                mirror_url = "",
                node_id = "",
                notifications_url = "",
                open_issues = 0,
                open_issues_count = 0,
                pulls_url = "",
                pushed_at = "",
                releases_url = "",
                size = 0,
                ssh_url = "",
                stargazers_count = 0,
                stargazers_url = "",
                statuses_url = "",
                subscribers_url = "",
                subscription_url = "",
                svn_url = "",
                tags_url = "",
                teams_url = "",
                topics = listOf(),
                trees_url = "",
                updated_at = "",
                url = "",
                visibility = "",
                watchers = 0,
                watchers_count = 0,
                web_commit_signoff_required = false,
                privateField = true
            )
        )
    }
}
