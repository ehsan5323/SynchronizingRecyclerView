package ir.example.newstest.ui.home

import androidx.lifecycle.MutableLiveData
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.usecase.GetMockMetaDataUseCase
import ir.example.newstest.domain.usecase.GetMockNewsUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    mockNewsUseCase: GetMockNewsUseCase,
    mockMetaDataUseCase: GetMockMetaDataUseCase
) :
    BaseViewModel() {

    private var resultList = mutableListOf<News>()
    private var metaDataResultList = mutableListOf<MetaData>()

    val list = MutableLiveData<MutableList<News>>()
    val metaDataList = MutableLiveData<MutableList<MetaData>>()

    init {
        resultList = mockNewsUseCase(Unit)
        list.value = resultList
        metaDataResultList = mockMetaDataUseCase(Unit)
        metaDataList.value = metaDataResultList
    }


}