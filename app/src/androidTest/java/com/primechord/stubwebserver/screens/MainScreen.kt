package com.primechord.stubwebserver.screens

import com.kaspersky.kaspresso.screens.KScreen
import com.primechord.stubwebserver.FirstFragment
import com.primechord.stubwebserver.R
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.fragment_first
    override val viewClass: Class<*> = FirstFragment::class.java

    val getDataButton = KButton { withId(R.id.button_first) }

    val mainTextView = KTextView { withId(R.id.textview_first) }
}
