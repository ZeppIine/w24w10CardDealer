package kr.ac.kumoh.ce.s20180147.w24w10CardDealer.repository

import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.model.Card

interface CardRepository {
  val size: Int
  fun add(card: Card)
  fun getAllCards(): List<Card>
  fun clear()
}