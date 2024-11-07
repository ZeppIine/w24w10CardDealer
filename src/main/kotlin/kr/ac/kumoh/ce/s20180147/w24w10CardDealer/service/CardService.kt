package kr.ac.kumoh.ce.s20180147.w24w10CardDealer.service

import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.model.Card
import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.repository.CardRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CardService(private val repository: CardRepository) {
  companion object {
    const val CARDS_COUNT = 5
  }

  fun getAllCards(): List<Card> {
    return repository.getAllCards() // 모든 카드 반환
  }

  fun dealCards(count: Int = CARDS_COUNT) {
    val suits = arrayOf("clubs", "diamonds", "hearts", "spades")
    val ranks = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace")

    repository.clear()

    val uniqueCards = mutableSetOf<Card>()
    while (uniqueCards.size < count) {
      val randomSuit = suits[Random.nextInt(suits.size)]
      val randomRank = ranks[Random.nextInt(ranks.size)]
      uniqueCards.add(Card(randomRank, randomSuit))
    }

    val sortedCards = uniqueCards.toList()
      .sortedWith(compareBy({ suits.indexOf(it.suit) }, { ranks.indexOf(it.rank) }))

    sortedCards.forEach { repository.add(it) }
  }
}