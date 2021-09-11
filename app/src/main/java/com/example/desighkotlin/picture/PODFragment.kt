package com.example.desighkotlin.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.desighkotlin.R
import com.example.desighkotlin.databinding.FragmentMainBinding
import com.example.desighkotlin.utils.ERROR_TEXT
import com.example.desighkotlin.utils.FAVORITE
import com.example.desighkotlin.utils.LOADING_TEXT
import com.example.desighkotlin.utils.SETTINGS
import com.example.desighkotlin.view.MainActivity
import com.example.desighkotlin.viewmodel.PODData
import com.example.desighkotlin.viewmodel.PODViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PODFragment : Fragment() {


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
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
        _binding = FragmentMainBinding.inflate(inflater)

        super.onCreate(savedInstanceState)
        setActionBar()

        return binding.root
    }

    private var isMain = true
    private fun setActionBar() {

        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null // лучше придумать замену бургеру
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
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            )

        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        /*
            bottomSheetBehavior.addBottomSheetCallback(

                object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        when (newState) {
                            BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                            BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                            BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                            BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                            BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                            BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        TODO("not implemented")
                    }
                }

            )*/
    }

    private fun renderData(data: PODData) {

        when (data) {
            is PODData.Success -> {
                binding.customView.load(data.serverResponseData.url) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is PODData.Loading -> {
                Toast.makeText(context, LOADING_TEXT, Toast.LENGTH_LONG).show()
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
            R.id.app_bar_fav -> {
                Toast.makeText(context, FAVORITE, Toast.LENGTH_SHORT).show()
            }

            R.id.app_bar_settings -> {
                Toast.makeText(context, SETTINGS, Toast.LENGTH_SHORT).show()
            }

            android.R.id.home -> {

                BottomNavigationDrawerFragment.newInstance().show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}