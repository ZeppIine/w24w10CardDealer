package kr.ac.kumoh.ce.s20180147.w24w10CardDealer.controller

import kr.ac.kumoh.ce.s20180147.w24w10CardDealer.service.CardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CardController(private val service: CardService) {
  @GetMapping("/cards/random")
  fun getRandomCards(model: Model): String {
    service.dealCards()

    val cards = service.getAllCards()

    model.addAttribute("cards", cards[0].imageName)
    return "cards"
  }
}