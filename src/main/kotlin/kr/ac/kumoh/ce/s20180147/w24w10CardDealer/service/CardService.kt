package kr.ac.kumoh.ce.s20180147.w24w10CardDealer.service

import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.model.Card
import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.repository.CardRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CardService(private val repository: CardRepository) {

  fun getAllCards(): List<Card> {
    return repository.getAllCards() // 모든 카드 반환
  }

  fun dealCards(count: Int = 5) {
    val suits = arrayOf("clubs", "diamonds", "hearts", "spades")
    val ranks = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace")

    repository.clear()

    val uniqueCards = mutableSetOf<Card>()
    while (uniqueCards.size < count) {
      val randomSuit = suits[Random.nextInt(suits.size)]
      val randomRank = ranks[Random.nextInt(ranks.size)]
      uniqueCards.add(Card(randomRank, randomSuit))
    }

    // 이 방법은 "10"이 "1"로 시작하므로 다른 숫자보다 먼저 나와서 변경했음
    //val sortedCards = uniqueCards.toList()
    //    .sortedWith(compareBy<Card> { it.suit }.thenBy { it.rank })
    val sortedCards = uniqueCards.toList()
      .sortedWith(compareBy({ suits.indexOf(it.suit) }, { ranks.indexOf(it.rank) }))

    sortedCards.forEach { repository.add(it) }
  }
}