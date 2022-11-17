package com.primechord.stubwebserver

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.primechord.stubwebserver.application.MyApplication
import com.primechord.stubwebserver.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val thread = Thread {
                try {
                    val service = (requireActivity().application as MyApplication)
                        .appCompositionRoot.gitHubService
                    val repos = service.listRepos(user = "primechord")
                    val owner = repos.execute().body()?.first()?.owner.toString()
                    Handler(Looper.getMainLooper()).post {
                        val textOnScreen = view.rootView.findViewById<TextView>(R.id.textview_first)
                        textOnScreen.text = owner
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}