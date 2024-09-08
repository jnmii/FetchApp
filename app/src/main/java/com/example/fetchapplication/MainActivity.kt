package com.example.fetchapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchapplication.data.ItemsRepositoryImpl
import com.example.fetchapplication.data.model.Item
import com.example.fetchapplication.presentation.ItemsViewModel
import com.example.fetchapplication.ui.theme.FetchApplicationTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ItemsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ItemsViewModel(ItemsRepositoryImpl(FetchInstance.api))
                as T
            }
        }
    } )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchApplicationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val itemList = viewModel.items.collectAsState().value
                    val context = LocalContext.current

                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show->
                            if(show){
                                Toast.makeText(
                                    context, "Error", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    if (itemList.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }

                }else{
                        GroupedItemList(itemList)

                    }                }
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupedItemList(itemList: List<Item>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Grouped items by listId
        val groupedItems = itemList.groupBy { it.listId }

        groupedItems.forEach { (listId, items) ->
            // Sticky header for each group
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = "ID Group: $listId",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    // Spacer between header and items
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            // Display the items in the group
            items(items.size) { index ->
                Item(items[index])
                Spacer(modifier = Modifier.height(16.dp)) // Space between items
            }
        }
    }
}

@Composable
fun Item(item: Item){
    Column (modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .height(65.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primaryContainer)) { Text(modifier = Modifier.padding(horizontal = 16.dp ),text = " List Id: ${item.listId}",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
    )
        Spacer(modifier = Modifier.height(6.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp ),text = "Item Name :${item.id}",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

    }



}

