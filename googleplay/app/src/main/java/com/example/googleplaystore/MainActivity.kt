package com.example.googleplaystore

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googleplaystore.databinding.ActivityMainBinding
import com.example.googleplaystore.databinding.AppCategoryBinding
import com.example.googleplaystore.databinding.AppItemBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var main: ActivityMainBinding

    data class AppItem(
        val appName: String,
        val appStarRate: String,
        val appIcon: Int
    )

    data class AppCategory(val categoryName: String, val appItems: List<AppItem>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        main = ActivityMainBinding.inflate(layoutInflater)
        val view = main.root
        setContentView(view)

        val categoryData = generateCategoryDataList()
        val categoryAdapter = CategoryAdapterView(categoryData)

        val categoryView = main.categoryRecyclerView
        categoryView.layoutManager = LinearLayoutManager(this)
        categoryView.adapter = categoryAdapter


    }

    inner class CategoryAdapterView(private val categories: List<AppCategory>) :
        RecyclerView.Adapter<CategoryAdapterView.CategoryViewHolder>() {
        inner class CategoryViewHolder(val binding: AppCategoryBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val binding =
                AppCategoryBinding.inflate(layoutInflater, parent, false)
            return CategoryViewHolder(binding)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = categories[position]
            with(holder.binding) {
                categoryName.text = category.categoryName

                val appItemAdapter = AppItemAdapterView(category.appItems)
                itemRecyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                itemRecyclerView.adapter = appItemAdapter
            }
        }

        override fun getItemCount(): Int {
            return categories.size
        }
    }

    inner class AppItemAdapterView(private val appItems: List<AppItem>) :
        RecyclerView.Adapter<AppItemAdapterView.AppItemViewHolder>() {
        inner class AppItemViewHolder(val binding: AppItemBinding) :
            RecyclerView.ViewHolder(binding.root)

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppItemViewHolder {
            val binding =
                AppItemBinding.inflate(layoutInflater, parent, false)
            return AppItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
            val item = appItems[position]
            with(holder.binding) {
                appName.text = item.appName
                appStar.text = item.appStarRate + "⭐"
                appIcon.setImageResource(item.appIcon)
            }

        }

        override fun getItemCount(): Int {
            return appItems.size
        }
    }

    private fun generateCategoryDataList(): List<AppCategory> {
        val categoryList = mutableListOf<AppCategory>()
        val categoryNames = arrayOf(
            "Game",
            "Social network",
            "Popular",
            "Education",
            "Shopping"
        )

        for (categoryName in categoryNames) {
            val appItemList = generateItemDataList(15)
            val category = AppCategory(categoryName, appItemList)
            categoryList.add(category)
        }

        return categoryList
    }

    private fun generateItemDataList(count: Int): List<AppItem> {
        val itemList = mutableListOf<AppItem>()

        repeat(count) {
            itemList.add(generateItemData())
        }

        return itemList
    }

    private fun generateItemData(): AppItem {
        val random = Random()

        val name = generateItemName()

        val star = 3.0f + 2.0f * random.nextFloat()
        val starRate = String.format("%.1f", star)

        val imageIndex = drawableResourceNames[random.nextInt(drawableResourceNames.size)]
        val packageName = applicationContext.packageName
        val imageSrc = resources.getIdentifier(imageIndex, "drawable", packageName)

        return AppItem(name, starRate, imageSrc)
    }

    private fun generateItemName(): String {
        val random = Random()

        return itemNames[random.nextInt(itemNames.size)]
    }

    private val itemNames = arrayOf(
        "Facebook",
        "Instagram",
        "WhatsApp",
        "TikTok",
        "YouTube",
        "Twitter",
        "Snapchat",
        "LinkedIn",
        "Netflix",
        "Spotify",
        "Shopee",
        "Be",
        "Amazon",
        "Google Maps",
        "Shazam",
        "Gmail",
        "Reddit",
        "Pinterest",
        "Zoom",
        "WhatsApp",
        "Pinterest",
        "Slack",
        "Discord",
        "Microsoft Teams",
        "Telegram",
        "Momo",
        "WeChat",
        "Skype",
        "Grab",
        "Duolingo"
    )

    private val drawableResourceNames = arrayOf(
        "icon_drive", "icon_gmail", "icon_insta", "icon_icon_stacbucks",
        "icon_team", "icon_netflix", "icon_twitter","icon_fb","icon_tiktok"
    )
}

