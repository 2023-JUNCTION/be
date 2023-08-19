package com.example.api.dto

data class ArticleData(
  val ARTICLE_ID: String,
  val ARTICLE_NAME: String,
  val NFC_URL: String,
  val SALE_PRICE: String,
  val DISCOUNT_PRICE: String
)

data class Article(
  val articleId: String,
  val articleName: String,
  val nfcUrl: String,
  val data: ArticleData
)

data class PushSolumImageRequest(
  val labelCode: String,
  val page: Int,
  val frontPage: Int,
  val image: String,
  val articleList: List<Article>
)
