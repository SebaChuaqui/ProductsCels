package com.example.productscels

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productscels.ViewModel.MyViewModel
import com.example.productscels.adapter.AdapterProducts
import com.example.productscels.pojos.ProductsItem
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), AdapterProducts.ProductsId {

    lateinit var  mProductsViewModel: MyViewModel
    var mId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProductsViewModel= ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView
        val mAdapter = AdapterProducts(this)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(context, 2)

        mProductsViewModel.mAllProducts.observe(viewLifecycleOwner, Observer{
            mAdapter.updateListProducts(it)
            Log.d("funciona", it.toString())
        })

    }

    override fun passData(mProductId: ProductsItem) {
        val mBundle = Bundle()
        mBundle.putInt("id", mProductId.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }
}
