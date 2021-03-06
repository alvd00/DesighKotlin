package com.example.desighkotlin.view.picture

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.FragmentPodStartBinding
import com.example.desighkotlin.planets.ApiActivity
import com.example.desighkotlin.planets.ApiBottomActivity
import com.example.desighkotlin.utils.ERROR_TEXT
import com.example.desighkotlin.utils.FAVORITE
import com.example.desighkotlin.utils.LOADING_TEXT
import com.example.desighkotlin.view.MainActivity
import com.example.desighkotlin.view.settings.SettingsFragment
import com.example.desighkotlin.viewmodel.PODData
import com.example.desighkotlin.viewmodel.PODViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PODFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: FragmentPodStartBinding? = null
    val binding: FragmentPodStartBinding
        get() {
            return _binding!!
        }

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPodStartBinding.inflate(inflater)

        super.onCreate(savedInstanceState)
        setActionBar()
       /* binding.scroll.setOnScrollChangeListener { it, y, u, i, o ->
            binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        }*/


        return binding.root
    }

    private var isMain = true

    @SuppressLint("ResourceAsColor")
    private fun setActionBar() {
        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null // ?????????? ?????????????????? ???????????? ??????????????
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                    )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()

/*?????????????? ????????, ???? ???????????? ?????? ????????????, ?? mainActivity ?????????????? ???????????????? ????????, ???? ????????????????. ?????? ???? ???????????????? ?? ?????????????????? ?????????? ???????? ???? ????????????????????(
      binding.changeTheme.setOnClickListener {
          android.Manifest.
          binding.main.setBackgroundResource(R.drawable.universe)
      }*/

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            )
        }
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun setData(data: PODData.Success) {
        val url = data.serverResponseData.hdurl
        if (url.isNullOrEmpty()) {
            val videoUrl = data.serverResponseData.url
            videoUrl?.let { showAVideoUrl(it) }
        } else {
            binding.customView.load(url)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun showAVideoUrl(videoUrl: String) = with(binding) {
        customView.visibility = View.GONE
        videoOfTheDay.visibility = View.VISIBLE
        videoOfTheDay.text = "?????????????? ?? ?????? ?????? ???????????????? ??????, ???? ???????? ?????????? ??????! " + "${videoUrl.toString()} \n ???????????? >??????????< ?????????? ?????????????? ?? ?????????? ????????"
        videoOfTheDay.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(videoUrl)
            }
            startActivity(i)
        }
    }



    private fun renderData(data: PODData) {
        when (data) {
            is PODData.Success -> {
                setData(data)
//                binding.customView.load(data.serverResponseData.url) {
//                    error(R.drawable.ic_load_error_vector)
//                    placeholder(R.drawable.progress_image_animation)
//                }
//                binding.descriptionPhoto.setText(data.serverResponseData.explanation)
            }
            is PODData.Loading -> {
                binding.customView.load(R.drawable.progress_image_animation){
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is PODData.Error -> {
                Toast.makeText(context, ERROR_TEXT, Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PODFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_api_activity -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, ApiActivity::class.java))
            }

            R.id.action_api_bottom_activity -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, ApiBottomActivity::class.java))
            }
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container,SettingsFragment.newInstance()).addToBackStack("").commit()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }


}