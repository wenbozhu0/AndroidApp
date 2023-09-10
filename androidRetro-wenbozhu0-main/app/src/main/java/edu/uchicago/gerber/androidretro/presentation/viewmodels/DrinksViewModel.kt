package edu.uchicago.gerber.androidretro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import edu.uchicago.gerber.androidretro.common.DrinksConstants
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import edu.uchicago.gerber.androidretro.data.repository.ApiProvider
import edu.uchicago.gerber.androidretro.data.repository.DrinksRepository
import edu.uchicago.gerber.androidretro.data.repository.MongoRepository
import edu.uchicago.gerber.androidretro.presentation.screens.Paginate
import edu.uchicago.gerber.androidretro.presentation.screens.favorites.DrinksFavoritesOperation
import edu.uchicago.gerber.androidretro.presentation.screens.favorites.DrinksFavoritesState
import edu.uchicago.gerber.androidretro.presentation.screens.favorites.DrinksMongoPostSource
import edu.uchicago.gerber.androidretro.presentation.screens.favorites.DrinksMongoSource
import edu.uchicago.gerber.androidretro.presentation.screens.search.drinks.DrinksSearchOperation
import edu.uchicago.gerber.androidretro.presentation.screens.search.drinks.DrinksSearchState
import edu.uchicago.gerber.androidretro.presentation.screens.search.drinks.DrinksSource
import kotlinx.coroutines.launch

class DrinksViewModel: ViewModel() {
    private val drinksRepository: DrinksRepository = DrinksRepository(ApiProvider.drinksApi());
    private val mongoRepository: MongoRepository = MongoRepository(ApiProvider.mongoApi());

    private var _queryText = mutableStateOf("")
    val queryText: State<String> = _queryText


    private var _drinks = mutableStateOf(DrinksConstants.drinksItem)
    val drinks: State<DrinksInfo> = _drinks

    private val _searchState = mutableStateOf(DrinksSearchState())
    val searchState: State<DrinksSearchState> = _searchState

    private var _drinksFavorites = mutableStateOf(DrinksConstants.drinksItem)
    val drinksFavorites: State<DrinksInfo> = _drinksFavorites

    private val _favoritesState = mutableStateOf(DrinksFavoritesState())
    val favoritesState: State<DrinksFavoritesState> = _favoritesState

    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
    fun setDrinks(drinks: DrinksInfo) {
        _drinks.value = drinks
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }

    fun setDrinksFavorites(drinks: DrinksInfo) {
        _drinks.value = drinks
    }


    fun onSearch() {
        _searchState.value = DrinksSearchState(searchOperation = DrinksSearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = DrinksSearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        DrinksSource(
                            drinksRepository = drinksRepository,
                            paginateData = Paginate(
                                query = _queryText.value,
                            )
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = DrinksSearchOperation.DONE
            )
        }
    }

    fun onFavoritesList() {
        _favoritesState.value = DrinksFavoritesState(favoritesOperation = DrinksFavoritesOperation.LOADING)
        viewModelScope.launch {
            _favoritesState.value = DrinksFavoritesState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        DrinksMongoSource(
                            mongoRepository = mongoRepository,
                            paginateData = Paginate(
                                query = _queryText.value,
                            )
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                favoritesOperation = DrinksFavoritesOperation.DONE
            )
        }
    }

    fun onAddToFav(drinks: DrinksInfo) {
        // mongoRepository.addFavorites(drinks);
        viewModelScope.launch {
            DrinksMongoPostSource(
                mongoRepository = mongoRepository
            ).post(drinks)
        }
    }
}