package furhatos.app.pizzaorder

import furhatos.app.pizzaorder.nlu.OrderPizza
import furhatos.records.Record
import furhatos.records.User

val User.order : OrderPizza
    get() = data.getOrPut("order", OrderPizza())